package com.example.projet_plongee.base.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projet_plongee.base.Base

class MembreView(application: Application) :
    AndroidViewModel(application) {
        val BDD = Base.getInstance(application)
        val dao = BDD.MembreDAO()
        val bateau = dao.getAllMembre()
}