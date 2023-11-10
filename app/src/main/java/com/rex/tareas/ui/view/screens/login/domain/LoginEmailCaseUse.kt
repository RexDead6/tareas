package com.rex.tareas.ui.view.screens.login.domain

import com.rex.tareas.ui.view.screens.login.data.models.UserAuth
import com.rex.tareas.ui.view.screens.login.data.network.AuthServices
import javax.inject.Inject


class LoginEmailCaseUse @Inject constructor(
    private val authServices: AuthServices
){

    suspend operator fun invoke(userAuth: UserAuth): Boolean{
        return authServices.login(userAuth.email, userAuth.pass) != null
    }
}