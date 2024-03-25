package com.example.projet_plongee.base.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bateau (
    @PrimaryKey(autoGenerate = false) var id: Long = 0,
    var nom: String,
    var capacite: Int,
)