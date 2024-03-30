package com.example.projet_plongee.base.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projet_plongee.base.entity.Siteplongee

@Dao
interface DaoSite {
    @Insert
    fun insert(vararg site: Siteplongee)

    @Insert
    fun insertOne(site: Siteplongee): Long

    @Update
    fun update(vararg site: Siteplongee)

    @Delete
    fun delete(vararg site: Siteplongee)

    @Query("Select * FROM Siteplongee")
    fun getAllSite(): LiveData<List<Siteplongee>>

    @Query("Select nom FROM Siteplongee")
    fun getNom(): List<String>
}