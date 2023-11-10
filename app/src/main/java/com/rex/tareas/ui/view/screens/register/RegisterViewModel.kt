package com.rex.tareas.ui.view.screens.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex.tareas.core.navigation.Navigator
import com.rex.tareas.core.navigation.Screen
import com.rex.tareas.ui.view.screens.register.data.models.UserRegister
import com.rex.tareas.ui.view.screens.register.domain.RegisterUseCase
import com.rex.tareas.ui.view.screens.tasks.TasksScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator: Navigator,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _userRegister = MutableLiveData<UserRegister>(UserRegister())
    val userRegister: LiveData<UserRegister> = _userRegister

    private val _enabledButton = MutableLiveData<Boolean>(false)
    val enabledButton: LiveData<Boolean> = _enabledButton

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _messageError = MutableLiveData<String>("")
    val messageError: LiveData<String> = _messageError

    private val _inputErrors = MutableLiveData<MutableMap<String, String>>(mutableMapOf())
    val inputErrors: LiveData<MutableMap<String, String>> = _inputErrors

    fun onChangeUserRegister(name: String, email: String, pass: String, passConfirm: String){
        _userRegister.value = _userRegister.value!!.copy(
            name = name,
            email = email,
            pass = pass,
            passConfirm = passConfirm
        )
        _enabledButton.value = _userRegister.value!!.email.isNotEmpty() &&
                _userRegister.value!!.name.isNotEmpty() &&
                _userRegister.value!!.pass.isNotEmpty() &&
                _userRegister.value!!.passConfirm.isNotEmpty()
    }

    fun backButton(){
        navigator.toBack()
    }

    private fun validateInputs(): Boolean{
        var success = true
        var errors = mutableMapOf<String, String>()

        if (_userRegister.value!!.name.isEmpty()){
            success = false
            errors["name"] = "Nombre no puede estar vacio"
        }

        if (_userRegister.value!!.email.isEmpty()){
            success = false
            errors["email"] = "Correo electronico no puede estar vacio"
        }

        if (_userRegister.value!!.pass.isEmpty()){
            success = false
            errors["pass"] = "Clave no puede estar vacio"
        }

        if (_userRegister.value!!.passConfirm.isEmpty()){
            success = false
            errors["passConfirm"] = "Confirmacion de clave no puede estar vacio"
        }

        if (_userRegister.value!!.pass.length < 6){
            success = false
            errors["pass"] = "Clave debe contener mas de 6 caracteres"
        }

        _inputErrors.value = errors

        return  success
    }

    fun registerUser(){
        _inputErrors.value = mutableMapOf()

        if (!validateInputs()) return

        viewModelScope.launch {
            _isLoading.value = true
            val success = registerUseCase(_userRegister.value!!)
            if (!success) {
                _messageError.value = "No se ha podido conectar con el servicio"
            }else{
                navigator.navigate(Screen.TasksNav)
            }
            _isLoading.value = false
        }
    }
}