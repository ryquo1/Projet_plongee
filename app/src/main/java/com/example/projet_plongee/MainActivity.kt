package com.example.projet_plongee

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
import com.example.projet_plongee.Api.Requete
import com.example.projet_plongee.ui.theme.Projet_plongeeTheme
import com.example.projet_plongee.Api.Gestion
import com.example.projet_plongee.base.entity.Perogative
import com.example.projet_plongee.base.view.BateauView
import com.example.projet_plongee.base.view.MembreView
import com.example.projet_plongee.base.view.PeriodeView
import com.example.projet_plongee.base.view.PerogativeView
import com.example.projet_plongee.base.view.SiteView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projet_plongeeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /**
                     * Mise en place de la base de donnée
                     */
                    val requete: Requete by viewModels()
                    val viewBateau: BateauView by viewModels()
                    val viewPeriod: PeriodeView by viewModels()
                    val viewPrerogative: PerogativeView by viewModels()
                    val viewSite: SiteView by viewModels()
                    val viemMembre: MembreView by viewModels()
                    val ApiToBdd: Gestion = Gestion()
                    ApiToBdd.GetbateauToBdd(requete, viewBateau)
                    ApiToBdd.GetPeriodToBdd(requete, viewPeriod)
                    ApiToBdd.GetPrerogativeToBdd(requete, viewPrerogative)
                    ApiToBdd.GetSiteToBdd(requete, viewSite)
                    ApiToBdd.GetMembreToBdd(requete , viemMembre)

                }
                /**
                 * Bouton pour changer de page
                 **/
                Button(onClick = {
                    val nouveauIntent = Intent(this, ViewInterface::class.java)
                    startActivity(nouveauIntent)
                }) {
                    Text(text = "changer de vue")
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