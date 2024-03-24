package com.example.projet_plongee.Base

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Plongee (
    @PrimaryKey(autoGenerate = false) var id: Long,
    var numeroBateau: Int,
    var numMembreSecuritee: Int,
    var numMembreDirecteur: Int,
    var numSite: Int,
    var numMembrePilote: Int,
    var numPrerogativ: Int,
    var numPeriode: Int,
    var date: Date,
    var minimumEnregistre: Int,
    var maximumEnregistre: Int,
    var observation: String,
)//Rajoutee les clés étrangères