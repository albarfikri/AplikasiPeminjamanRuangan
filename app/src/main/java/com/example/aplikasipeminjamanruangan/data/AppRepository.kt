package com.example.aplikasipeminjamanruangan.data

import com.example.aplikasipeminjamanruangan.BuildConfig
import com.example.aplikasipeminjamanruangan.data.remote.firebase.RealtimeDB
import com.example.aplikasipeminjamanruangan.data.remote.retrofit.apiimagedetector.FileApi
import com.example.aplikasipeminjamanruangan.data.remote.retrofit.apipcr.FilePcrApi
import com.example.aplikasipeminjamanruangan.domain.model.RetrofitImageModel
import com.example.aplikasipeminjamanruangan.domain.model.RetrofitPcrModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import com.example.aplikasipeminjamanruangan.domain.repository.IAppRepository
import com.example.aplikasipeminjamanruangan.presentation.utils.COLLECTION
import com.example.aplikasipeminjamanruangan.presentation.utils.URL_IMAGE_DETECTION
import com.example.aplikasipeminjamanruangan.presentation.utils.URL_PCR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.File
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class AppRepository @Inject constructor(
    private val realtimeDB: RealtimeDB,
    @Named(URL_IMAGE_DETECTION) private val retrofit: Retrofit,
    @Named(URL_PCR) private val retrofitUrlPcr: Retrofit
) : IAppRepository {
    override suspend fun getRooms(): Flow<Resource<List<RoomsModel?>>> = realtimeDB.getRooms()
    override suspend fun getImageResult(file: File): Flow<Resource<RetrofitImageModel>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = retrofit
                    .create(FileApi::class.java).uploadImage(
                        image = MultipartBody.Part
                            .createFormData(
                                "image",
                                file.name,
                                file.asRequestBody()
                            )
                    )
                emit(Resource.Success(response))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(Throwable(e.message)))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(Throwable(e.message())))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun verifiedNim(nim: String): Flow<Resource<RetrofitPcrModel>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = retrofitUrlPcr
                    .create(FilePcrApi::class.java).validateNim(
                        apikey = BuildConfig.API_KEY,
                        collection = COLLECTION,
                        nim = nim,
                    )
                emit(Resource.Success(response))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(Throwable(e.message)))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(Throwable(e.message())))
            }
        }.flowOn(Dispatchers.IO)
}