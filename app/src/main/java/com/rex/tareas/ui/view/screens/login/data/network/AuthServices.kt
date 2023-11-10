package com.rex.tareas.ui.view.screens.login.data.network

import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.rex.tareas.provider.FirebaseProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthServices @Inject constructor(
    private val firebaseProvider: FirebaseProvider
) {
    suspend fun login(email: String, pass: String): AuthResult? {
        return try {
            firebaseProvider.auth.signInWithEmailAndPassword(email, pass).await()
        } catch (e: FirebaseException){
            null
        }
    }
}