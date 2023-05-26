package com.example.aplikasipeminjamanruangan.data

import com.example.aplikasipeminjamanruangan.data.remote.firebase.RealtimeDB
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import com.example.aplikasipeminjamanruangan.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val realtimeDB: RealtimeDB
): IAppRepository {
    override suspend fun getRooms(): Flow<Resource<List<RoomsModel?>>> = realtimeDB.getRooms()
}