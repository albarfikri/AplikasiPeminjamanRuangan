package com.example.aplikasipeminjamanruangan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel : ViewModel() {
    private var _sharedState = MutableStateFlow(RoomsModel())
    val sharedStated = _sharedState.asStateFlow()

    fun addRooms(roomsModel: RoomsModel) {
        _sharedState.value = roomsModel
    }

    fun resetRoomsData() {
        _sharedState.update {
            it.copy(
                deskripsi_ruangan = null,
                fasilitas_ruangan = null,
                foto_ruangan = null,
                id_ruangan = 0,
                isLent = false,
                lantai_ruangan = null,
                nama_ruangan = null
            )
        }
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