package com.practicaltask.ui.login

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.practicaltask.R
import com.practicaltask.api.local.Status
import com.practicaltask.base.BaseActivity
import com.practicaltask.databinding.ActivityLoginBinding
import com.practicaltask.ui.dashborad.DashboardActivity
import com.practicaltask.utils.Util.dismissProgress
import com.practicaltask.utils.Util.showProgress
import com.practicaltask.utils.extensions.isValidEmail
import com.practicaltask.utils.extensions.setSafeOnClickListener
import com.practicaltask.utils.extensions.showToast
import com.practicaltask.utils.local.PreferenceKeys.IS_USER_LOGIN
import com.practicaltask.utils.local.PreferenceKeys.USER_TOKEN
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val mViewModel by viewModels<LoginViewModel>()

    override fun init() {

        setObserver()

    }

    override fun setAllClickListener() {

        mBinding.btnLogin.setSafeOnClickListener {
            if (checkLogin()) {
                mViewModel.callForLogin(createLoginParams())
            }
        }

    }

    private fun setObserver() {

        mViewModel.loginResponse.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    showProgress()
                }

                Status.SUCCESS -> {
                    dismissProgress()
                    lifecycleScope.launch {
                        localStorage.setPref(IS_USER_LOGIN, true)
                        localStorage.setPref(USER_TOKEN, it.data?.token.toString())
                        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                        finish()
                    }
                }

                Status.ERROR -> {
                    dismissProgress()
                    it.message?.let {
                        showToast(it)
                    }
                }
            }
        }

    }

    private fun checkLogin(): Boolean {

        return when {
            mBinding.edEmail.text.toString().isEmpty() -> {
                showToast(R.string.please_enter_email_id)
                false
            }
            !mBinding.edEmail.text.toString().trim().isValidEmail() -> {
                showToast(R.string.please_enter_valid_email_id)
                false
            }
            mBinding.edPass.text.toString().isEmpty() -> {
                showToast(R.string.please_enter_password)
                false
            }
            else -> true

        }

    }

    private fun createLoginParams(): HashMap<String, Any> {
        val param = HashMap<String, Any>()
        param["email"] = mBinding.edEmail.text.toString()
        param["password"] = mBinding.edPass.text.toString()
        return param
    }

}