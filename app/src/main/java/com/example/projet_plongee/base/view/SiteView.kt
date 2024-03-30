package com.example.projet_plongee.base.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projet_plongee.base.Base

class SiteView(application: Application) :
    AndroidViewModel(application) {
        val BDD = Base.getInstance(application)
        val dao = BDD.SiteDAO()
        val bateau = dao.getAllSite()
}