package com.example.android_accountbook_13.utils

import com.example.android_accountbook_13.data.DataResponse

class Event(var event: DataResponse<*>){
    operator fun invoke(e: DataResponse<*>) {
        event = e
    }
}
