package com.example.android_accountbook_13.presenter.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_accountbook_13.data.DataResponse
import com.example.android_accountbook_13.data.dto.Category
import com.example.android_accountbook_13.data.dto.Method
import com.example.android_accountbook_13.data.local.repository.category.CategoryRepository
import com.example.android_accountbook_13.data.local.repository.method.MethodRepository
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

    fun getAllMethod() {
        viewModelScope.launch {
            val response = methodRepository.getAllMethod()
            if (response is DataResponse.Success) {
                _methods.value = response.data!!
            }
        }
    }

    fun getIncomeCategory() {
        viewModelScope.launch {
            val response = categoryRepository.getIncomeCategory()
            if (response is DataResponse.Success) {
                _incomeCategories.value = response.data!!
            }
        }
    }

    fun getExpenseCategory() {
        viewModelScope.launch {
            val response = categoryRepository.getExpenseCategory()
            if (response is DataResponse.Success) {
                _expenseCategories.value = response.data!!
            }
        }
    }

    fun insertMethod(method: Method) {
        viewModelScope.launch {
            val response = methodRepository.insertMethod(method)
            if (response is DataResponse.Error) {

            }
        }
    }

    fun insertCategory(category: Category) {
        viewModelScope.launch {
            val response = categoryRepository.insertCategory(category)
            if (response is DataResponse.Error) {

            }
        }
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch {
            val response = categoryRepository.updateCategory(category)
            if (response is DataResponse.Error) {

            }
        }
    }

    fun updateMethod(method: Method) {
        viewModelScope.launch {
            val response = methodRepository.updateMethod(method)
            if (response is DataResponse.Error) {

            }
        }
    }
}

