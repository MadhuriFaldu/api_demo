package com.practicaltask.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.practicaltask.ui.login.User
import com.practicaltask.utils.local.LocalStorage
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity<B : ViewBinding>(val binding: (LayoutInflater) -> B) : AppCompatActivity() {

    var _binding: B? = null
    val mBinding: B get() = _binding!!

    @Inject
    lateinit var localStorage: LocalStorage

    var userDetails: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = binding(layoutInflater)
        setContentView(_binding!!.root)
        init()
        setAllClickListener()
    }

    abstract fun init()

    abstract fun setAllClickListener()

}