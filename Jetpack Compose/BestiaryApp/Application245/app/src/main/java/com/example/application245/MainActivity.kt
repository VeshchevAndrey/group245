package com.example.application245

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.application245.ui.theme.Application245Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application245Theme() {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val viewModel : BestiaryViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "names"
    ) {
        composable("names") {
            NameScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = "details/{creatureId}",
            arguments = listOf(navArgument("creatureId") { type = NavType.IntType })
        ) { backStackEntry ->
            val creatureId = backStackEntry.arguments?.getInt("creatureId") ?: 0
            DetailsScreen(
                creatureId = creatureId,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameScreen(
    navController: NavController,
    viewModel: BestiaryViewModel
){
    val creatures = viewModel.currentCreatures.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Бестиарий") })
        },
        containerColor = Color.Black
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(items = creatures.value) { creature ->
                NameScreenItem(
                    creature = creature,
                    onClick = {
                        navController.navigate("details/${creature.id}")
                    })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    creatureId: Int,
    viewModel: BestiaryViewModel,
    navController: NavController
){
    val creature = viewModel.getCreatureById(creatureId)

    if (creature == null) {
        Scaffold { Text(text = "Существо не найдено", Modifier.padding(it)) }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = creature.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .height(250.dp)
                .background(Color.LightGray)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.battlebg_ffvii_forest),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            Image(
                bitmap = ImageBitmap.imageResource(creature.imageRes),
                contentDescription = creature.name,
                modifier = Modifier.size(250.dp).align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit,
            )
            Text(text = creature.description)
        }
    }
}

@Composable
fun NameScreenItem(creature: Creature, onClick: () -> Unit){
    Row(
        modifier = Modifier
            .padding(2.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .background(Color(0xFF1E3D95))
            .border(3.dp, color = Color(0xFFF4F9F9), shape = RectangleShape)
            .padding(20.dp)
    ) {
        Text(text = creature.name, color = Color.White)
    }
}