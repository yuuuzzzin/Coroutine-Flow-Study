package com.example.flowstudy.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiSate: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    fun login(id: String?, password: String?) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
            delay(2000L)
            if(id.isNullOrBlank() || password.isNullOrBlank()) {
                _loginUiState.value = LoginUiState.Error("로그인 정보를 입력해주세요.")
            } else if(id == "yujin" && password == "yujin37") {
                _loginUiState.value = LoginUiState.Success
            } else {
                _loginUiState.value = LoginUiState.Error("아이디 또는 비밀번호를 확인해주세요.")
            }
        }
    }

    sealed class LoginUiState {
        object Success: LoginUiState()
        object Loading: LoginUiState()
        data class Error(val message: String): LoginUiState()
        object Empty: LoginUiState()
    }

}