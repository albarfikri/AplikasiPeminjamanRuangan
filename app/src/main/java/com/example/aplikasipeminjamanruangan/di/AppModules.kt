package com.example.aplikasipeminjamanruangan.di

import com.example.aplikasipeminjamanruangan.data.AppRepository
import com.example.aplikasipeminjamanruangan.data.remote.firebase.RealtimeDB
import com.example.aplikasipeminjamanruangan.domain.repository.IAppRepository
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules{

    @Provides
    @Singleton
    fun provideRepository(reference: DatabaseReference): RealtimeDB = RealtimeDB(dbReference = reference)

}