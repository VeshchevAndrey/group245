// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application245.ui.theme.Application245Theme
import kotlinx.coroutines.launch

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

// animateDpAsState - выполняет анимации для значений, использующих dp

@Composable
fun ApplicationScreen(modifier: Modifier){
    val startOffset = 0
//    val endOffset = 100
    val endOffset = LocalConfiguration.current.screenWidthDp - 150

    val boxOffset = remember { mutableStateOf(startOffset) }

    val animationOffset = animateDpAsState(
        targetValue = boxOffset.value.dp,
        animationSpec = tween(
            durationMillis = 2500,
            easing = FastOutSlowInEasing,
            delayMillis = 500
        )
    )

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .offset(x = animationOffset.value)
                .size(150.dp)
                .background(Color.LightGray)
        )
        Box(
            modifier = Modifier
                .size(animationOffset.value)
                .background(Color.LightGray)
        )
        Button(onClick = {
            boxOffset.value = if (boxOffset.value == startOffset) endOffset else startOffset
        }) { Text(text = "Запустить") }
    }
}
