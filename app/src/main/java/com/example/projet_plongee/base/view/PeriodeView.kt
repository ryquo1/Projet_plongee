package com.example.projet_plongee.base.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projet_plongee.base.Base

class PeriodeView(application: Application) :
    AndroidViewModel(application) {
        val bdd = Base.getInstance(application)
        val dao = bdd.periodeDAO()
        val bateau = dao.getAllPeriode()
}