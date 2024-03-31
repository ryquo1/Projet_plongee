package com.example.projet_plongee.api

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.livedata.observeAsState
import org.json.JSONObject
import androidx.compose.runtime.Composable
import com.example.projet_plongee.base.entity.Bateau
import com.example.projet_plongee.base.entity.Membre
import com.example.projet_plongee.base.entity.Periode
import com.example.projet_plongee.base.entity.Perogative
import com.example.projet_plongee.base.entity.Siteplongee
import com.example.projet_plongee.base.view.BateauView
import com.example.projet_plongee.base.view.MembreView
import com.example.projet_plongee.base.view.PeriodeView
import com.example.projet_plongee.base.view.PerogativeView
import com.example.projet_plongee.base.view.SiteView

class Gestion {

    @Composable
    fun GetbateauToBdd(requete: Requete, viewBateau: BateauView) {
        val state = requete.getBoatResult().observeAsState()
        requete.getAllBoat()
        if (state.value == null) {
            CircularProgressIndicator()
        } else {
            Column {
                val json = JSONObject(state.value!!)
                val array = json.getJSONArray("data")
                Thread {
                    for (i in 0..<array.length()) {
                        val json2 = JSONObject(array.getJSONObject(i).getString("attributes"))
                        val nouveauBateau = Bateau(
                            i.toLong(),
                            json2.getString("name"),
                            json2.getString("capacity").toInt(),
                        )
                        viewBateau.bdd.bateauDAO().delete(nouveauBateau) //a changer
                        viewBateau.bdd.bateauDAO().insert(nouveauBateau)
                    }
                }.start()
            }
        }
    }


    @Composable
    fun GetPeriodToBdd(requete: Requete, viewPeriod: PeriodeView) {
        val state = requete.getPeriodResult().observeAsState()
        requete.getAllPeriod()
        if (state.value == null) {
            CircularProgressIndicator()
        } else {
            Column {
                val json = JSONObject(state.value!!)
                val array = json.getJSONArray("data")
                Thread {
                    for (i in 0..<array.length()) {
                        val json2 = JSONObject(array.getJSONObject(i).getString("attributes"))
                        val nouvellePeriod = Periode(
                            i.toLong(),
                            json2.getString("label"),
                            json2.getString("start_time"),
                            json2.getString("end_time"),
                        )
                        viewPeriod.bdd.periodeDAO().delete(nouvellePeriod) //a changer
                        viewPeriod.bdd.periodeDAO().insert(nouvellePeriod)
                    }
                }.start()
            }
        }
    }

    @Composable
    fun GetPrerogativeToBdd(requete: Requete, viewPerogative: PerogativeView) {
        val state = requete.getPrerogativeResult().observeAsState()
        requete.getAllPrerogative()
        if (state.value == null) {
            CircularProgressIndicator()
        } else {
            Column {
                val json = JSONObject(state.value!!)
                val array = json.getJSONArray("data")
                Thread {
                    for (i in 0..<array.length()) {
                        val json2 = JSONObject(array.getJSONObject(i).getString("attributes"))
                        val nouvellePrerogative = Perogative(
                            i.toLong(),
                            json2.getString("level"),
                            json2.getString("label"),
                            json2.getString("priority").toInt(),
                        )
                        viewPerogative.bdd.perogativeDAO().delete(nouvellePrerogative) //a changer
                        viewPerogative.bdd.perogativeDAO().insert(nouvellePrerogative)
                    }
                }.start()
            }
        }
    }

    @Composable
    fun GetSiteToBdd(requete: Requete, viewSite: SiteView) {
        val state = requete.getSiteResult().observeAsState()
        requete.getAllSite()
        if (state.value == null) {
            CircularProgressIndicator()
        } else {
            Column {
                val json = JSONObject(state.value!!)
                val array = json.getJSONArray("data")
                Thread {
                    for (i in 0..<array.length()) {
                        val json2 = JSONObject(array.getJSONObject(i).getString("attributes"))
                        val nouveauSite = Siteplongee(
                            i.toLong(),
                            json2.getString("name"),
                            json2.getString("coord"),
                            json2.getString("depth"),
                            json2.getString("description"),
                        )
                        viewSite.bdd.siteDAO().delete(nouveauSite) //a changer
                        viewSite.bdd.siteDAO().insert(nouveauSite)
                    }
                }.start()
            }
        }
    }

    @Composable
    fun GetMembreToBdd(requete: Requete, viewMembre: MembreView) {
        val state = requete.getMembreResult().observeAsState()
        var res = 0
        requete.getAllMembre()
        if (state.value == null) {
            CircularProgressIndicator()
        } else {
            val json = JSONObject(state.value!!)
            val array = json.getJSONArray("data")
            Thread {
                for (i in 0..<array.length()) {
                    val json2 = JSONObject(array.getJSONObject(i).getString("attributes"))
                    val array2 = json2.getJSONArray("functions")
                    for (j in 0..<array2.length()) {
                        val json3 = JSONObject(array2.getJSONObject(j).getString("pivot"))
                        res = json3.get("NUM_FUNCTION").toString().toInt()
                    }

                    val nouveauMembre = Membre(
                        i.toLong(),
                        json2.getString("licence"),
                        json2.getString("name"),
                        json2.getString("surname"),
                        json2.getString("date_certification"),
                        json2.getString("pricing"),
                        res,
                        json2.getString("remaining_dives").toInt(),
                        json2.getString("subdate"),
                    )
                    viewMembre.bdd.membreDAO().delete(nouveauMembre) //a changer
                    viewMembre.bdd.membreDAO().insert(nouveauMembre)
                }
            }.start()
        }
    }
}