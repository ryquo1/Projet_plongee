package com.example.projet_plongee.base.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import java.util.Date

@Entity
data class Plongee (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var numeroBateau: Int,
    var numMembreSecuritee: Int,
    var numMembreDirecteur: Int,
    var numSite: Int,
    var numMembrePilote: Int,
    var numPrerogativ: Int,
    var numPeriode: Int,
    var date: String,
    var minimumEnregistre: Int,
    var maximumEnregistre: Int,
    var observation: String,
)