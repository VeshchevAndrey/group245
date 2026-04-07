// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
            val currentSearchState = remember {
                mutableStateOf(SearchState("", "", ""))
            }

            val products = arrayOf(
                Item("Resident Evil Requem", R.drawable.p1, 7000.0),
                Item("Horizon Forbidden West", R.drawable.p1, 4990.0),
                Item("Ghost of Yotei", R.drawable.p1, 4990.0),
                Item("Marvel's Spider-Man 2", R.drawable.p1, 3999.0),
                Item("Elden Ring", R.drawable.p1, 4499.0),
                Item("Final Fantasy XVI", R.drawable.p1, 4990.0)
            )

            MarketplaceNavHost(
                navigationController,
                currentSearchState,
                products
            )
        }
    }
}

@Composable
fun MarketplaceNavHost(
    navController: NavHostController,
    curState: MutableState<SearchState>,
    productsArray:  Array<Item>
){
    Column() {
        NavHost(
            navController = navController,
            startDestination = Destination.Home.route
        ){
            composable(route = Destination.Home.route) {
                HomeScreen(productsArray, curState.value, navController)
            }
            composable(route = Destination.Search.route) {
                SearchScreen(navController, curState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(products: Array<Item>, currentState: SearchState, navController: NavController){
    val filteredProducts = remember(
        currentState.searchItem, currentState.minPrice, currentState.maxPrice
    ) {
        derivedStateOf {
            products.filter { product ->
                val nameMatch = (currentState.searchItem.isEmpty()) or
                        (product.name.contains(currentState.searchItem, true))
                val minVal = currentState.minPrice.toDoubleOrNull() ?: 0.0
                val maxVal = currentState.maxPrice.toDoubleOrNull() ?: 999999.0
                val priceMatch = (product.price >= minVal) and (product.price <= maxVal)

                (nameMatch) and (priceMatch)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Товары (${filteredProducts.value.size})") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Destination.Search.route)
                    }) {
                        Icon(Icons.Rounded.Search, "Search")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}, enabled = false) {
                        Icon(
                            ImageVector.vectorResource(R.drawable.rounded_directions_car_24),
                            "Icon"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(items = filteredProducts.value){ product ->
                SingleItem(product)
            }
        }

        if (filteredProducts.value.isEmpty()) {
            Text(text = "Products nof found!")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, curState: MutableState<SearchState>){
//    var newState = curState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Поиск") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Destination.Home.route)
                    }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TextField(
                value = curState.value.searchItem,
                onValueChange = {curState.value = curState.value.copy(searchItem = it)},
                label = { Text("Название товара") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = {curState.value = curState.value.copy(searchItem = "")}
                    ) {
                        Icon(Icons.Rounded.Clear, "Clear")
                    }
                }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Цена от", modifier = Modifier.padding(horizontal = 10.dp))
                OutlinedTextField(
                    value = curState.value.minPrice,
                    onValueChange = {curState.value = curState.value.copy(minPrice = it)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                Text(text = "до", modifier = Modifier.padding(horizontal = 10.dp))
                OutlinedTextField(
                    value = curState.value.maxPrice,
                    onValueChange = {curState.value = curState.value.copy(maxPrice = it)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) { Text("Применить фильтры") }
            Spacer(modifier = Modifier.weight(2f))
        }
    }
}

@Composable
fun SingleItem(item: Item){
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(bitmap = ImageBitmap.imageResource(item.image), contentDescription = item.name)
        Text(text = item.name, modifier = Modifier.weight(1f))
        Text(text = item.price.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun MyPreview() {

}

data class Item(val name: String, val image: Int, val price: Double)

data class SearchState(val searchItem: String, val minPrice: String, val maxPrice: String)

sealed class Destination(val route: String) {

    object Home: Destination("home")
    object Search: Destination("search")

}
