package com.practicaltask.ui.dashborad.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryListDataModel(
    var data: ArrayList<Category> = arrayListOf()
) : Parcelable

@Parcelize
data class Category(
    var id: String? = "",
    var name: String? = "",
    var year: String? = "",
    var color: String? = "",
) : Parcelable