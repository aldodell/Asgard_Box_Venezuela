package com.philosophicas.asgardboxvenezuela.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.philosophicas.asgardboxvenezuela.R
import com.philosophicas.asgardboxvenezuela.auth.AuthManager
import com.philosophicas.asgardboxvenezuela.auth.GoogleSignInButton
import com.philosophicas.asgardboxvenezuela.ui.theme.AsgardBoxVenezuelaTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navHostController: NavHostController?) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val closingSession = remember { mutableStateOf(false) }
    val userName = remember {
        mutableStateOf(
            "Anonymous"
        )
    }

    LaunchedEffect(true) {
        scope.launch {
            userName.value = AuthManager(context).userName
        }
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
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Usuario: ${userName.value}",
                        modifier = Modifier
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    GoogleSignInButton(
                        modifier = Modifier
                            .fillMaxWidth(0.7f),

                        onSuccess = {
                            scope.launch {
                                val name =
                                    FirebaseAuth.getInstance().currentUser?.displayName
                                        ?: "anonymous"
                                Toast.makeText(context, "Bienvenido $name", Toast.LENGTH_LONG)
                                    .show()
                                userName.value = name
                                navHostController?.popBackStack()
                            }
                        },
                        onError = {
                            Toast.makeText(context, "Error: ${it}", Toast.LENGTH_LONG).show()
                            navHostController?.popBackStack()
                        }
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Cerrar sesión",
                        textDecoration = TextDecoration.Underline,
                        color = Color.Blue,
                        modifier = Modifier.clickable {
                            closingSession.value = true
                        }
                    )
                }
            }


        }
    }



    if (closingSession.value) {
        BasicAlertDialog(onDismissRequest = {}) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
            )
            {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Cerrar la sesión puede impedir algunos servicios de esta"
                                + " aplicación. Por favor considere mantener alguna sesión activa"

                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(
                        onClick = {
                            scope.launch {
                                try {
                                    FirebaseAuth.getInstance().signOut()
                                    userName.value = "Anonymous"
                                    navHostController?.popBackStack()
                                    Toast.makeText(context, "Sesión cerrada", Toast.LENGTH_LONG)
                                        .show()
                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "Error: ${e.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                            closingSession.value = false
                        },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)

                    ) {
                        Text("Cerrar sesión")
                    }
                    TextButton(
                        onClick = { closingSession.value = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Mantener sesión abierta")
                    }
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