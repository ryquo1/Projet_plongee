package com.example.projet_plongee.base.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projet_plongee.base.entity.Plongee

@Dao
interface DaoPlongee {
    @Insert
    fun insert(vararg plongee: Plongee)

    @Insert
    fun insertOne(plongee: Plongee): Long

    @Update
    fun update(vararg plongee: Plongee)

    @Delete
    fun delete(vararg plongee: Plongee)

    @Query("Select * FROM Plongee")
    fun getAllplongee(): LiveData<List<Plongee>>

}