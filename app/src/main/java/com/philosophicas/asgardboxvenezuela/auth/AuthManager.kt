package com.philosophicas.asgardboxvenezuela.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.initialize
import com.google.firebase.ktx.Firebase

import com.philosophicas.asgardboxvenezuela.R
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class AuthManager() {
    lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
        FirebaseApp.initializeApp(context)
    }

    private val auth by lazy { FirebaseAuth.getInstance() }

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun googleSignInIntent(): Intent {
        val options = GoogleSignInOptions
            .Builder()
            .requestEmail()
            .requestProfile()
            .requestIdToken(context.getString(R.string.default_web_client))
            .requestId()
            .build()

        val client = GoogleSignIn.getClient(context, options)

        return client.signInIntent

    }

    fun handleResultSignInWithGoogle(
        task: Task<GoogleSignInAccount>,
        callback: (() -> Unit)? = null
    ) {
        task.addOnSuccessListener { result ->
            val credential = GoogleAuthProvider.getCredential(result.idToken, null)
            runBlocking {
                try {
                    auth.signInWithCredential(credential).await()
                    auth.currentUser?.let {
                        Toast.makeText(
                            context,
                            "Bienvenido: ${it.displayName}",
                            Toast.LENGTH_LONG
                        ).show()
                        callback?.invoke()
                    }
                } catch (e: FirebaseAuthInvalidUserException) {
                    Toast.makeText(context, "Usuario inválido: ${e.message}", Toast.LENGTH_LONG)
                        .show()
                } catch (e: FirebaseAuthInvalidCredentialsException) {

                    Toast.makeText(
                        context,
                        "Credenciales inválidos: ${e.message} ",
                        Toast.LENGTH_LONG
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Error logueando con Google: ${e.message}",
                        Toast.LENGTH_LONG

                    ).show()
                    Log.e("XXX", e.message ?: "xxx")
                }
            }
        }
            .addOnFailureListener { error ->


                Toast.makeText(
                    context,
                    "Error logueando con Google: ${error.message}",
                    Toast.LENGTH_LONG
                ).show()

            }
    }
}
