package com.rex.tareas.core.navigation

sealed class Screen(override val route: String) : NavigationDestination{
    object LoginNav : Screen("/login")
    object RegisterNav :  Screen("/register")
    object TasksNav : Screen("/tasks")
}
