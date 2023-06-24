package com.example.aplikasipeminjamanruangan.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PengajuanModel(
    val fasilitas: String? = null,
    val jmulai: String? = null,
    val jselesai: String? = null,
    val nama: String? = null,
    val nim: String? = null,
    val prodi: String? = null,
    val ruangan: String? = null,
    val tanggal: String? = null,
    val pengajuanDiterima: Boolean = false,
    val fotoRuangan: String? = null
) : Parcelable