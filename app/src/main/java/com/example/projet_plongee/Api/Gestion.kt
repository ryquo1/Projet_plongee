package com.example.projet_plongee.Api

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.livedata.observeAsState
import org.json.JSONObject
import androidx.compose.runtime.Composable
import com.example.projet_plongee.base.*
import com.example.projet_plongee.base.dao.DaoBateau
import com.example.projet_plongee.base.entity.Bateau
import com.example.projet_plongee.base.entity.Periode
import com.example.projet_plongee.base.entity.Perogative
import com.example.projet_plongee.base.entity.Siteplongee
import com.example.projet_plongee.base.view.BateauView
import com.example.projet_plongee.base.view.PeriodeView
import com.example.projet_plongee.base.view.PerogativeView
import com.example.projet_plongee.base.view.SiteView

class Gestion {

@Composable
    fun GetbateauToBdd(requete: Requete, viewBateau: BateauView){
        val state = requete.getBoatResult().observeAsState()
        requete.getAllBoat()
        if (state.value == null){
            CircularProgressIndicator()
        }else{
            Column {
                val json = JSONObject(state.value!!)
                val array = json.getJSONArray("data")
                Thread {
                    for (i in 0..array.length()-1) {
                        val json2 = JSONObject(array.getJSONObject(i).getString("attributes"))
                        val nouveauBateau = Bateau(
                            i.toLong(),
                            json2.getString("name"),
                            json2.getString("capacity").toInt(),
                        )
                        viewBateau.BDD.BateauDAO().delete(nouveauBateau) //a changer
                        viewBateau.BDD.BateauDAO().insert(nouveauBateau)
                    }
                }.start()
            }
        }
    }


    @Composable
    fun GetPeriodToBdd (requete: Requete, viewPeriod: PeriodeView){
        val state = requete.getPeriodResult().observeAsState()
        requete.getAllPeriod()
        if (state.value == null){
            CircularProgressIndicator()
        }else{
            Column {
                val json = JSONObject(state.value!!)
                val array = json.getJSONArray("data")
                Thread {
                    for (i in 0..array.length()-1) {
                        val json2 = JSONObject(array.getJSONObject(i).getString("attributes"))
                        val nouvellePeriod = Periode(
                            i.toLong(),
                            json2.getString("label"),
                            json2.getString("start_time"),
                            json2.getString("end_time"),
                        )
                        viewPeriod.BDD.PeriodeDAO().delete(nouvellePeriod) //a changer
                        viewPeriod.BDD.PeriodeDAO().insert(nouvellePeriod)
                    }
                }.start()
            }
        }
    }

    @Composable
    fun GetPrerogativeToBdd (requete: Requete, viewPerogative: PerogativeView){
        val state = requete.getPrerogativeResult().observeAsState()
        requete.getAllPrerogative()
        if (state.value == null){
            CircularProgressIndicator()
        }else{
            Column {
                val json = JSONObject(state.value!!)
                val array = json.getJSONArray("data")
                Thread {
                    for (i in 0..array.length()-1) {
                        val json2 = JSONObject(array.getJSONObject(i).getString("attributes"))
                        val nouvellePrerogative = Perogative(
                            i.toLong(),
                            json2.getString("level"),
                            json2.getString("label"),
                            json2.getString("priority").toInt(),
                        )
                        viewPerogative.BDD.PerogativeDAO().delete(nouvellePrerogative) //a changer
                        viewPerogative.BDD.PerogativeDAO().insert(nouvellePrerogative)
                    }
                }.start()
            }
        }
    }

    @Composable
    fun GetSiteToBdd (requete: Requete, viewSite: SiteView){
        val state = requete.getSiteResult().observeAsState()
        requete.getAllSite()
        if (state.value == null){
            CircularProgressIndicator()
        }else{
            Column {
                val json = JSONObject(state.value!!)
                val array = json.getJSONArray("data")
                Thread {
                    for (i in 0..array.length()-1) {
                        val json2 = JSONObject(array.getJSONObject(i).getString("attributes"))
                        val nouveauSite = Siteplongee(
                            i.toLong(),
                            json2.getString("name"),
                            json2.getString("coord"),
                            json2.getString("depth"),
                            json2.getString("description"),
                        )
                        viewSite.BDD.SiteDAO().delete(nouveauSite) //a changer
                        viewSite.BDD.SiteDAO().insert(nouveauSite)
                    }
                }.start()
            }
        }
    }

}