package com.example.thecatfamiliar

import android.app.Application
import com.example.thecatfamiliar.di.initKoin
import org.koin.android.ext.koin.androidContext

class FamiliarApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FamiliarApplication)
        }
    }
}