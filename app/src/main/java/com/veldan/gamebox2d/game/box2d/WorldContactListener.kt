package com.veldan.gamebox2d.game.box2d

import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold
import com.veldan.gamebox2d.game.box2d.bodies.AbstractBody
import com.veldan.gamebox2d.utils.log

object WorldContactListener: ContactListener {

    override fun beginContact(contact: Contact) {
        val a = contact.fixtureA.body.userData as AbstractBody
        val b = contact.fixtureB.body.userData as AbstractBody
        log("contact: a = ${a.name} | b = ${b.name}")
    }

    override fun endContact(contact: Contact) {
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold?) {
    }

    override fun postSolve(contact: Contact, impulse: ContactImpulse?) {
    }

}