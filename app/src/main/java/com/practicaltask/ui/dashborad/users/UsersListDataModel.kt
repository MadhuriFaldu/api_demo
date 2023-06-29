package com.practicaltask.ui.dashborad.users

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersListDataModel(
    var data: ArrayList<User> = arrayListOf()
) : Parcelable

@Parcelize
data class User(
    var id: String? = "",
    var email: String? = "",
    var first_name: String? = "",
    var last_name: String? = "",
    var avatar: String? = "",
) : Parcelable