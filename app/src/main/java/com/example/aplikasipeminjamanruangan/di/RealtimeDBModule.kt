package com.example.aplikasipeminjamanruangan.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RealtimeDBModule {
    private val dbReference = "rooms"

    @Provides
    @Singleton
    fun provideRealtimeDBRef(): DatabaseReference = Firebase.database.getReference(dbReference)
}