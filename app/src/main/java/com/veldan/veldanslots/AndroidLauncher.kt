package com.veldan.veldanslots

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.veldan.veldanslots.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class AndroidLauncher : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    val controller by lazy { AndroidLauncherController(this) }

    lateinit var binding      : ActivityMainBinding
    lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_KingSolomonSlots)
        controller.initialize()
    }

    override fun exit() {
        finish()
        exitProcess(0)
    }

}