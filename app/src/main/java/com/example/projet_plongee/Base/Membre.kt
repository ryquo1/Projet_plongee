package com.example.projet_plongee.Base

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Membre (
    @PrimaryKey(autoGenerate = false) var id: Long =0,
    var numLicence: String,
    var nom: String,
    var prenom: String,
    var dateCertificat: Date,
    var prix: String,
    var status: Int,
    var nombrePlongee: Int,
    var motDePasse: String,
)