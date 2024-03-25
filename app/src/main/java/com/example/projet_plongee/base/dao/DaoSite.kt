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
import com.example.projet_plongee.base.entity.Plongee
import com.example.projet_plongee.base.entity.Site

@Dao
interface DaoSite {
    @Insert
    fun insert(vararg site: Site)

    @Insert
    fun insertOne(site: Site): Long

    @Update
    fun update(vararg site: Site)

    @Delete
    fun delete(vararg site: Site)

    @Query("Select * FROM Site")
    fun getAllSite(): LiveData<List<Site>>

}