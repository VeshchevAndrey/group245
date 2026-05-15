// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.application245.ui.theme.Application245Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application245Theme() {
                Scaffold() { paddingValues ->
                    ApplicationScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

// animateColorAsState - выполняет анимации для значений типа Color, т.е. изменяет цвет
// keyframes {} - изменение анимации через ключевые кадры, использующие длительность анимации
// AnimatedVisibility = анимирует появление и исчезновение обьекта

@Composable
fun ApplicationScreen(modifier: Modifier){
    val firstColor = Color(0xFF000000)
    val secondColor = Color(0xFFFF5722)

//    val startOffset = 0
//    val endOffser = LocalConfiguration.current.screenWidthDp - 150
//
//    val boxOffset = remember { mutableStateOf(startOffset) }
//    val offsetAnimation = animateDpAsState(boxOffset.value.dp)
//    val boxSize = remember { mutableStateOf(false) }
//    val sizeAnimation = animateDpAsState(
//        targetValue = if (boxSize.value) 10.dp else 10.dp,
//        animationSpec = keyframes {
//            durationMillis = 2500
//            10.dp at 0
//            150.dp at 1250
//            10.dp at 2500
//        })

    val boxColor = remember { mutableStateOf(firstColor) }
    val colorAnimation = animateColorAsState(
        targetValue = boxColor.value,
        animationSpec = tween(durationMillis = 2500)
    )

    val colorKeyframeAnimation = animateColorAsState(
        targetValue = boxColor.value,
        animationSpec = keyframes {
            durationMillis = 2500
            Color.Red at 500
            Color.Green at 1500
            Color.Blue at 2000
        }
    )

    val boxVisible = remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(modifier = Modifier
            .size(150.dp)
            .background(colorAnimation.value)
        )
        Box(modifier = Modifier
            .size(150.dp)
            .background(colorKeyframeAnimation.value)
        )
        Button(onClick = {
            boxColor.value = if (boxColor.value == firstColor) secondColor else firstColor
        }) { Text(text = "Изменить") }
//        Box(modifier = Modifier
//            .offset(x = offsetAnimation.value)
//            .size(sizeAnimation.value)
//            .background(firstColor)
//        )
//        Button(onClick = {
//            boxSize.value = !boxSize.value
//            boxOffset.value = if (boxOffset.value == startOffset) endOffser else startOffset
//        }) {
//            Text(text = "Запустить")
//        }
        AnimatedVisibility(visible = boxVisible.value) {
            Box(modifier = Modifier
                .size(150.dp)
                .background(secondColor)
            )
        }
        Button(onClick = {
            boxVisible.value = !boxVisible.value
        }) { Text(text = "Видимость") }
    }
}
