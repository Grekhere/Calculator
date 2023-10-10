package com.example.mycalc

import android.os.Bundle
import net.objecthunter.exp4j.ExpressionBuilder
import androidx.activity.ComponentActivity
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycalc.ui.theme.MycalcTheme

class main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MycalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.DarkGray) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable fun CalculatorApp() {
    var calculation by remember { mutableStateOf("")
    }

    Column( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally )
    { Text( text = calculation,
        color = Color.White,
        fontSize = 34.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally) )
        Spacer(modifier = Modifier.height(15.dp))

        Column { Row { CalculatorButton( text = "1", onClick = { calculation += "1" } )
            CalculatorButton( text = "2", onClick = { calculation += "2" } )
            CalculatorButton( text = "3", onClick = { calculation += "3" } ) }
            Row { CalculatorButton( text = "4", onClick = { calculation += "4" } )
                CalculatorButton( text = "5", onClick = { calculation += "5" } )
                CalculatorButton( text = "6", onClick = { calculation += "6" } ) }
            Row { CalculatorButton( text = "7", onClick = { calculation += "7" } )
                CalculatorButton( text = "8", onClick = { calculation += "8" } )
                CalculatorButton( text = "9", onClick = { calculation += "9" } ) }
            Row (modifier = Modifier.align(Alignment.CenterHorizontally)){
                CalculatorButton(text = "0",
                    onClick = { calculation += "0" } ) } }


        Spacer(modifier = Modifier.height(15.dp))


        Row { CalculatorButton( text = "*", onClick = { calculation += "*" } )
            CalculatorButton( text = "/", onClick = { calculation += "/" } )
            CalculatorButton( text = "+", onClick = { calculation += "+" } )
            CalculatorButton( text = "-", onClick = { calculation += "-" } ) }

        Spacer(modifier = Modifier.height(15.dp))

        Button( modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color.Black), shape = RoundedCornerShape(8.dp), onClick = { calculation = evaluate(calculation) } ) { Text( text = "=", color = Color.White, fontWeight = FontWeight.Bold ) }

        Spacer(modifier = Modifier.height(15.dp))

        Button( modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color.Black), shape = RoundedCornerShape(8.dp), onClick = { calculation = "" } ) { Text( text = "Очистить", color = Color.White, fontWeight = FontWeight.Bold ) } } }

@Composable fun CalculatorButton(text: String, onClick: () -> Unit) { Button( modifier = Modifier .padding(8.dp) .size(64.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.Black), shape = RoundedCornerShape(50), onClick = onClick ) { Text( text = text, color = Color.White, fontWeight = FontWeight.Bold ) } }

fun evaluate(calculation: String): String
{
    return try { val result = ExpressionBuilder(calculation).build().evaluate().toString()
    result
}
catch (e: Exception) { "Error" } }