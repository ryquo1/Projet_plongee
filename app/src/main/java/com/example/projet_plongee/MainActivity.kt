package com.example.projet_plongee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projet_plongee.ui.theme.Projet_plongeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projet_plongeeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun SaisieAdherent(){
    Column {
        var nom by remember { mutableStateOf("") }
        TextField(value = nom, onValueChange = {nom = it}, label = { Text("Nom") } )
        var prenom by remember { mutableStateOf("") }
        TextField(value = prenom, onValueChange = {prenom = it}, label = { Text("Prenom") } )
        var numLicence by remember { mutableStateOf("") }
        TextField(value = numLicence, onValueChange = {numLicence = it}, label = { Text("NumLicence") } )
        var motDePasse by remember { mutableStateOf("") }
        TextField(value = motDePasse, onValueChange = {motDePasse = it}, label = { Text("Label") } )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Projet_plongeeTheme {
        SaisieAdherent()
    }
}