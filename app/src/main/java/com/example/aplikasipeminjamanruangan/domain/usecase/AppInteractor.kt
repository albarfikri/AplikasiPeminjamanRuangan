package com.example.aplikasipeminjamanruangan.domain.usecase

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.aplikasipeminjamanruangan.data.Resource
import com.example.aplikasipeminjamanruangan.domain.model.CameraXModel
import com.example.aplikasipeminjamanruangan.domain.model.RetrofitImageModel
import com.example.aplikasipeminjamanruangan.domain.model.RetrofitPcrModel
import com.example.aplikasipeminjamanruangan.domain.model.RoomsModel
import com.example.aplikasipeminjamanruangan.domain.repository.IAppRepository
import com.example.aplikasipeminjamanruangan.domain.repository.ICustomCameraRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class AppInteractor @Inject constructor(
    private val appRepository: IAppRepository,
    private val customCameraRepository: ICustomCameraRepository
) : IAppUseCase {
    override suspend fun getRooms(): Flow<Resource<List<RoomsModel?>>> = appRepository.getRooms()
    override suspend fun captureAndSaveImage(context: Context): Flow<Resource<CameraXModel>> =
        customCameraRepository.captureAndSaveImage(context)

    override suspend fun showCameraPreview(
        previewView: PreviewView, lifecycleOwner: LifecycleOwner
    ) {
        customCameraRepository.showCameraPreview(
            previewView, lifecycleOwner
        )
    }

    override suspend fun getImageResult(file: File): Flow<Resource<RetrofitImageModel>> =
        appRepository.getImageResult(file)

    override suspend fun verifiedNim(nim: String): Flow<Resource<RetrofitPcrModel>> =
        appRepository.verifiedNim(nim)
}