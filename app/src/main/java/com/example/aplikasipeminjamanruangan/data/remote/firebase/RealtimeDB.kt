package com.example.aplikasipeminjamanruangan.data.remote.firebase

import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.domain.model.PengajuanModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
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
) {
    suspend fun getRooms(): Flow<Resource<List<RoomsModel?>>> = callbackFlow {
        trySend(Resource.Loading)
        dbRoomsReference.keepSynced(true)
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val rooms = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue<RoomsModel>()
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
}