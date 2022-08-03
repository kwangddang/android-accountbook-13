package com.example.android_accountbook_13.presenter.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.data.dto.History
import com.example.android_accountbook_13.data.local.repository.accountbook.AccountRepository
import com.example.android_accountbook_13.data.local.repository.history.HistoryRepository
import com.example.android_accountbook_13.utils.Date
import com.example.android_accountbook_13.utils.Event
import com.example.android_accountbook_13.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {
    private val _accountBookItems = MutableStateFlow<List<AccountBookItem>>(emptyList())
    val accountBookItems: StateFlow<List<AccountBookItem>> get() = _accountBookItems

    private val _checkedItems = MutableStateFlow<List<AccountBookItem>>(emptyList())
    val checkedItems: StateFlow<List<AccountBookItem>> get() = _checkedItems

    private val _incomeMoney = mutableStateOf(0L)
    val incomeMoney: State<Long> get() = _incomeMoney

    private val _expenseMoney = mutableStateOf(0L)
    val expenseMoney: State<Long> get() = _expenseMoney

    private val _date = mutableStateOf(getCurrentDate())
    val date: State<Date> get() = _date

    val incomeMoneyOfDay = mutableMapOf<Int, Long>()
    val expenseMoneyOfDay = mutableMapOf<Int, Long>()

    var isSuccess = mutableStateOf(Event(DataResponse.Empty))

    init {
        getAccountBookItems()
    }


    fun getAccountBookItems() {
        viewModelScope.launch {
            val response = accountRepository.getAccountBook(date.value.year, date.value.month)
            if (response is DataResponse.Success) {
                _accountBookItems.value = response.data!!
                getMoney()
            }
        }
    }

    fun getCheckedItems(incomeChecked: Boolean, expenseChecked: Boolean) {
        _checkedItems.value = _accountBookItems.value.filter {
            if (incomeChecked && expenseChecked) {
                true
            } else if (incomeChecked) {
                it.history.methodType == 1
            } else if (expenseChecked) {
                it.history.methodType == 0
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
                if (item.history.methodType == 1) {
                    income += item.history.money
                } else {
                    expense += item.history.money
                }
            }
            incomeMoneyOfDay[day] = income
            expenseMoneyOfDay[day] = expense
        }
    }

    fun deleteHistory(historyIds: List<Int>) {
        viewModelScope.launch {
            isSuccess.value = Event(historyRepository.deleteHistory(historyIds))
        }
    }

    fun insertHistory(history: History) {
        viewModelScope.launch {
            isSuccess.value = Event(historyRepository.insertHistory(history))
        }
    }

    fun updateHistory(history: History) {
        viewModelScope.launch {
            isSuccess.value = Event(historyRepository.updateHistory(history))
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

    private fun getMoney() {
        _expenseMoney.value = 0L
        _incomeMoney.value = 0L

        _accountBookItems.value.forEach { item ->
            item.history.apply {
                if (methodType == 0) {
                    _expenseMoney.value += money
                } else {
                    _incomeMoney.value += money
                }
            }
        }
    }
}