package com.example.aplikasipeminjamanruangan.data.remote.firebase

import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RealtimeDB @Inject constructor(
    private val dbReference: DatabaseReference
) {
    suspend fun getRooms(): Flow<Resource<List<RoomsModel?>>> = callbackFlow {
        trySend(Resource.Loading)
        dbReference.keepSynced(true)
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

        dbReference.addValueEventListener(event)
        awaitClose { close() }
    }
}