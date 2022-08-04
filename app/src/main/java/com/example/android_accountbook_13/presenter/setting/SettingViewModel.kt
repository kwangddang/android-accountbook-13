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
        getAllMethod({}, {})
        getAllCategory({}, {})
    }

    fun getAllMethod(onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            _methods.value = methodRepository.getAllMethod(onSuccess, onFailure)
        }
    }

    fun getAllCategory(onSuccess: () -> Unit, onFailure: () -> Unit) {
        getIncomeCategory(onSuccess, onFailure)
        getExpenseCategory(onSuccess, onFailure)
    }

    private fun getIncomeCategory(onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            _incomeCategories.value = categoryRepository.getIncomeCategory(onSuccess, onFailure)
        }
    }

    private fun getExpenseCategory(onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
                _expenseCategories.value = categoryRepository.getExpenseCategory(onSuccess, onFailure)
        }
    }

    fun insertMethod(method: Method, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            methodRepository.insertMethod(method,onSuccess, onFailure)
        }
    }

    fun insertCategory(category: Category, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            categoryRepository.insertCategory(category,onSuccess, onFailure)
        }
    }

    fun updateCategory(category: Category, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            categoryRepository.updateCategory(category,onSuccess, onFailure)
        }
    }

    fun updateMethod(method: Method, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            methodRepository.updateMethod(method,onSuccess, onFailure)
        }
    }
}

