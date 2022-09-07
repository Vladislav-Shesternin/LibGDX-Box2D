package com.veldan.gamebox2d

import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.veldan.gamebox2d.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AndroidLauncherController(val activity: AndroidLauncher) {

    private lateinit var coroutineLoader: CoroutineScope

    lateinit var binding      : ActivityMainBinding
    lateinit var navController: NavController



    fun initialize() {
        with(activity) {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            navController = findNavController(R.id.nav_host_fragment)
        }
    }

    fun showLoader() {
        coroutineLoader = CoroutineScope(Dispatchers.Main)
        coroutineLoader.launch { binding.lottie.apply {
            isVisible = true
            playAnimation()
        } }
    }

    fun hideLoader() {
        coroutineLoader.launch { binding.lottie.apply {
            isVisible = false
            cancelAnimation()
            cancel()
        } }
    }

}