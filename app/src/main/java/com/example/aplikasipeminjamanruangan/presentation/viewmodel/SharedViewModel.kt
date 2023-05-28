package com.example.aplikasipeminjamanruangan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {
    private var _sharedState = MutableStateFlow(RoomsModel())
    val sharedStated = _sharedState.asStateFlow()

    fun addRooms(roomsModel: RoomsModel) {
        _sharedState.value = roomsModel
    }
//        _sharedState.value.copy(
//            deskripsi_ruangan = roomsModel.deskripsi_ruangan,
//            fasilitas_ruangan = roomsModel.deskripsi_ruangan,
//            foto_ruangan = roomsModel.foto_ruangan,
//            id_ruangan = roomsModel.id_ruangan,
//            isLent = roomsModel.isLent,
//            lantai_ruangan = roomsModel.lantai_ruangan,
//            nama_ruangan = roomsModel.nama_ruangan
//        )
}