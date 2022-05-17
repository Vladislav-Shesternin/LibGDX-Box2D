package com.veldan.veldanslots

import android.app.Application
import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.veldan.veldanslots.utils.billing.BillingUtil
import com.veldan.veldanslots.utils.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var appContext: Context private set

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        MobileAds.initialize(this)
    }

}