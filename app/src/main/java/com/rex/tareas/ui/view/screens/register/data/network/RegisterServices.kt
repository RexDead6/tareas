package com.rex.tareas.ui.view.screens.register.data.network

import com.google.firebase.auth.AuthResult
import com.rex.tareas.provider.FirebaseProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterServices @Inject constructor(
    private val firebaseProvider: FirebaseProvider
) {
    suspend fun register(email: String, pass: String): AuthResult? {
        return firebaseProvider.auth.createUserWithEmailAndPassword(email, pass).await()
    }
}