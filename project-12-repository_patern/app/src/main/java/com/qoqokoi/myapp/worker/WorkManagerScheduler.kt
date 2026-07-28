package com.qoqokoi.myapp.worker

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

object WorkManagerScheduler {

    fun schedulePeriodicRefresh(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(
            15L, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "RefreshDataWork",
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}
