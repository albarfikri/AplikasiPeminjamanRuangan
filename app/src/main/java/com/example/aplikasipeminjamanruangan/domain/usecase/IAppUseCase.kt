package com.example.aplikasipeminjamanruangan.domain.usecase

import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import kotlinx.coroutines.flow.Flow

interface IAppUseCase {
    suspend fun getRooms(): Flow<Resource<List<RoomsModel?>>>
}