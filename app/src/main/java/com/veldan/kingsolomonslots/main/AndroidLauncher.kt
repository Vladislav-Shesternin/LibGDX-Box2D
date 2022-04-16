package com.veldan.kingsolomonslots.main

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.veldan.kingsolomonslots.R
import com.veldan.kingsolomonslots.databinding.ActivityMainBinding
import com.veldan.kingsolomonslots.main.controller.AndroidLauncherControllerPublic
import kotlin.system.exitProcess

class AndroidLauncher : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    val controller by lazy { AndroidLauncherControllerPublic(this) }

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