package com.example.projet_plongee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projet_plongee.Api.Requete
import com.example.projet_plongee.ui.theme.Projet_plongeeTheme
import com.example.projet_plongee.Api.Gestion
import com.example.projet_plongee.base.entity.Perogative
import com.example.projet_plongee.base.view.BateauView
import com.example.projet_plongee.base.view.PeriodeView
import com.example.projet_plongee.base.view.PerogativeView
import com.example.projet_plongee.base.view.SiteView

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
                    val requete: Requete by viewModels()
                    val viewBateau : BateauView by viewModels()
                    val viewPeriod : PeriodeView by viewModels()
                    val viewPrerogative : PerogativeView by viewModels()
                    val viewSite : SiteView by viewModels()
                    val ApiToBdd : Gestion = Gestion()
                    ApiToBdd.GetbateauToBdd(requete, viewBateau)
                    ApiToBdd.GetPeriodToBdd(requete, viewPeriod)
                    ApiToBdd.GetPrerogativeToBdd(requete, viewPrerogative)
                    ApiToBdd.GetSiteToBdd(requete , viewSite )
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Projet_plongeeTheme {
        Greeting("Android")
    }
}