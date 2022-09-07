package com.veldan.gamebox2d

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.veldan.gamebox2d.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class AndroidLauncher : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        lateinit var binding: ActivityMainBinding
        lateinit var navController: NavController

        private lateinit var coroutineLoader: CoroutineScope

        fun showLoader() {
            coroutineLoader = CoroutineScope(Dispatchers.Main)
            coroutineLoader.launch {
                binding.lottie.apply {
                    isVisible = true
                    playAnimation()
                }
            }
        }

        fun hideLoader() {
            coroutineLoader.launch {
                binding.lottie.apply {
                    isVisible = false
                    cancelAnimation()
                    cancel()
                }
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        showLoader()
    }

    override fun exit() {
        finishAndRemoveTask()
        exitProcess(0)
    }



    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
    }

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        with(navController) {
            navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) }
        }
    }

}