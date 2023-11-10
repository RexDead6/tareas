package com.rex.tareas.core.navigation

import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
    var destination: NavigationDestination = Screen.LoginNav
    var navOptions: NavOptions = NavOptions.Builder().build()

    var isNavigate : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var back: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun navigate(destination: NavigationDestination, delStack: Boolean = false) {
        isNavigate.value = true
        this.destination = destination
        navOptions = if (delStack) {
            NavOptions.Builder()
                .setPopUpTo(destination.route, inclusive = true)
                .build()
        }else{
            NavOptions.Builder()
                .build()
        }
    }

    fun toBack() {
        back.value = true
    }
}