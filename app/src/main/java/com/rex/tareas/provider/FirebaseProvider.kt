package com.rex.tareas.provider

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseProvider @Inject constructor() {
    val auth: FirebaseAuth get() = FirebaseAuth.getInstance()
    val db = Firebase.firestore
}