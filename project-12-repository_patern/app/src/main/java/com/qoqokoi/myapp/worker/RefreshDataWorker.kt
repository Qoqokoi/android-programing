package com.qoqokoi.myapp.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.qoqokoi.myapp.data.local.AppDatabase
import com.qoqokoi.myapp.data.remote.ApiClient
import com.qoqokoi.myapp.data.repository.AppRepository

class RefreshDataWorker(
    appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        Log.d("RefreshDataWorker", "Background work started: Executing data refresh...")

        return try {
            val database = AppDatabase.getDatabase(applicationContext)
            val network = ApiClient.networkService
            val repository = AppRepository(database.deviceDao(), network)

            repository.refreshData()

            Log.d("RefreshDataWorker", "Work finished successfully with Result.success()")
            Result.success()
        } catch (e: Exception) {
            Log.e("RefreshDataWorker", "Work failed: ${e.localizedMessage}")
            Result.retry()
        }
    }
}
