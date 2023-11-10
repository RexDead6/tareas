package com.rex.tareas.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rex.tareas.core.navigation.Navigator
import com.rex.tareas.core.navigation.Screen
import com.rex.tareas.provider.FirebaseProvider
import com.rex.tareas.ui.theme.TareasTheme
import com.rex.tareas.ui.view.screens.login.LoginScreen
import com.rex.tareas.ui.view.screens.login.LoginViewModel
import com.rex.tareas.ui.view.screens.register.RegisterScreen
import com.rex.tareas.ui.view.screens.register.RegisterViewModel
import com.rex.tareas.ui.view.screens.tasks.TasksScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var firebaseProvider: FirebaseProvider

    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TareasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val isNavigate by navigator.isNavigate.collectAsState()
                    val back by navigator.back.collectAsState()

                    LaunchedEffect(isNavigate) {
                        if (isNavigate) {
                            navController.navigate(
                                navigator.destination.route,
                                navOptions = navigator.navOptions
                            )
                            navigator.isNavigate.value = false
                        }
                    }

                    LaunchedEffect(back) {
                        if (back) {
                            navController.popBackStack()
                            navigator.back.value = false
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = if (firebaseProvider.auth.currentUser != null) {
                            Screen.TasksNav.route
                        } else {
                            Screen.LoginNav.route
                        }
                    ) {
                        composable(Screen.LoginNav.route) {
                            LoginScreen(loginViewModel)
                        }

                        composable(Screen.RegisterNav.route) {
                            RegisterScreen(registerViewModel)
                        }

                        composable(Screen.TasksNav.route) {
                            TasksScreen()
                        }
                    }
                }
            }
        }
    }
}