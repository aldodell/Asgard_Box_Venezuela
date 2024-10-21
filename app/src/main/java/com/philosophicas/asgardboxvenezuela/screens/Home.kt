package com.philosophicas.asgardboxvenezuela.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.philosophicas.asgardboxvenezuela.ui.theme.AsgardBoxVenezuelaTheme

@Composable
fun Home(navController: NavHostController?) {
    AsgardBoxVenezuelaTheme {
        Scaffold()
        { }
    }

}

@Preview(showBackground = true)
@Composable
fun Home_Preview() {
    Home(null)
}