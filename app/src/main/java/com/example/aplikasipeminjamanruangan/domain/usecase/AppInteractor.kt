package com.example.aplikasipeminjamanruangan.domain.usecase

import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import com.example.aplikasipeminjamanruangan.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppInteractor @Inject constructor(private val appRepository: IAppRepository) : IAppUseCase {
    override suspend fun getRooms(): Flow<Resource<List<RoomsModel?>>> = appRepository.getRooms()
}