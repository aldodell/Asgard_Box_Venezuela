package com.philosophicas.asgardboxvenezuela.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.philosophicas.asgardboxvenezuela.R
import com.philosophicas.asgardboxvenezuela.auth.AuthManager
import com.philosophicas.asgardboxvenezuela.ui.theme.AsgardBoxVenezuelaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navHostController: NavHostController?) {

    val context = LocalContext.current
    val authManager = AuthManager(context)
    val scope = rememberCoroutineScope()
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        authManager.handleResultSignInWithGoogle(GoogleSignIn.getSignedInAccountFromIntent(result.data))
    }
    AsgardBoxVenezuelaTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Registro")
                    }
                )
            }
        )
        { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            )

            //Button google
            {
                OutlinedButton(
                    onClick = {
                        googleSignInLauncher.launch(authManager.googleSignInIntent())
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)

                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_google),
                        contentDescription = "google"
                    )

                    Spacer(
                        modifier = Modifier
                            .width(16.dp)
                    )
                    Text(
                        text = "Registrarse con Google"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SignUp_Preview() {
    SignUp(null)
}