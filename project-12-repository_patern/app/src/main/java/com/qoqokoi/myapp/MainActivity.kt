package com.qoqokoi.myapp

import android.app.Application
import com.qoqokoi.myapp.worker.WorkManagerScheduler

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Jadwalkan WorkManager saat aplikasi dimulai
        WorkManagerScheduler.schedulePeriodicRefresh(this)
    }
}
