package com.example.projet_plongee

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import com.example.projet_plongee.base.*
import com.example.projet_plongee.base.entity.Periode
import com.example.projet_plongee.base.entity.Siteplongee
import com.example.projet_plongee.base.view.BateauView
import com.example.projet_plongee.base.view.MembreView
import com.example.projet_plongee.base.view.PeriodeView
import com.example.projet_plongee.base.view.PerogativeView
import com.example.projet_plongee.base.view.SiteView
import kotlin.math.log

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

        Thread{
            val period : PeriodeView by viewModels()
            val spinnerPeriod = findViewById<Spinner>(R.id.PeriodSpinner)
            val list : List<String> = period.BDD.PeriodeDAO().getLabel()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerPeriod.adapter = adapter
        }.start()

        Thread{
            val site : SiteView by viewModels()
            val spinnerSite = findViewById<Spinner>(R.id.SiteSpinner)
            val list : List<String> = site.BDD.SiteDAO().getNom()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerSite.adapter = adapter
        }.start()

        Thread{
            val bateau : BateauView by viewModels()
            val spinnerBateau = findViewById<Spinner>(R.id.BateauSpinner)
            val list : List<String> = bateau.BDD.BateauDAO().getNom()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerBateau.adapter = adapter
        }.start()

        Thread{
            val niveau : PerogativeView by viewModels()
            val spinnerNiveau = findViewById<Spinner>(R.id.NiveauSpinner)
            val list : List<String> = niveau.BDD.PerogativeDAO().getNiveau()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerNiveau.adapter = adapter
        }.start()

       Thread{
            val directeur : MembreView by viewModels()
            val spinnerDirecteur = findViewById<Spinner>(R.id.DirecteurSpinner)
            val list : List<String> = directeur.BDD.MembreDAO().getNomResponsable()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerDirecteur.adapter = adapter
        }.start()

        Thread{
            val pilote : MembreView by viewModels()
            val spinnerpilote = findViewById<Spinner>(R.id.PiloteSpinner)
            val list : List<String> = pilote.BDD.MembreDAO().getNomPilote()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerpilote.adapter = adapter
        }.start()

        Thread{
            val securite : MembreView by viewModels()
            val spinnersecurite = findViewById<Spinner>(R.id.SecuriteSpinner)
            val list : List<String> = securite.BDD.MembreDAO().getNomSecurite()
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnersecurite.adapter = adapter
        }.start()


        val bouton = findViewById<Button>(R.id.button2)
        bouton.setOnClickListener{
            val ancienIntent = Intent(this, ViewInterface::class.java)
            val nouveauIntent = Intent(this, MainActivity::class.java)
            startActivity(nouveauIntent)
            stopService(ancienIntent)
        }
    }
}