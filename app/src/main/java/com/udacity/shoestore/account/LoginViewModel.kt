package com.udacity.shoestore.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var _username = ""
    private var _password = ""

    private val _isProcessing = MutableLiveData<Boolean>()
    val isProcessing: LiveData<Boolean>
        get() = _isProcessing

    private val _isInputValid = MutableLiveData<Boolean>()
    val isInputValid: LiveData<Boolean>
        get() = _isInputValid

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    fun validateInput(username: String, password: String) {
        _username = username
        _password = password

        _isInputValid.value = !(_username.isEmpty() || _password.isEmpty())
    }

    fun doLoging() {
        if (_isInputValid.value == false) {
            _isLoggedIn.value = false
            return
        }
        _isProcessing.value = true

        val delayTimeInMilis = 6 * 1000L
        viewModelScope.launch {
            delay(delayTimeInMilis)
            _isProcessing.value = false
            _isLoggedIn.value = true
        }
    }
}