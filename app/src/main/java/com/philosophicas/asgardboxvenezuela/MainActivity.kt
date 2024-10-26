package com.philosophicas.asgardboxvenezuela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.philosophicas.asgardboxvenezuela.auth.AuthManager
import com.philosophicas.asgardboxvenezuela.navigations.Navigation
import com.philosophicas.asgardboxvenezuela.ui.theme.AsgardBoxVenezuelaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        System.setProperty("kotlinx.coroutines.debug", "on")

        enableEdgeToEdge()
        setContent {
            AsgardBoxVenezuelaTheme(darkTheme = false, dynamicColor = false)
            {
                val navController = rememberNavController()
                Navigation(navController)
            }

        }
    }
}

