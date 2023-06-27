package com.example.aplikasipeminjamanruangan.data.remote.firebase

import android.util.Log
import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.domain.model.PeminjamanModel
import com.example.aplikasipeminjamanruangan.domain.model.PengajuanModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModelMain
import com.example.aplikasipeminjamanruangan.presentation.utils.DB_PEMINJAMAN
import com.example.aplikasipeminjamanruangan.presentation.utils.DB_PENGAJUAN
import com.example.aplikasipeminjamanruangan.presentation.utils.DB_ROOMS
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Named

class RealtimeDB @Inject constructor(
    @Named(DB_ROOMS) private val dbRoomsReference: DatabaseReference,
    @Named(DB_PENGAJUAN) private val dbPengajuanReference: DatabaseReference,
    @Named(DB_PEMINJAMAN) private val dbPeminjamanReference: DatabaseReference,
) {
    suspend fun getRooms(): Flow<Resource<List<RoomsModelMain?>>> = callbackFlow {
        trySend(Resource.Loading)
        dbRoomsReference.keepSynced(true)
        dbRoomsReference.orderByKey()
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                Log.d("Albar Fikri", snapshot.value.toString())
                val rooms = snapshot.children.map { dataSnapshot ->
                    RoomsModelMain(
                        key = dataSnapshot.key,
                        item = dataSnapshot.getValue<RoomsModel>()
                    )
                }
                trySend(Resource.Success(rooms))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(Throwable(error.message)))
            }
        }
        dbRoomsReference.addValueEventListener(event)
        awaitClose { close() }
    }

    suspend fun updateRooms(roomsModelMain: RoomsModelMain): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading)
        val data = toMap(roomsModelMain.item!!)

        dbRoomsReference.child(roomsModelMain.key!!).updateChildren(
            data
        ).addOnCompleteListener {
            trySend(Resource.Success("Update Succesfully"))
        }.addOnFailureListener {
            trySend(Resource.Error(it))
        }
        awaitClose {
            close()
        }
    }

    private fun toMap(roomsModel: RoomsModel): Map<String, Any?> {
        return mapOf(
            "deskripsi_ruangan" to roomsModel.deskripsi_ruangan,
            "fasilitas_ruangan" to roomsModel.fasilitas_ruangan,
            "foto_ruangan" to roomsModel.foto_ruangan,
            "id_ruangan" to roomsModel.id_ruangan,
            "isLent" to roomsModel.isLent,
            "lantai_ruangan" to roomsModel.lantai_ruangan,
            "nama_ruangan" to roomsModel.nama_ruangan
        )
    }

    suspend fun insertPengajuan(item: PengajuanModel): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading)
        dbPengajuanReference.push().setValue(
            item
        ).addOnCompleteListener {
            if (it.isSuccessful)
                trySend(Resource.Success("Data inserted Successfully.."))
        }.addOnFailureListener {
            trySend(Resource.Error(it))
        }
        awaitClose {
            close()
        }
    }

    suspend fun getPengajuan(): Flow<Resource<List<PengajuanModel?>>> = callbackFlow {
        trySend(Resource.Loading)
        dbPengajuanReference.keepSynced(true)
        dbPengajuanReference.orderByKey()
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val pengajuan = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue<PengajuanModel>()
                }
                trySend(Resource.Success(pengajuan))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(Throwable(error.message)))
            }
        }
        dbPengajuanReference.addValueEventListener(event)
        awaitClose { close() }
    }

    suspend fun insertPeminjaman(item: PeminjamanModel): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading)
        dbPeminjamanReference.push().setValue(
            item
        ).addOnCompleteListener {
            if (it.isSuccessful)
                trySend(Resource.Success("Data inserted Successfully.."))
        }.addOnFailureListener {
            trySend(Resource.Error(it))
        }
        awaitClose {
            close()
        }
    }

    suspend fun getPeminjaman(): Flow<Resource<List<PeminjamanModel?>>> = callbackFlow {
        trySend(Resource.Loading)
        dbPeminjamanReference.keepSynced(true)
        dbPeminjamanReference.orderByKey()
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val pengajuan = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue<PeminjamanModel>()
                }
                trySend(Resource.Success(pengajuan))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(Throwable(error.message)))
            }
        }
        dbPeminjamanReference.addValueEventListener(event)
        awaitClose { close() }
    }
}