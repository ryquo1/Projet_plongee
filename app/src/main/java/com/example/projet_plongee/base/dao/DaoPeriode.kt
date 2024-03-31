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

@Dao
interface DaoPeriode {
    @Insert
    fun insert(vararg periode: Periode)

    @Insert
    fun insertOne(periode: Periode): Long

    @Update
    fun update(vararg periode: Periode)

    @Delete
    fun delete(vararg periode: Periode)

    @Query("Select * FROM Periode")
    fun getAllPeriode(): LiveData<List<Periode>>

    @Query("Select label FROM Periode")
    fun getLabel(): List<String>

    @Query("Select id FROM Periode where label = :text")
    fun getIdperiod(text: String): Int
}