package com.philosophicas.asgardboxvenezuela.navigations


import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.philosophicas.asgardboxvenezuela.screens.Home
import com.philosophicas.asgardboxvenezuela.screens.SignUp
import kotlinx.serialization.Serializable


@Serializable
object HomeClass

@Serializable
object SignUpClass



@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeClass
    ) {
        composable<HomeClass> {
            Home(navController)
        }

        composable<SignUpClass> {
            SignUp(navController)
        }
    }
}
