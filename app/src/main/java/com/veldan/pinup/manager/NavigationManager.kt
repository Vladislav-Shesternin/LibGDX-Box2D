package com.veldan.pinup.manager

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.veldan.pinup.main.game

object NavigationManager {

    private val backStack = mutableListOf<Screen>()
    var key: Int? = null
        private set

    fun navigate(to: Screen, from: Screen? = null, key: Int? = null) {
        Gdx.app.postRunnable {
            this.key = key

            game.screen = to
            from?.let { backStack.add(it) }
            if (backStack.contains(to)) backStack.remove(to)
        }
    }

    fun back(key: Int? = null) {
        Gdx.app.postRunnable {
            this.key = key

            if (backStack.isEmpty()) exit()
            else game.screen = backStack.removeLast()
        }
    }

    fun exit() {
        Gdx.app.postRunnable {
            game.dispose()
            backStack.clear()
            Gdx.app.exit()
        }
    }

}