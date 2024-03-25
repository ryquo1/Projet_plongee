package com.example.projet_plongee.base.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projet_plongee.base.entity.Bateau
import com.example.projet_plongee.base.entity.Membre

@Dao
interface DaoMembre {
    @Insert
    fun insert(vararg membre: Membre)

    @Insert
    fun insertOne(membre: Membre): Long

    @Update
    fun update(vararg membre: Membre)

    @Delete
    fun delete(vararg membre: Membre)

    @Query("Select * FROM Membre")
    fun getAllMembre(): LiveData<List<Membre>>

}