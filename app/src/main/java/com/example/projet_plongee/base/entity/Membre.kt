package com.example.projet_plongee.base.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Membre (
    @PrimaryKey(autoGenerate = true) var id: Long =0,
    var numLicence: String,
    var nom: String,
    var prenom: String,
    var dateCertificat: String,
    var prix: String,
    var status: Int,
    var nombrePlongee: Int,
    var motDePasse: String,
)