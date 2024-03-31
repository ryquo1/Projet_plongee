package com.example.projet_plongee

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.projet_plongee.api.Requete
import com.example.projet_plongee.ui.theme.Projet_plongeeTheme
import com.example.projet_plongee.api.Gestion
import com.example.projet_plongee.base.entity.Membre
import com.example.projet_plongee.base.view.BateauView
import com.example.projet_plongee.base.view.MembreView
import com.example.projet_plongee.base.view.PeriodeView
import com.example.projet_plongee.base.view.PerogativeView
import com.example.projet_plongee.base.view.SiteView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projet_plongeeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    /**
                     * Mise en place de la base de donnée
                     */
                    val requete: Requete by viewModels()
                    val viewBateau: BateauView by viewModels()
                    val viewPeriod: PeriodeView by viewModels()
                    val viewPrerogative: PerogativeView by viewModels()
                    val viewSite: SiteView by viewModels()
                    val viewMembre: MembreView by viewModels()
                    val apiToBdd = Gestion()
                    apiToBdd.GetbateauToBdd(requete, viewBateau)
                    apiToBdd.GetPeriodToBdd(requete, viewPeriod)
                    apiToBdd.GetPrerogativeToBdd(requete, viewPrerogative)
                    apiToBdd.GetSiteToBdd(requete, viewSite)
                    apiToBdd.GetMembreToBdd(requete, viewMembre)

                    /**
                     * Bouton pour changer de page
                     **/
                    val nouveauIntent = Intent(this, ViewInterface::class.java)

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.background(Color.LightGray)
                    ) {
                        Text(text = "Saisie Adherent", fontSize = 35.sp, modifier = Modifier.padding(bottom = 10.dp))
                        SaisieAdherent(viewMembre)
                        Button(onClick = {
                            startActivity(nouveauIntent)
                        }) {
                            Text(text = "changer de vue")
                        }

                    }
                }
            }
        }
    }

    @Composable
    private fun SaisieAdherent(viewMembre: MembreView) {
        var numLicence by remember { mutableStateOf("") }
        val maxChar = 12
        TextField(value = numLicence,
            onValueChange = { if (it.length <= maxChar) numLicence = it },
            label = { Text("NumLicence") })

        Spacer(modifier = Modifier.padding(Dp(5f)))
        var nom by remember { mutableStateOf("") }
        TextField(value = nom, onValueChange = { nom = it }, label = { Text("Nom") })

        Spacer(modifier = Modifier.padding(Dp(5f)))
        var prenom by remember { mutableStateOf("") }
        TextField(value = prenom, onValueChange = { prenom = it }, label = { Text("Prenom") })

        Spacer(modifier = Modifier.padding(Dp(5f)))
        var motDePasse by rememberSaveable { mutableStateOf("") }
        var motDePasseVisible by rememberSaveable { mutableStateOf(false) }
        TextField(value = motDePasse,
            onValueChange = { motDePasse = it },
            label = { Text("Mot De Passe") },
            visualTransformation = if (motDePasseVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {

                IconButton(onClick = { motDePasseVisible = !motDePasseVisible }) {
                    if (motDePasseVisible) Icon(
                        painter = painterResource(id = R.drawable.visibility),
                        contentDescription = null
                    )
                    else Icon(
                        painter = painterResource(id = R.drawable.visibility_off),
                        contentDescription = null
                    )
                }
            })
        Spacer(modifier = Modifier.padding(Dp(5f)))
        var date by remember {
            mutableStateOf("")
        }
        var showDatePicker by remember {
            mutableStateOf(false)
        }
        TextField(value = date,
            onValueChange = { date = it },
            label = { Text("Date de Certificat") },
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = null
                    )
                }

                if (showDatePicker) {
                    MyDatePickerDialog(onDateSelected = { date = it },
                        onDismiss = { showDatePicker = false })
                }
            })
        Spacer(modifier = Modifier.padding(Dp(5f)))
        var selectedStatus by remember {
            mutableStateOf("")
        }
        var showStatusPicker by remember {
            mutableStateOf(false)
        }
        val selectedOption = remember { mutableStateOf("") }
        TextField(value = selectedStatus,
            onValueChange = { selectedStatus = it },
            label = { Text("Statut") },
            trailingIcon = {
                IconButton(onClick = { showStatusPicker = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.person), contentDescription = null
                    )
                }

                if (showStatusPicker) {
                    Dialog(onDismissRequest = { showStatusPicker = false }) {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(Dp(15f))
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(selected = selectedOption.value == "adulte", onClick = {
                                    selectedOption.value = "adulte"
                                    selectedStatus = "adulte"
                                    showStatusPicker = false
                                })
                                Text("adulte")
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(selected = selectedOption.value == "enfant", onClick = {
                                    selectedOption.value = "enfant"
                                    selectedStatus = "enfant"
                                    showStatusPicker = false
                                })
                                Text("enfant")
                            }
                        }
                    }
                }
            })
        Spacer(modifier = Modifier.padding(Dp(5f)))
        var actif by remember { mutableStateOf("") }
        TextField(value = actif,
            onValueChange = { actif = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            label = { Text("Actif ?") },
            placeholder = { Text("0 = non, 1 = oui") })
        Spacer(modifier = Modifier.padding(Dp(5f)))
        var nombrePlongee by remember { mutableStateOf("") }
        TextField(value = nombrePlongee,
            onValueChange = { nombrePlongee = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            label = { Text("Nombre de plongée") })
        Spacer(modifier = Modifier.padding(Dp(5f)))
        val checkValeurs =
            numLicence != "" && nom != "" && prenom != "" && motDePasse != "" && date != "" && selectedStatus != "" && actif != "" && nombrePlongee != ""

        Button(enabled = checkValeurs, onClick = {
            Thread {
                val nouveauMembre = Membre(
                    numLicence = numLicence,
                    nom = nom,
                    prenom = prenom,
                    motDePasse = motDePasse,
                    dateCertificat = date,
                    status = actif.toInt(),
                    prix = selectedStatus,
                    nombrePlongee = nombrePlongee.toInt()
                )
                viewMembre.bdd.membreDAO().insert(nouveauMembre)
                numLicence = ""
                nom = ""
                prenom = ""
                motDePasse = ""
                date = ""
                actif = ""
                selectedStatus = ""
                nombrePlongee = ""
            }.start()
        }) {
            Text(text = "Créer la nouvelle personne")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onDateSelected: (String) -> Unit, onDismiss: () -> Unit,
) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    DatePickerDialog(onDismissRequest = { onDismiss() }, confirmButton = {
        Button(onClick = {
            onDateSelected(selectedDate)
            onDismiss()
        }

        ) {
            Text(text = "OK")
        }
    }, dismissButton = {
        Button(onClick = {
            onDismiss()
        }) {
            Text(text = "Cancel")
        }
    }) {
        DatePicker(
            state = datePickerState
        )
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("FR_fr"))
    return formatter.format(Date(millis))
}

