// package com.example.application245

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorScreen()
        }
    }
}

@Composable
fun CalculatorScreen(){
//     val currentCalculatorState = remember { mutableStateOf(CalculatorState()) }
    val display = rememberSaveable { mutableStateOf("0") }
    val previousNumber = rememberSaveable { mutableStateOf<Double?>(null) }
    val currentOperator = rememberSaveable { mutableStateOf<String?>(null) }
    val isNewNumber = rememberSaveable { mutableStateOf(true) }


    val buttons = arrayOf(
        stringArrayResource(R.array.first_row),
        stringArrayResource(R.array.second_row),
        stringArrayResource(R.array.third_row),
        stringArrayResource(R.array.fourth_row),
        stringArrayResource(R.array.fifth_row)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
//            text = currentCalculatorState.value.display,
            text = display.value,
            fontSize = 72.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            textAlign = TextAlign.Right
            )
        buttons.forEach { row ->
            Row(
                modifier = Modifier
                    .padding(vertical = 0.5f.dp)
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                row.forEach { labelButton ->
                    CalculatorButton(
                        label = labelButton,
                        onButtonClick = {
//                            handleButtonClick(
//                                button = labelButton,
//                                currentState = currentCalculatorState.value,
//                                updateState = {
//                                    newState -> currentCalculatorState.value = newState
//                                }
//                            )
                            handleButtonClick(
                                button = labelButton,
                                display = display.value,
                                previousNumber = previousNumber.value,
                                currentOperator = currentOperator.value,
                                isNewNumber = isNewNumber.value,
                                updateState = { newDisplay, newPrevious, newOperator, newInput ->
                                    display.value = newDisplay
                                    previousNumber.value = newPrevious
                                    currentOperator.value = newOperator
                                    isNewNumber.value = newInput
                                }
                            )
                        },
                        modifier = Modifier
                            .weight( if (labelButton == "0") 2f else 1f)
                            .fillMaxSize(),
                        color = if (labelButton in arrayOf("+", "-", "*", "/", "=")) {
                            Color.Red
                        } else Color.White ,
                        textColor = if (labelButton in arrayOf("+", "-", "*", "/", "=")) {
                            Color.White
                        } else Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    label: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    textColor: Color = Color.Black,
    onButtonClick: () -> Unit
){
    Button(
        onClick = { onButtonClick() },
        shape = RectangleShape,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = textColor)
    ) {
        Text(
            text = label,
            fontSize = 36.sp,
            fontWeight = FontWeight.Light
        )
    }
}

fun handleButtonClick(
    button: String,
//    currentState: CalculatorState,
    display: String,
    previousNumber: Double?,
    currentOperator: String?,
    isNewNumber: Boolean,
//    updateState: (CalculatorState) -> Unit
    updateState: (String, Double?, String?, Boolean) -> Unit
){
//    var newState = currentState
    var newDisplay = display
    var newPreviousNumber = previousNumber
    var newCurrentOperator = currentOperator
    var newInputFlag = isNewNumber

    when (button){
        ".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
//            if (newState.isNewNumber) {
            if (newInputFlag) {
//                newState = newState.copy(
//                    display = if (button == ".") "0." else button,
//                    isNewNumber = false
//                )
                newDisplay = if (button == ".") "0." else button
                newInputFlag = false
            }
            else {
//                val newDisplay = if ((newState.display == "0") and (button != ".")) button else newState.display + button
//                newState = newState.copy(display = newDisplay)
                newDisplay = if ((display == "0") and (button != "."))  button else display + button
            }
        }
        "+", "-", "*", "/" -> {
//            val currentNumber = newState.display.toDoubleOrNull() ?: 0.0
            val currentNumber = newDisplay.toDoubleOrNull() ?: 0.0

//            newState = if ((newState.previousNumber == null) or (newState.currentOperator == null)){
//                newState.copy(
//                    previousNumber = currentNumber,
//                    currentOperator = button,
//                    isNewNumber = true
//                )
//            }
//            else {
//                val result = calculate(newState.previousNumber!!, currentNumber, newState.currentOperator!!)
//                newState.copy(
//                    display = result.toString().removeSuffix(".0"),
//                    previousNumber = result,
//                    currentOperator = button,
//                    isNewNumber = true
//                )
//            }
            if (previousNumber == null){
                newPreviousNumber = currentNumber
            }
            else if (currentOperator != null) {
                val result = calculate(previousNumber, currentNumber, currentOperator!!)
                newDisplay = result.toString().removeSuffix(".0")
                newPreviousNumber = result
            }
            newCurrentOperator = button
            newInputFlag  = true
        }
        "=" -> {
//            val currentNumber = newState.display.toDoubleOrNull() ?: 0.0
//            val result = calculate(newState.previousNumber!!, currentNumber, newState.currentOperator!!)
//
//            newState = newState.copy(
//                display = result.toString().removeSuffix(".0"),
//                previousNumber = null,
//                currentOperator = null,
//                isNewNumber = true
//            )
            if ((currentOperator != null) and (previousNumber != null)){
                val currentNumber = newDisplay.toDoubleOrNull() ?: 0.0
                val result = calculate(previousNumber!!, currentNumber, currentOperator!!)

                newDisplay = result.toString().removeSuffix(".0")
                newPreviousNumber = null
                newCurrentOperator = null
                newInputFlag = true
            }
        }
        "+/-" -> {
//            if (newState.display != "0"){
//                val number = newState.display.toDoubleOrNull() ?: 0.0
//                newState = newState.copy(display = (-number).toString())
//            }
            if (newDisplay != "0"){
                val number = newDisplay.toDoubleOrNull() ?: 0.0
                newDisplay = (-number).toString()
            }
        }
        "%" -> {
//            val number = newState.display.toDoubleOrNull() ?: 0.0
//            newState = newState.copy(display = (number/100).toString())
            val number = newDisplay.toDoubleOrNull() ?: 0.0
            newDisplay = (number / 100).toString()
        }
//        "C" -> newState = CalculatorState()
        "C" -> {
            newDisplay = "0"
            newPreviousNumber = null
            newCurrentOperator = null
            newInputFlag = true
        }
    }
//    updateState(newState)
    updateState(newDisplay, newPreviousNumber, newCurrentOperator, newInputFlag)
}

fun calculate(a: Double, b: Double, operator: String): Double{
    return when (operator){
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> if (b != 0.0) a / b else 0.0
        else -> b
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {

}

//data class CalculatorState(
//    val display: String = "0",
//    val previousNumber: Double? = null,
//    val currentOperator: String? = null,
//    val isNewNumber: Boolean = true
//)
