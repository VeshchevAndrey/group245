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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Отрисовка элементов интерфейса (Composable-функций) на экране приложения
        setContent {
            val mutableString = remember { mutableStateOf("") }
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
                            DropdownFunction(mutableString)
                        }
                    )
                },
            ) {
                SampleFunction(
                    modifier = Modifier.padding(it),
                    mutableString
                )
            }
        }
    }
}

@Composable
fun DropdownFunction(choicedText: MutableState<String>){
    val expandedState = remember { mutableStateOf(false) }

    IconButton(
        onClick = { expandedState.value = true }
    ) {
        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More Button")
    }
    DropdownMenu(
        expanded = expandedState.value,
        onDismissRequest = {expandedState.value = false}
    ) {
        DropdownMenuItem(
            text = { Text(text = "Сменить тему") },
            onClick = {  },
            leadingIcon = {
                Icon(
                    ImageVector.vectorResource(R.drawable.theme), // Заменить на свой ресурс!
                    contentDescription = "Dark Theme",
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        HorizontalDivider(
            thickness = 5.dp
        )
        DropdownMenuItem(
            text = { Text(text = "Создать группу") },
            onClick = { choicedText.value = "Создать группу" }
        )
        DropdownMenuItem(
            text = { Text(text = "Избранное") },
            onClick = { choicedText.value = "Избранное" }
        )
    }
}

@Composable
fun SampleFunction(modifier: Modifier = Modifier, choicedText: MutableState<String>){
    Text(
        text = choicedText.value,
        modifier = modifier)
}

@Preview
@Composable
fun PreviewForMyFunction(){
}

