package com.veldan.pinup.main.controller

import androidx.navigation.findNavController
import com.veldan.pinup.R
import com.veldan.pinup.databinding.ActivityMainBinding
import com.veldan.pinup.main.AndroidLauncher

class AndroidLauncherControllerPrivate(val activity: AndroidLauncher) {

    fun initialize() {
        with(activity) {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            navController = findNavController(R.id.nav_host_fragment)
        }
    }

}