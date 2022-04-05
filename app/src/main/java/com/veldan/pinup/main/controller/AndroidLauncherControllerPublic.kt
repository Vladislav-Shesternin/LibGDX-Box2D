package com.veldan.pinup.main.controller

import androidx.core.view.isVisible
import com.veldan.pinup.main.AndroidLauncher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AndroidLauncherControllerPublic(val activity: AndroidLauncher) {

    private lateinit var coroutineLoader: CoroutineScope



    fun showLoader() {
        coroutineLoader = CoroutineScope(Dispatchers.Main)
        coroutineLoader.launch { activity.binding.lottie.apply {
            isVisible = true
            playAnimation()
        } }
    }

    fun hideLoader() {
        coroutineLoader.launch { activity.binding.lottie.apply {
            isVisible = false
            cancelAnimation()
            cancel()
        } }
    }

}