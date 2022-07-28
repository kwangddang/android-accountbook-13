package com.example.android_accountbook_13.presenter.history

import androidx.lifecycle.ViewModel
import com.example.android_accountbook_13.data.HistoryItem
import com.example.android_accountbook_13.domain.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel(){
    private val _historyItems = MutableStateFlow<List<HistoryItem>>(emptyList())
    val historyItems: StateFlow<List<HistoryItem>> get() = _historyItems

    private val _checkedItems = MutableStateFlow<List<HistoryItem>>(emptyList())
    val checkedItems: StateFlow<List<HistoryItem>> get() = _checkedItems

    private val _totalIncome = MutableStateFlow<Int>(0)
    val totalIncome: StateFlow<Int> get() = _totalIncome

    private val _totalExpense = MutableStateFlow<Int>(0)
    val totalExpense: StateFlow<Int> get() = _totalExpense

    var itemsByDay = mapOf<Int,List<HistoryItem>>()

    fun getHistory(month: Int) {
        _historyItems.value = getHistoryUseCase(month)
    }

    fun getCheckedHistory(num: Int) {
        if(num > 1)
            _checkedItems.value = historyItems.value
        else {
            _checkedItems.value = historyItems.value.filter { item ->
                item.payment.methodType == num
            }
        }
    }

    fun getTotalMoney() {
        _historyItems.value.forEach { item ->
            if(item.payment.methodType == 0) {
                _totalIncome.value += item.payment.money
            }
            else if(item.payment.methodType == 1) {
                _totalExpense.value += item.payment.money
            }
        }
    }
    
    fun setGroupByDay() {
        itemsByDay = checkedItems.value.groupBy { it.payment.day }
    }
}