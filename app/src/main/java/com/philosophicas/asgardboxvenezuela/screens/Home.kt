package com.philosophicas.asgardboxvenezuela.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.philosophicas.asgardboxvenezuela.R
import com.philosophicas.asgardboxvenezuela.navigations.SignUpClass
import com.philosophicas.asgardboxvenezuela.ui.theme.AsgardBoxVenezuelaTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController?) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var drawerState = rememberDrawerState(DrawerValue.Closed)

    Scaffold(
        modifier = Modifier,
        topBar = {
            //Text(text = context.applicationInfo.name)
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "DateRange")
                }

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = "DateRange")
                }


            }
        }
    )
    { paddingValues ->

        DismissibleNavigationDrawer(
            drawerContent = {
                DismissibleDrawerSheet() {
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text(text = "Home") },
                        onClick = {},
                        selected = false,
                    )
                }
            },
            drawerState = drawerState,
            modifier = Modifier

        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Image(
                    painter = painterResource(R.drawable.young1),
                    contentDescription = "young",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )

                Image(
                    painter = painterResource(R.drawable.asgardboxlogo),
                    contentDescription = "young",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_menu),
                        contentDescription = "Menu",
                    )
                }

                IconButton(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    onClick = {
                        scope.launch {
                            navController?.navigate(SignUpClass)
                        }
                    }
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_login),
                        contentDescription = "Menu"

                    )
                }

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Home_Preview() {
    AsgardBoxVenezuelaTheme(darkTheme = false, dynamicColor = false) {
        Home(null)
    }


}