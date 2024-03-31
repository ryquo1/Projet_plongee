package com.example.projet_plongee.base.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Periode (
    @PrimaryKey(autoGenerate = false) var id: Long = 0,
    var label: String,
    var dateDebut: String,
    var dateFin: String
)