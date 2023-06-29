package com.practicaltask.utils

import android.content.Context
import com.practicaltask.api.hilt.ApiClient.getProgressBar
import io.github.rupinderjeet.kprogresshud.KProgressHUD

object Util {

    private var p: KProgressHUD? = null

    fun Context.showProgress() {
        p = getProgressBar(this)
        p?.show()
    }

    fun Context.dismissProgress() {
        p?.dismiss()
    }

}