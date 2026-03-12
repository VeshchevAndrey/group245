// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(R.string.app_name)) },
                        actions = {
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "Search Button"
                                )
                            }
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.MoreVert,
                                    contentDescription = "More Button"
                                )
                            }
                        }
                    )
                },
                bottomBar = {
                    BottonAppBar()
                }
            ) {
                ImageFunction(modifier = Modifier.padding(it))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottonAppBar(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            imageVector = ImageVector.vectorResource(R.drawable.dog), // Ваш ресурс!
            contentDescription = "Dog Icon"
        )
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = ImageVector.vectorResource(R.drawable.dog), // Ваш ресурс!
            contentDescription = "Dog Icon",
            tint = Color(0xFFE8EAF6)
        )
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Rounded.Home,
            contentDescription = "Home Page"
        )
    }
}

@Composable
fun ImageFunction(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.dog_v),
            contentDescription = "The dog"
        )
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.dog),
            contentDescription = "Dog Icon"
        )
    }
}

@Preview
@Composable
fun PreviewForMyFunction(){
    ImageFunction()
}

