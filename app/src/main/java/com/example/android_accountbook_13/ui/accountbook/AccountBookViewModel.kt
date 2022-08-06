package com.example.android_accountbook_13.ui.accountbook

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.local.repository.accountbook.AccountRepository
import com.example.android_accountbook_13.data.local.repository.history.HistoryRepository
import com.example.android_accountbook_13.utils.Date
import com.example.android_accountbook_13.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountBookViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {
    private val _accountBookItems = MutableStateFlow<List<AccountBookItem>>(emptyList())

    private val _checkedItems = mutableStateOf<List<AccountBookItem>>(emptyList())
    val checkedItems: State<List<AccountBookItem>> get() = _checkedItems

    private val _incomeMoney = mutableStateOf(0L)
    val incomeMoney: State<Long> get() = _incomeMoney

    private val _expenseMoney = mutableStateOf(0L)
    val expenseMoney: State<Long> get() = _expenseMoney

    private val _date = mutableStateOf(getCurrentDate())
    val date: State<Date> get() = _date

    val incomeMoneyOfDay = mutableMapOf<Int, Long>()
    val expenseMoneyOfDay = mutableMapOf<Int, Long>()

    var navItem: AccountBookItem? = null

    init {
        getAccountBookItems()
    }


    fun getAccountBookItems(onFailure: (String?) -> Unit = {}) {
        viewModelScope.launch {
            accountRepository.getAccountBook(date.value.year, date.value.month, onFailure){
                _accountBookItems.value = it
            }
            getMoney()
        }
    }

    fun deleteHistory(historyIds: List<Int>, onFailure: (String?) -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            historyRepository.deleteHistory(historyIds, onFailure, onSuccess)
        }
    }

    fun insertHistory(history: History, onFailure: (String?) -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            historyRepository.insertHistory(history, onFailure, onSuccess)
        }
    }

    fun updateHistory(history: History, onFailure: (String?) -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            historyRepository.updateHistory(history, onFailure, onSuccess)
        }
    }

    fun getCheckedItems(incomeChecked: Boolean, expenseChecked: Boolean) {
        _checkedItems.value = _accountBookItems.value.filter {
            if (incomeChecked && expenseChecked) {
                true
            } else if (incomeChecked) {
                it.history.methodType == 0
            } else if (expenseChecked) {
                it.history.methodType == 1
            } else {
                false
            }
        }
        val group = _checkedItems.value.groupBy { it.history.day }
        incomeMoneyOfDay.clear()
        expenseMoneyOfDay.clear()
        group.forEach { (day, historyList) ->
            var income = 0L
            var expense = 0L
            for (item in historyList) {
                if (item.history.methodType == 0) {
                    income += item.history.money
                } else {
                    expense += item.history.money
                }
            }
            incomeMoneyOfDay[day] = income
            expenseMoneyOfDay[day] = expense
        }
    }

    fun decreaseDate() {
        _date.value = com.example.android_accountbook_13.utils.decreaseDate(_date.value)
        getAccountBookItems()
    }

    fun increaseDate() {
        _date.value = com.example.android_accountbook_13.utils.increaseDate(_date.value)
        getAccountBookItems()
    }

    fun changeDate(year: Int, month: Int, day: Int = 1) {
        _date.value = Date(year, month, day)
        getAccountBookItems()
    }

    fun getPairList(): List<Pair<Long, Category>> {
        val expenseHistory = _accountBookItems.value.filter { item ->
            item.history.methodType == 1
        }

        val group = expenseHistory.groupBy { item ->
            item.history.categoryId
        }
        val pair = arrayListOf<Pair<Long, Category>>()
        group.forEach { (categoryId, list) ->
            var money = 0L
            list.forEach { item ->
                money += item.history.money
            }
            pair.add(Pair(money, list[0].category))
        }
        pair.sortWith { o1, o2 -> (o2.first - o1.first).toInt() }

        return pair
    }

    private fun getMoney() {
        _incomeMoney.value = 0L
        _expenseMoney.value = 0L

        _accountBookItems.value.forEach { item ->
            item.history.apply {
                if (methodType == 0) {
                    _incomeMoney.value += money
                } else {
                    _expenseMoney.value += money
                }
            }
        }
    }
}