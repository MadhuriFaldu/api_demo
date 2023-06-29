package com.practicaltask.api.local

import com.practicaltask.api.remote.PracticalApi
import com.practicaltask.ui.dashborad.category.CategoryListDataModel
import com.practicaltask.ui.dashborad.users.UsersListDataModel
import com.practicaltask.ui.login.LoginDataModel

class PracticalRepository(private val holderApi: PracticalApi) : BaseApiResponse() {

    /** Auth Module Apis */
    suspend fun callForLogin(param: Map<String, Any>): Result<LoginDataModel> {
        return safeApiCall {
            holderApi.callForLogin(param)
        }
    }

    suspend fun callCategoryList(): Result<CategoryListDataModel> {
        return safeApiCall {
            holderApi.callCategoryList()
        }
    }

    suspend fun callUsersList(): Result<UsersListDataModel> {
        return safeApiCall {
            holderApi.callUsersList()
        }
    }
}