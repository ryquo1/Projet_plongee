package com.example.projet_plongee.base

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projet_plongee.base.dao.DaoBateau
import com.example.projet_plongee.base.dao.DaoMembre
import com.example.projet_plongee.base.dao.DaoPeriode
import com.example.projet_plongee.base.dao.DaoPerogative
import com.example.projet_plongee.base.dao.DaoPlongee
import com.example.projet_plongee.base.dao.DaoSite
import com.example.projet_plongee.base.entity.Bateau
import com.example.projet_plongee.base.entity.Membre
import com.example.projet_plongee.base.entity.Periode
import com.example.projet_plongee.base.entity.Perogative
import com.example.projet_plongee.base.entity.Plongee
import com.example.projet_plongee.base.entity.Siteplongee

@Database(
    entities = [Bateau::class, Membre::class, Periode::class, Perogative::class, Plongee::class, Siteplongee::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 2, to = 3)
    ]
)

abstract class Base : RoomDatabase() {
    abstract fun BateauDAO(): DaoBateau
    abstract fun MembreDAO(): DaoMembre
    abstract fun PeriodeDAO(): DaoPeriode
    abstract fun PerogativeDAO(): DaoPerogative
    abstract fun PlongeeDAO(): DaoPlongee
    abstract fun SiteDAO(): DaoSite

    companion object {
        private var instance: Base? = null
        fun getInstance(context: Context): Base {
            if (instance == null) instance = Room.databaseBuilder(
                context, Base::class.java, "modules.sqlite"
            ).build()
            return instance!!
        }
    }
}