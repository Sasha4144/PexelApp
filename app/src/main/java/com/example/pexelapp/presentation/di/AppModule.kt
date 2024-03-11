package com.example.pexelapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.pexelapp.data.api.ApiService
import com.example.pexelapp.data.database.MainDB
import com.example.pexelapp.data.repository.DatabaseRepositoryImpl
import com.example.pexelapp.data.repository.WebRepositoryImpl
import com.example.pexelapp.domain.repository.DatabaseRepository
import com.example.pexelapp.domain.repository.WebRepository
import com.example.pexelapp.utils.Constants.URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWebRepository(apiService: ApiService): WebRepository {
        return WebRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMainDB(app: Application): MainDB {
        return Room.databaseBuilder(
            app,
            MainDB::class.java,
            name = "photos.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(db: MainDB): DatabaseRepository {
        return DatabaseRepositoryImpl(db)
    }
}