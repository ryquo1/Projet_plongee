package com.example.projet_plongee

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projet_plongee.api.Requete
import com.example.projet_plongee.base.entity.Plongee
import com.example.projet_plongee.base.view.BateauView
import com.example.projet_plongee.base.view.MembreView
import com.example.projet_plongee.base.view.PeriodeView
import com.example.projet_plongee.base.view.PerogativeView
import com.example.projet_plongee.base.view.PlongeeView
import com.example.projet_plongee.base.view.SiteView
import kotlin.concurrent.thread

class ViewInterface : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_interface)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainView)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
        val spinnerPeriod = findViewById<Spinner>(R.id.PeriodSpinner)
        val spinnerSite = findViewById<Spinner>(R.id.SiteSpinner)
        val spinnerBateau = findViewById<Spinner>(R.id.BateauSpinner)
        val spinnerNiveau = findViewById<Spinner>(R.id.NiveauSpinner)
        val spinnerDirecteur = findViewById<Spinner>(R.id.DirecteurSpinner)
        val spinnerpilote = findViewById<Spinner>(R.id.PiloteSpinner)
        val spinnersecurite = findViewById<Spinner>(R.id.SecuriteSpinner)

        initialiserIntreface(spinnerPeriod, spinnerSite, spinnerBateau, spinnerNiveau, spinnerDirecteur, spinnerpilote, spinnersecurite)

        val changebouton = findViewById<Button>(R.id.button2)
        changebouton.setOnClickListener {
            val ancienIntent = Intent(this, ViewInterface::class.java)
            val nouveauIntent = Intent(this, MainActivity::class.java)
            startActivity(nouveauIntent)
            stopService(ancienIntent)
        }

        val createBouton = findViewById<Button>(R.id.button)
        createBouton.setOnClickListener {
            val date = findViewById<EditText>(R.id.editTextjour).text.toString()
            val period = spinnerPeriod.selectedItem.toString()
            val site = spinnerSite.selectedItem.toString()
            val bateau = spinnerBateau.selectedItem.toString()
            val niveau = spinnerNiveau.selectedItem.toString()
            val directeur = spinnerDirecteur.selectedItem.toString()
            val pilote = spinnerpilote.selectedItem.toString()
            val securite = spinnersecurite.selectedItem.toString()
            val effectifMin =
                findViewById<TextView>(R.id.editTextEffectifMin).text.toString().toInt()
            val effectifMax =
                findViewById<TextView>(R.id.editTextEffectifMax).text.toString().toInt()

            createBouton(date, period, site, bateau, niveau, directeur, pilote, securite, effectifMin, effectifMax)
        }
    }

    private val periodView: PeriodeView by viewModels()
    private val siteView: SiteView by viewModels()
    private val bateauView: BateauView by viewModels()
    private val niveauView: PerogativeView by viewModels()
    private val directeurView: MembreView by viewModels()
    private val piloteView: MembreView by viewModels()
    private val securiteView: MembreView by viewModels()
    private val plongeeView: PlongeeView by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createBouton(date: String, period: String, site: String, bateau: String, niveau: String, directeur: String, pilote: String, securite: String, effectifMin: Int, effectifMax: Int) {

        thread {
            val requete = Requete()
            val id: Long = 0
            val numPeriod = periodView.bdd.periodeDAO().getIdperiod(period)
            val numSite = siteView.bdd.siteDAO().getIdSite(site)
            val numBateau = bateauView.bdd.bateauDAO().getIdBateau(bateau)
            val numNiveau = niveauView.bdd.perogativeDAO().getIdNiveau(niveau)
            val numDirecteur = directeurView.bdd.membreDAO().getIdMembre(directeur)
            val numPilote = piloteView.bdd.membreDAO().getIdMembre(pilote)
            val numSecurite = securiteView.bdd.membreDAO().getIdMembre(securite)
            val observation = " "

            val nouvellePlongee = Plongee(id, numBateau, numSecurite, numDirecteur, numSite, numSite, numNiveau, numPeriod, date, effectifMin, effectifMax, observation)
            plongeeView.bdd.plongeeDAO().insert(nouvellePlongee)
           /* val url = "https://dev-sae301grp5.users.info.unicaen.fr/api/dives?" +
                    "date=" + date + "&" +
                    "period=" + numPeriod + "&" +
                    "min_registered=" + effectifMin + "&" +
                    "max_registered=" + effectifMax + "&" +
                    "observation=" + observation + "&" +
                    "boat=" + numBateau + "&" +
                    "site=" + numSite + "&" +
                    "surface_security=" + numSecurite + 1 + "&" +
                    "leader=" + numDirecteur + 1 + "&" +
                    "pilot=" + numPilote + 1
            requete.postDive(url) */
            // ce bout de code ne fait pas planter mais renvoi une internal error du serveur
        }

    }

    private fun initialiserIntreface(spinnerPeriod: Spinner, spinnerSite: Spinner, spinnerBateau: Spinner, spinnerNiveau: Spinner, spinnerDirecteur: Spinner, spinnerpilote: Spinner, spinnersecurite: Spinner) {
        Thread {

            val listPeriod: List<String> = periodView.bdd.periodeDAO().getLabel()
            val adapterPeriod = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPeriod)
            adapterPeriod.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerPeriod.adapter = adapterPeriod

            val listSite: List<String> = siteView.bdd.siteDAO().getNom()
            val adapterSite = ArrayAdapter(this, android.R.layout.simple_spinner_item, listSite)
            adapterSite.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerSite.adapter = adapterSite

            val listBateau: List<String> = bateauView.bdd.bateauDAO().getNom()
            val adapterBateau = ArrayAdapter(this, android.R.layout.simple_spinner_item, listBateau)
            adapterBateau.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerBateau.adapter = adapterBateau

            val listNiveau: List<String> = niveauView.bdd.perogativeDAO().getNiveau()
            val adapterNiveau = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNiveau)
            adapterNiveau.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerNiveau.adapter = adapterNiveau

            var listDirecteur: List<String> = directeurView.bdd.membreDAO().getNomResponsable()
            if (listDirecteur.isEmpty()) listDirecteur = listOf(" ")
            val adapterDirecteur =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, listDirecteur)
            adapterDirecteur.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerDirecteur.adapter = adapterDirecteur

            val listPilote: List<String> = piloteView.bdd.membreDAO().getNomPilote()
            val adapterPilote = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPilote)
            adapterPilote.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerpilote.adapter = adapterPilote

            val listSecurite: List<String> = securiteView.bdd.membreDAO().getNomSecurite()
            val adapterSecurite =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, listSecurite)
            adapterSecurite.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnersecurite.adapter = adapterSecurite
        }.start()
    }


}