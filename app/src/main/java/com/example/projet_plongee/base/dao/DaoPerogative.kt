package com.example.projet_plongee.base.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projet_plongee.base.entity.Bateau
import com.example.projet_plongee.base.entity.Membre
import com.example.projet_plongee.base.entity.Periode
import com.example.projet_plongee.base.entity.Perogative

@Dao
interface DaoPerogative {
    @Insert
    fun insert(vararg perogative: Perogative)

    @Insert
    fun insertOne(perogative: Perogative): Long

    @Update
    fun update(vararg perogative: Perogative)

    @Delete
    fun delete(vararg perogative: Perogative)

    @Query("Select * FROM Perogative")
    fun getAllPerogative(): LiveData<List<Perogative>>


    @Query("Select niveau from Perogative")
    fun getNiveau(): List<String>

    @Query("Select id FROM PEROGATIVE where niveau = :text")
    fun getIdNiveau(text: String): Int
}