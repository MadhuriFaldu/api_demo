package com.practicaltask.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicaltask.api.local.PracticalRepository
import com.practicaltask.api.local.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.practicaltask.api.local.Result
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: PracticalRepository) : ViewModel() {

    private val _loginResponse = MutableLiveData<Result<LoginDataModel>>()
    val loginResponse: LiveData<Result<LoginDataModel>> = _loginResponse

    fun callForLogin(param: Map<String, Any>) = viewModelScope.launch {
        _loginResponse.postValue(Result(Status.LOADING, null, null))
        _loginResponse.postValue(repository.callForLogin(param))
    }

}