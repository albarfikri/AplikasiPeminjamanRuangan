package com.example.aplikasipeminjamanruangan.domain.model

data class RoomsModel(
    var deskripsi_ruangan: String? =null,
    var fasilitas_ruangan: String? = null,
    var foto_ruangan: String? = null,
    var id_ruangan: Int? = 0,
    var lantai_ruangan: String? = null,
    var nama_ruangan: String? = null
)