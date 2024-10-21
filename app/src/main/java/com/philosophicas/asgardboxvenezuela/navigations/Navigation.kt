package com.philosophicas.asgardboxvenezuela.navigations


import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.philosophicas.asgardboxvenezuela.screens.SignUp
import kotlinx.serialization.Serializable


@Serializable
object Home

@Serializable
object SignUp


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SignUp
    ) {
        composable<Home> {

        }

        composable<SignUp> {
            SignUp(navController)
        }
    }
}
