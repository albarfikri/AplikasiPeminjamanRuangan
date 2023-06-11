package com.example.aplikasipeminjamanruangan.di

import com.example.aplikasipeminjamanruangan.data.remote.firebase.RealtimeDB
import com.example.aplikasipeminjamanruangan.presentation.utils.BASE_URL_IMAGE_DETECTION
import com.example.aplikasipeminjamanruangan.presentation.utils.BASE_URL_PCR
import com.example.aplikasipeminjamanruangan.presentation.utils.URL_IMAGE_DETECTION
import com.example.aplikasipeminjamanruangan.presentation.utils.URL_PCR
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {
    @Provides
    @Singleton
    fun provideRepository(reference: DatabaseReference): RealtimeDB =
        RealtimeDB(dbReference = reference)

    @Provides
    @Singleton
    @Named(URL_IMAGE_DETECTION)
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_IMAGE_DETECTION)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named(URL_PCR)
    fun provideRetrofitInstance2(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_PCR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}