// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(R.string.app_name)) },
                        actions = {
                            IconButton(
                                onClick = {navController.navigate("search")}
                            ) { Icon(Icons.Rounded.Search, "Search") }
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = { navController.navigate("home") }
                            ) { Icon(Icons.AutoMirrored.Rounded.ArrowBack, "Back") }
                        }
                    )
                }
            ) {
                MyNavApplication(navController, modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun MyNavApplication(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(navController = navController, startDestination = "home"){
        composable("home") { HomePage(modifier = modifier) }
        composable("search") { Search(modifier = modifier) }
    }

}

@Composable
fun HomePage(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(text = "Вы на домашней странице")
    }

}
@Composable
fun Search(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(text = "Вы на странице поиска")
    }
}


@Preview(showBackground = true)
@Composable
fun MyPreview() {

}
