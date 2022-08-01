package com.example.android_accountbook_13.presenter.history

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.AccountBookItem
import com.example.android_accountbook_13.data.local.repository.accountbook.AccountRepository
import com.example.android_accountbook_13.data.local.repository.history.HistoryRepository
import com.example.android_accountbook_13.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {
    private val _accountBookItems = MutableStateFlow<List<AccountBookItem>>(emptyList())

    private val _checkedItems = MutableStateFlow<List<AccountBookItem>>(emptyList())
    val checkedItems: StateFlow<List<AccountBookItem>> get() = _checkedItems

    private val _incomeMoney = MutableStateFlow<Long>(0L)
    val incomeMoney: StateFlow<Long> get() = _incomeMoney

    private val _expenseMoney = MutableStateFlow<Long>(0L)
    val expenseMoney: StateFlow<Long> get() = _expenseMoney

    var date = mutableStateOf(getCurrentDate())

    fun getAccountBookItems(year: Int, month: Int) {
        viewModelScope.launch {
            val response = accountRepository.getAccountBook(year, month)
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
            } else if(expenseChecked) {
                it.history.methodType == 0
            } else {
                false
            }
        }
    }

    fun deleteHistory(deleteIdList: List<Int>) {
        viewModelScope.launch {
            deleteIdList.forEach{ id ->
                historyRepository.deleteHistory(id)
            }
        }
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