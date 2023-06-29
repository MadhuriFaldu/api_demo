package com.practicaltask.api.hilt

import android.content.Context
import com.google.gson.GsonBuilder
import com.practicaltask.BuildConfig
import com.practicaltask.api.local.PracticalRepository
import com.practicaltask.api.remote.PracticalApi
import com.practicaltask.utils.local.LocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.rupinderjeet.kprogresshud.KProgressHUD
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    @Provides
    @Singleton
    fun getRetrofitApi(okHttpClient: OkHttpClient): PracticalApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create())).build()
            .create(PracticalApi::class.java)
    }

    @Provides
    @Singleton
    fun getRepository(api: PracticalApi): PracticalRepository {
        return PracticalRepository(api)
    }

    @Singleton
    @Provides
    fun createDefaultOkHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder()
            .connectTimeout(1000, TimeUnit.SECONDS)
            .readTimeout(1000, TimeUnit.SECONDS)
            .writeTimeout(1000, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun getStorage(@ApplicationContext context: Context): LocalStorage {
        return LocalStorage(context)
    }

    @Provides
    @Singleton
    fun getProgressBar(@ApplicationContext context: Context): KProgressHUD {
        return KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
    }


}