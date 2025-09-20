package com.dfcruz.talkie

import android.app.Application
import android.os.StrictMode
import com.dfcruz.talkie.data.local.SeedManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TalkieApp : Application() {

    @Inject
    lateinit var seedManager: SeedManager

    override fun onCreate() {
        super.onCreate()
        /*
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }

         */

        seedManager.seed()
    }
}