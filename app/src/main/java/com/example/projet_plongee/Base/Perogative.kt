package com.example.projet_plongee.Base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Perogative (
    @PrimaryKey(autoGenerate = false) var id: Long,
    var niveau: String,
    var label: String,
    var priorite: Int,
)