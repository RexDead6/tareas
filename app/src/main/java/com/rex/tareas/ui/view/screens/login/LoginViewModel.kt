package com.rex.tareas.ui.view.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex.tareas.core.navigation.Navigator
import com.rex.tareas.core.navigation.Screen
import com.rex.tareas.ui.view.screens.login.data.models.UserAuth
import com.rex.tareas.ui.view.screens.login.domain.LoginEmailCaseUse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val loginEmailCaseUse: LoginEmailCaseUse
) : ViewModel() {
    private val _userAuth = MutableLiveData<UserAuth>((UserAuth()))
    val userAuth: LiveData<UserAuth> = _userAuth

    private val _enabledLogin = MutableLiveData<Boolean>(false)
    val enabledLogin: LiveData<Boolean> = _enabledLogin

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _inputErrors = MutableLiveData<MutableMap<String, String>>(mutableMapOf())
    val inputErrors: LiveData<MutableMap<String, String>> = _inputErrors

    fun onChangeLoginAuth(email: String, pass: String) {
        _userAuth.value = _userAuth.value!!.copy(email = email, pass = pass)
        _enabledLogin.value =
            _userAuth.value!!.email.isNotEmpty() && _userAuth.value!!.pass.isNotEmpty()
    }

    fun navigateToRegister() {
        navigator.navigate(Screen.RegisterNav)
    }

    private fun validateInputs(): Boolean {
        var success = true
        var errors = mutableMapOf<String, String>()

        if (_userAuth.value!!.email.isEmpty()) {
            errors["email"] = "Ingrese su correo electronico"
            success = false
        }

        if (_userAuth.value!!.pass.length < 6) {
            errors["pass"] = "Su clave debe ser mayor a 6"
            success = false
        }

        if (_userAuth.value!!.pass.isEmpty()) {
            errors["pass"] = "Ingrese su clave"
            success = false
        }

        _inputErrors.value = errors
        return success
    }

    fun onClickLoginEmail() {
        _inputErrors.value = mutableMapOf()
        if (!validateInputs()) return

        viewModelScope.launch {
            _isLoading.value = true
            if (loginEmailCaseUse(_userAuth.value!!)) {
                navigator.navigate(Screen.TasksNav, true)
            } else {
                _inputErrors.value = mutableMapOf(
                    "email" to "Correo electronico no registrado",
                    "pass" to "Clave incorrecta"
                )
            }
            _isLoading.value = false
        }
    }
}