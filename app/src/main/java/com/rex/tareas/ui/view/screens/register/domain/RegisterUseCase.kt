package com.rex.tareas.ui.view.screens.register.domain

import com.rex.tareas.ui.view.screens.register.data.models.UserRegister
import com.rex.tareas.ui.view.screens.register.data.network.RegisterServices
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerServices: RegisterServices
) {
    suspend operator fun invoke(userRegister: UserRegister): Boolean{
        return registerServices.register(userRegister.email, userRegister.pass) != null
    }
}