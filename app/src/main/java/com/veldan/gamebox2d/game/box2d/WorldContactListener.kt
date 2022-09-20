package com.veldan.gamebox2d.game.box2d

import com.badlogic.gdx.physics.box2d.*
import com.veldan.gamebox2d.game.box2d.bodies.AbstractBody
import com.veldan.gamebox2d.utils.log

object WorldContactListener: ContactListener {

    override fun beginContact(contact: Contact) {
        with(contact) {
            (fixtureA.body.userData as AbstractBody).beginContact((fixtureB.body.userData as AbstractBody))
            (fixtureB.body.userData as AbstractBody).beginContact((fixtureA.body.userData as AbstractBody))
        }
    }

    override fun endContact(contact: Contact) {
        with(contact) {
            (fixtureA.body.userData as AbstractBody).endContact((fixtureA.body.userData as AbstractBody))
            (fixtureB.body.userData as AbstractBody).endContact((fixtureB.body.userData as AbstractBody))
        }
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold?) {
    }

    override fun postSolve(contact: Contact, impulse: ContactImpulse?) {
    }

}