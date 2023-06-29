package com.practicaltask.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.practicaltask.utils.SafeClickListener

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun Activity.showToast(message: String?) {
    if (message?.isNotBlank() == true) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun Activity.showToast(message: Int) {
    Toast.makeText(this,getString(message), Toast.LENGTH_SHORT).show()
}

fun String?.isValidEmail(): Boolean {
    return if (checkNotEmpty()) {
        this?.let {
            return PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
        }
        false
    } else {
        false
    }
}

fun String?.checkNotEmpty(): Boolean {
    return this != null && isNotEmpty() && isNotBlank()
}