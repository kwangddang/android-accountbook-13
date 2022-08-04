package com.example.android_accountbook_13.presenter.setting

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.repository.category.CategoryRepository
import com.example.android_accountbook_13.data.local.repository.method.MethodRepository
import com.example.android_accountbook_13.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val methodRepository: MethodRepository,
    private val categoryRepository: CategoryRepository,
) : ViewModel() {
    private val _methods = MutableStateFlow<List<Method>>(emptyList())
    val methods: StateFlow<List<Method>> get() = _methods

    private val _incomeCategories = MutableStateFlow<List<Category>>(emptyList())
    val incomeCategories: StateFlow<List<Category>> get() = _incomeCategories

    private val _expenseCategories = MutableStateFlow<List<Category>>(emptyList())
    val expenseCategories: StateFlow<List<Category>> get() = _expenseCategories

    var isMethodSuccess = mutableStateOf(Event(DataResponse.Empty))
    var isCategorySuccess = mutableStateOf(Event(DataResponse.Empty))

    init {
        getAllMethod()
        getAllCategory()
    }

    fun getAllMethod(onFailure: () -> Unit = {}) {
        viewModelScope.launch {
            methodRepository.getAllMethod(onFailure) {
                _methods.value = it
            }
        }
    }

    fun getAllCategory(onFailure: () -> Unit = {}) {
        getIncomeCategory(onFailure)
        getExpenseCategory(onFailure)
    }

    private fun getIncomeCategory(onFailure: () -> Unit) {
        viewModelScope.launch {
            categoryRepository.getIncomeCategory(onFailure){
                _incomeCategories.value = it
            }
        }
    }

    private fun getExpenseCategory(onFailure: () -> Unit) {
        viewModelScope.launch {
            categoryRepository.getExpenseCategory(onFailure){
                _expenseCategories.value = it
            }
        }
    }

    fun insertMethod(method: Method, onFailure: () -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            methodRepository.insertMethod(method, onFailure, onSuccess)
        }
    }

    fun insertCategory(category: Category, onFailure: () -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            categoryRepository.insertCategory(category, onFailure, onSuccess)
        }
    }

    fun updateCategory(category: Category, onFailure: () -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            categoryRepository.updateCategory(category, onFailure, onSuccess)
        }
    }

    fun updateMethod(method: Method, onFailure: () -> Unit, onSuccess: () -> Unit) {
        viewModelScope.launch {
            methodRepository.updateMethod(method, onFailure, onSuccess)
        }
    }
}

