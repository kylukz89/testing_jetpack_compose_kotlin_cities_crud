package com.example.jetpackaula.Hilt

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.jetpackaula.Data.Database.AppDB
import com.example.jetpackaula.Data.Repositories.CidadesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class HiltApplication: Application()

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    // Função que retorna a instância do App db = banco de dados sqlite
    @Provides
    @Singleton
    fun providerDatabase(@ApplicationContext appContext: Context): AppDB {
        return Room.databaseBuilder(
            appContext,
            AppDB::class.java,
            "bdcurso.db"
        )
//            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun CidadesRepository(db: AppDB) = db.CidadeDAO()
}