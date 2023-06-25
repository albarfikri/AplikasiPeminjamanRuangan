package com.example.aplikasipeminjamanruangan.domain.repository

import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.domain.model.PengajuanModel
import com.example.aplikasipeminjamanruangan.domain.model.RetrofitImageModel
import com.example.aplikasipeminjamanruangan.domain.model.RetrofitPcrModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModelMain
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IAppRepository {
    suspend fun getRooms(): Flow<Resource<List<RoomsModelMain?>>>
    suspend fun updateRooms(roomsModelMain: RoomsModelMain): Flow<Resource<String>>
    suspend fun getImageResult(file: File): Flow<Resource<RetrofitImageModel>>
    suspend fun verifiedNim(nim: String): Flow<Resource<RetrofitPcrModel>>
    suspend fun insertPengajuan(data: PengajuanModel): Flow<Resource<String>>
    suspend fun getPengajuan(): Flow<Resource<List<PengajuanModel?>>>
}