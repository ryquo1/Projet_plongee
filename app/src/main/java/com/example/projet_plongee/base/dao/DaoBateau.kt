package com.example.projet_plongee.base.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projet_plongee.base.entity.Bateau

@Dao
interface DaoBateau {
    @Insert
    fun insert(vararg bateau: Bateau)

    @Insert
    fun insertOne(bateau: Bateau): Long

    @Update
    fun update(vararg bateau: Bateau)

    @Delete
    fun delete(vararg bateau: Bateau)

    @Query("Select * FROM Bateau")
    fun getAllBateau(): LiveData<List<Bateau>>

    @Query("Select nom FROM Bateau")
    fun getNom(): List<String>
}