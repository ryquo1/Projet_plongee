package com.example.projet_plongee.Base

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity
data class Periode (
    @PrimaryKey(autoGenerate = false) var id: Long = 0,
    var label: String,
    var dateDebut: Time,
    var dateFin: Time
)