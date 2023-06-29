package com.practicaltask.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.practicaltask.base.BaseActivity
import com.practicaltask.databinding.ActivitySplashBinding
import com.practicaltask.ui.dashborad.DashboardActivity
import com.practicaltask.ui.login.LoginActivity
import com.practicaltask.utils.local.PreferenceKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun init() {
        goToHomeApp()
    }

    override fun setAllClickListener() {

    }

    private fun goToHomeApp() {
        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launch {
                localStorage.getPref(PreferenceKeys.IS_USER_LOGIN, false) {
                    if (this) {
                        startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }, 2000)
    }

}