package com.example.android_accountbook_13.data

sealed class DataResponse<T> {
    data class Success<T>(val data: T? = null): DataResponse<T>()
    data class Error<T>(val errorMessage: String = "DataBase를 불러오지 못 했습니다."): DataResponse<T>()
}
