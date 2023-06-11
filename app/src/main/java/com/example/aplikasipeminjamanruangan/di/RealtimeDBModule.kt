package com.example.aplikasipeminjamanruangan.di

import com.example.aplikasipeminjamanruangan.presentation.utils.DB_REFERENCES
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
    @Provides
    @Singleton
    fun provideRealtimeDBRef(): DatabaseReference = Firebase.database.getReference(DB_REFERENCES)
}