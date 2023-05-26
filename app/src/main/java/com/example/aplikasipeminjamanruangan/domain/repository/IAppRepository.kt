package com.example.aplikasipeminjamanruangan.domain.repository

import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import kotlinx.coroutines.flow.Flow

interface IAppRepository {
    suspend fun getRooms(): Flow<Resource<List<RoomsModel?>>>
}