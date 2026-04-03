// package com.example.application245 - здесь название Вашего проекта!
 
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationController = rememberNavController()

            val navBackStackEntry = navigationController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(R.string.app_name)) },
                        actions = {
                            IconButton(onClick = {
                                navigationController.navigate(Destination.Search.route)
                            }) {
                                Icon(Icons.Rounded.Search, "Search")
                            }
                        },
                        navigationIcon = {
                            if (currentRoute != "home"){
                                IconButton(onClick = {
                                    navigationController.navigate(Destination.Home.route)
                                }) {
                                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, "Back")
                                }
                            }
                            else {
                                IconButton(onClick = {}) {
                                    Icon(
                                        ImageVector.vectorResource(R.drawable.rounded_directions_car_24),
                                        "Icon"
                                    )
                                }
                            }
                        }
                    )
                }
            ) { innerPadding ->
                MarketplaceNavHost(
                    navController = navigationController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun MarketplaceNavHost(navController: NavHostController, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        NavHost(
            navController = navController,
            startDestination = Destination.Home.route
        ){
            composable(route = Destination.Home.route) {
                HomeScreen()
            }
            composable(route = Destination.Search.route) {
                SearchScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(){
    val products = arrayOf(
        Item("Product 1", R.drawable.p1),
        Item("Product 2", R.drawable.p1),
        Item("Product 3", R.drawable.p1),
        Item("Product 4", R.drawable.p1),
        Item("Product 5", R.drawable.p1),
        Item("Product 6", R.drawable.p1)
    )

    LazyColumn() {
        items(items = products){ product ->
            SingleItem(product)
        }
    }
}

@Composable
fun SearchScreen(){
    val itemName = remember { mutableStateOf("") }

    Column() {
        TextField(
            value = itemName.value,
            onValueChange = {itemName.value = it}
        )
    }
}

@Composable
fun SingleItem(item: Item){
    Row() {
        Image(bitmap = ImageBitmap.imageResource(item.image), contentDescription = item.name)
        Text(text = item.name)
    }
}

@Preview(showBackground = true)
@Composable
fun MyPreview() {

}

data class Item(val name: String, val image: Int)

sealed class Destination(val route: String) {

    object Home: Destination("home")
    object Search: Destination("search")

}
