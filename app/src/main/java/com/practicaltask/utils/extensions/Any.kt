package com.practicaltask.utils.extensions

import com.google.gson.Gson

fun convertObjectToString(value: Any): String {
    return Gson().toJson(value)
}

fun <T> convertStringToObject(key: String, className: Class<T>): T {
    return Gson().fromJson(key, className)
}