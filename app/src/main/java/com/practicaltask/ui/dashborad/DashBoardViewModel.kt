package com.practicaltask.ui.dashborad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicaltask.api.local.PracticalRepository
import com.practicaltask.api.local.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.practicaltask.api.local.Result
import com.practicaltask.ui.dashborad.category.CategoryListDataModel
import com.practicaltask.ui.dashborad.users.UsersListDataModel
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(private val repository: PracticalRepository) :
    ViewModel() {

    private val _categoryResponse = MutableLiveData<Result<CategoryListDataModel>>()
    val categoryResponse: LiveData<Result<CategoryListDataModel>> = _categoryResponse

    private val _usersResponse = MutableLiveData<Result<UsersListDataModel>>()
    val usersResponse: LiveData<Result<UsersListDataModel>> = _usersResponse

    fun callCategoryList() = viewModelScope.launch {
        _categoryResponse.postValue(Result(Status.LOADING, null, null))
        _categoryResponse.postValue(repository.callCategoryList())
    }

    fun callUsersList() = viewModelScope.launch {
        _usersResponse.postValue(Result(Status.LOADING, null, null))
        _usersResponse.postValue(repository.callUsersList())
    }

}