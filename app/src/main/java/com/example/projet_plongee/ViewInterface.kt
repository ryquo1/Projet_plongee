package com.example.projet_plongee

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projet_plongee.base.entity.Plongee
import com.example.projet_plongee.base.view.BateauView
import com.example.projet_plongee.base.view.MembreView
import com.example.projet_plongee.base.view.PeriodeView
import com.example.projet_plongee.base.view.PerogativeView
import com.example.projet_plongee.base.view.PlongeeView
import com.example.projet_plongee.base.view.SiteView
import kotlin.concurrent.thread

class ViewInterface : AppCompatActivity() {
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
        initialiserIntreface(
            spinnerPeriod,
            spinnerSite,
            spinnerBateau,
            spinnerNiveau,
            spinnerDirecteur,
            spinnerpilote,
            spinnersecurite
        )


        val Changebouton = findViewById<Button>(R.id.button2)
        Changebouton.setOnClickListener {
            val ancienIntent = Intent(this, ViewInterface::class.java)
            val nouveauIntent = Intent(this, MainActivity::class.java)
            startActivity(nouveauIntent)
            stopService(ancienIntent)
        }

        val CreateBouton = findViewById<Button>(R.id.button)
        CreateBouton.setOnClickListener {
            val date = findViewById<EditText>(R.id.editTextDate).text.toString()
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

            CreateBouton(
                date,
                period,
                site,
                bateau,
                niveau,
                directeur,
                pilote,
                securite,
                effectifMin,
                effectifMax
            )
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

    private fun CreateBouton(
        date: String,
        period: String,
        site: String,
        bateau: String,
        niveau: String,
        directeur: String,
        pilote: String,
        securite: String,
        effectifMin: Int,
        effectifMax: Int,
    ) {

        thread {
        val id: Long = 0
        val numPeriod = periodView.BDD.PeriodeDAO().getIdperiod(period)
        val numSite = siteView.BDD.SiteDAO().getIdSite(site)
        val numBateau = bateauView.BDD.BateauDAO().getIdBateau(bateau)
        val numNiveau = niveauView.BDD.PerogativeDAO().getIdNiveau(niveau)
        val numDirecteur = directeurView.BDD.MembreDAO().getIdMembre(directeur)
        val numPilote = piloteView.BDD.MembreDAO().getIdMembre(pilote)
        val numSecurite = securiteView.BDD.MembreDAO().getIdMembre(securite)
        val observation: String = " "

            val nouvellePlongee = Plongee(
                id,
                numBateau,
                numSecurite,
                numDirecteur,
                numSite,
                numSite,
                numNiveau,
                numPeriod,
                date,
                effectifMin,
                effectifMax,
                observation
            )
            plongeeView.BDD.PlongeeDAO().insert(nouvellePlongee)
        }

    }


    private fun initialiserIntreface(
        spinnerPeriod: Spinner,
        spinnerSite: Spinner,
        spinnerBateau: Spinner,
        spinnerNiveau: Spinner,
        spinnerDirecteur: Spinner,
        spinnerpilote: Spinner,
        spinnersecurite: Spinner,
    ) {
        Thread {

            val list: List<String> = periodView.BDD.PeriodeDAO().getLabel()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerPeriod.adapter = adapter
        }.start()

        Thread {

            val list: List<String> = siteView.BDD.SiteDAO().getNom()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerSite.adapter = adapter
        }.start()

        Thread {

            val list: List<String> = bateauView.BDD.BateauDAO().getNom()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerBateau.adapter = adapter
        }.start()

        Thread {

            val list: List<String> = niveauView.BDD.PerogativeDAO().getNiveau()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerNiveau.adapter = adapter
        }.start()

        Thread {

            var list: List<String> = directeurView.BDD.MembreDAO().getNomResponsable()
            if (list.isEmpty()) list = listOf(" ")
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerDirecteur.adapter = adapter
        }.start()

        Thread {

            val list: List<String> = piloteView.BDD.MembreDAO().getNomPilote()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerpilote.adapter = adapter
        }.start()

        Thread {
            val list: List<String> = securiteView.BDD.MembreDAO().getNomSecurite()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnersecurite.adapter = adapter
        }.start()
    }


}