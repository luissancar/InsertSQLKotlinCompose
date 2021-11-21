package com.example.insertsqlkotlincompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insertsqlkotlincompose.ui.theme.InsertSQLKotlinComposeTheme
import kotlinx.coroutines.*
import org.intellij.lang.annotations.JdkConstants
import java.io.IOException
import java.net.URL
import androidx.compose.runtime.rememberCoroutineScope as rememberCoroutineScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InsertSQLKotlinComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {


                        Greeting()


                }
            }
        }
    }
}




@Composable
fun Greeting() {
    var textFieldValueMarca by rememberSaveable { mutableStateOf("") }
    var textFieldValueModelo by rememberSaveable { mutableStateOf("") }
    var textFieldValuePrecio by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()


    ) {
        Text(
            text = "SQL INSERT",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.Green,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )


        TextField(
            value = textFieldValueMarca,
            onValueChange = { nuevo ->
                textFieldValueMarca = nuevo
            },
            label = {
                Text(text = "Introducir marca")
            },
            modifier = Modifier
                .padding(10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(textAlign = TextAlign.Right)
        )


        TextField(
            value = textFieldValueModelo,
            onValueChange = { nuevo ->
                textFieldValueModelo = nuevo
            },
            label = {
                Text(text = "Introducir modelo")
            },
            modifier = Modifier
                .padding( 10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(textAlign = TextAlign.Right)
        )


        TextField(
            value = textFieldValuePrecio,
            onValueChange = { nuevo ->
                textFieldValuePrecio = nuevo
            },
            label = {
                Text(text = "Introducir modelo")
            },
            modifier = Modifier
                .padding( 10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(textAlign = TextAlign.Right)
        )

        Spacer(Modifier.height(20.dp) )


        Button(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(width = 100.dp, height = 50.dp)
                ,


            onClick = {
                insertar(textFieldValueMarca,textFieldValueModelo,textFieldValuePrecio)
                textFieldValueMarca=""
                textFieldValueModelo=""
                textFieldValuePrecio=""
            }
        ){
            Text(text = "Insert"
            )
        }


    }
   
}



 fun insertar(marca:String,modelo:String,precio:String){

    val url = "http://iesayala.ddns.net/json/insertguitar.php/?marca=$marca&modelo=$modelo&precio=$precio"

        leerUrl(url)

}


 fun leerUrl(urlString:String){
    GlobalScope.launch(Dispatchers.IO)   {
         val response = try {
             URL(urlString)
                 .openStream()
                 .bufferedReader()
                 .use { it.readText() }
         } catch (e: IOException) {
             "Error with ${e.message}."
             Log.d("io", e.message.toString())
         } catch (e: Exception) {
             "Error with ${e.message}."
             Log.d("io", e.message.toString())
         }
     }

    return
}