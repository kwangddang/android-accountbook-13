package com.example.android_accountbook_13.presenter.history

import androidx.lifecycle.ViewModel
import com.example.android_accountbook_13.data.AccountBookItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HistoryViewModel  constructor() : ViewModel(){
    private val _accountBookItems = MutableStateFlow<List<AccountBookItem>>(emptyList())
    val accountBookItems: StateFlow<List<AccountBookItem>> get() = _accountBookItems

    private val _checkedItems = MutableStateFlow<List<AccountBookItem>>(emptyList())
    val checkedItems: StateFlow<List<AccountBookItem>> get() = _checkedItems

    private val _totalIncome = MutableStateFlow<Int>(0)
    val totalIncome: StateFlow<Int> get() = _totalIncome

    private val _totalExpense = MutableStateFlow<Int>(0)
    val totalExpense: StateFlow<Int> get() = _totalExpense

    var itemsByDay = mapOf<Int,List<AccountBookItem>>()

    fun getCheckedHistory(num: Int) {
        if(num > 1)
            _checkedItems.value = accountBookItems.value
        else {
            _checkedItems.value = accountBookItems.value.filter { item ->
                item.history.methodType == num
            }
        }
    }

    fun getTotalMoney() {
        _accountBookItems.value.forEach { item ->
            if(item.history.methodType == 0) {
                _totalIncome.value += item.history.money
            }
            else if(item.history.methodType == 1) {
                _totalExpense.value += item.history.money
            }
        }
    }
    
    fun setGroupByDay() {
        itemsByDay = checkedItems.value.groupBy { it.history.day }
    }
}