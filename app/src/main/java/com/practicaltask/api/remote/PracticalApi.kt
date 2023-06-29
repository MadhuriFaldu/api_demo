package com.practicaltask.api.remote

import com.practicaltask.api.local.ApiParam
import com.practicaltask.ui.dashborad.category.CategoryListDataModel
import com.practicaltask.ui.dashborad.users.UsersListDataModel
import com.practicaltask.ui.login.LoginDataModel
import retrofit2.Response
import retrofit2.http.*

interface PracticalApi {

    @FormUrlEncoded
    @JvmSuppressWildcards
    @POST(ApiParam.Login)
    suspend fun callForLogin(
        @FieldMap fieldMap: Map<String, Any>
    ): Response<LoginDataModel>

    @JvmSuppressWildcards
    @GET(ApiParam.Unknown)
    suspend fun callCategoryList(): Response<CategoryListDataModel>

    @JvmSuppressWildcards
    @GET(ApiParam.Users)
    suspend fun callUsersList(): Response<UsersListDataModel>

}