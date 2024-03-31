package com.example.projet_plongee.Api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Requete : ViewModel() {

    private val liveDataBoat: MutableLiveData<String> = MutableLiveData<String>()

    fun getBoatResult(): LiveData<String> = liveDataBoat
    fun getAllBoat(): LiveData<String> {
        Thread {
            val urlRequest = URL("https://dev-sae301grp5.users.info.unicaen.fr/api/boats")
            try {
                val conn = urlRequest.openConnection() as HttpsURLConnection
                conn.connect()
                if (conn.responseCode != 200) {
                    liveDataBoat.postValue("Http error in connection (error code : ${conn.responseCode})")
                    return@Thread
                }
                val flux: BufferedReader = conn.inputStream.bufferedReader()
                liveDataBoat.postValue(flux.readText())
            } catch (e: Exception) {
                liveDataBoat.postValue("Network error during process ($e)")
                return@Thread
            }
        }.start()
        return liveDataBoat
    }


    private val liveDataSite: MutableLiveData<String> = MutableLiveData<String>()

    fun getSiteResult(): LiveData<String> = liveDataSite
    fun getAllSite(): LiveData<String> {
        Thread {
            val urlRequest = URL("https://dev-sae301grp5.users.info.unicaen.fr/api/sites")
            try {
                val conn = urlRequest.openConnection() as HttpsURLConnection
                conn.connect()
                if (conn.responseCode != 200) {
                    liveDataSite.postValue("Http error in connection (error code : ${conn.responseCode})")
                    return@Thread
                }
                val flux: BufferedReader = conn.inputStream.bufferedReader()
                liveDataSite.postValue(flux.readText())
            } catch (e: Exception) {
                liveDataSite.postValue("Network error during process ($e)")
                return@Thread
            }
        }.start()
        return liveDataSite
    }

    private val liveDataPrerogative: MutableLiveData<String> = MutableLiveData<String>()

    fun getPrerogativeResult(): LiveData<String> = liveDataPrerogative
    fun getAllPrerogative(): LiveData<String> {
        Thread {
            val urlRequest = URL("https://dev-sae301grp5.users.info.unicaen.fr/api/prerogatives")
            try {
                val conn = urlRequest.openConnection() as HttpsURLConnection
                conn.connect()
                if (conn.responseCode != 200) {
                    liveDataPrerogative.postValue("Http error in connection (error code : ${conn.responseCode})")
                    return@Thread
                }
                val flux: BufferedReader = conn.inputStream.bufferedReader()
                liveDataPrerogative.postValue(flux.readText())
            } catch (e: Exception) {
                liveDataPrerogative.postValue("Network error during process ($e)")
                return@Thread
            }
        }.start()
        return liveDataPrerogative
    }

    private val liveDataPeriod: MutableLiveData<String> = MutableLiveData<String>()

    fun getPeriodResult(): LiveData<String> = liveDataPeriod
    fun getAllPeriod(): LiveData<String> {
        Thread {
            val urlRequest = URL("https://dev-sae301grp5.users.info.unicaen.fr/api/periods")
            try {
                val conn = urlRequest.openConnection() as HttpsURLConnection
                conn.connect()
                if (conn.responseCode != 200) {
                    liveDataPeriod.postValue("Http error in connection (error code : ${conn.responseCode})")
                    return@Thread
                }
                val flux: BufferedReader = conn.inputStream.bufferedReader()
                liveDataPeriod.postValue(flux.readText())
            } catch (e: Exception) {
                liveDataPeriod.postValue("Network error during process ($e)")
                return@Thread
            }
        }.start()
        return liveDataPeriod
    }

    private val liveDataMembre: MutableLiveData<String> = MutableLiveData<String>()

    fun getMembreResult(): LiveData<String> = liveDataMembre
    fun getAllMembre(): LiveData<String> {
        Thread {
            val urlRequest = URL("https://dev-sae301grp5.users.info.unicaen.fr/api/members")
            try {
                val conn = urlRequest.openConnection() as HttpsURLConnection
                conn.connect()
                if (conn.responseCode != 200) {
                    liveDataMembre.postValue("Http error in connection (error code : ${conn.responseCode})")
                    return@Thread
                }
                val flux: BufferedReader = conn.inputStream.bufferedReader()
                liveDataMembre.postValue(flux.readText())
            } catch (e: Exception) {
                liveDataMembre.postValue("Network error during process ($e)")
                return@Thread
            }
        }.start()
        return liveDataMembre
    }


    fun postDive(requete: String) {
        Thread {
            val urlRequest = URL(requete)
            try {
                val conn = urlRequest.openConnection() as HttpsURLConnection
                conn.requestMethod = "POST"
                conn.connect()
                if (conn.responseCode != 200) {
                    Log.d("REQUETEPOST", conn.responseMessage)
                    return@Thread
                }
            } catch (e: Exception) {
                Log.d("ERRORREQUETE", e.message.toString())
                return@Thread
            }
        }.start()
    }
}