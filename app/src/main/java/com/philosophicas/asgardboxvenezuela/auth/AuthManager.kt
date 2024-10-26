package com.philosophicas.asgardboxvenezuela.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.philosophicas.asgardboxvenezuela.R
import kotlinx.coroutines.runBlocking

class AuthManager(val context: Context) {
    val userName = FirebaseAuth.getInstance().currentUser?.displayName ?: "Anonymous"
}