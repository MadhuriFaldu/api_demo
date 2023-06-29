package com.practicaltask.ui.login

import android.os.Parcelable
import com.practicaltask.api.local.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginDataModel(
    val token : String? = ""
) : BaseModel(), Parcelable

@Parcelize
data class RESULT(
    val user: User? = null
) : Parcelable

@Parcelize
data class User(
    val user_id: String? = null,
    val email: String? = null,
    val firstname: String? = null,
    val photo: String? = null,
    val age: String? = null,
    val gender: String? = null,
    val user_type: String? = null,
    val fb_id: String? = null,
    val devicetype: String? = null,
    val devicetoken: String? = null,
    val activation_token: String? = null,
    val is_active: String? = null,
) : Parcelable