package com.example.aplikasipeminjamanruangan.domain.usecase

import android.content.Context
import android.graphics.Camera
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.domain.model.CameraXModel
import com.example.aplikasipeminjamanruangan.domain.model.RetrofitImageModel
import com.example.aplikasipeminjamanruangan.domain.model.RetrofitPcrModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IAppUseCase {
    suspend fun getRooms(): Flow<Resource<List<RoomsModel?>>>
    suspend fun captureAndSaveImage(context:Context): Flow<Resource<CameraXModel>>
    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    )
    suspend fun getImageResult(file: File): Flow<Resource<RetrofitImageModel>>

    suspend fun verifiedNim(nim: String): Flow<Resource<RetrofitPcrModel>>
}