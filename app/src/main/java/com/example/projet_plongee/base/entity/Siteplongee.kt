package com.example.projet_plongee.base.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Siteplongee (
    @PrimaryKey(autoGenerate = false) var id: Long = 0,
    var nom: String,
    var coordonne: String,
    var profondeur: String,
    var description: String,
)