package com.alejandro.ataleofheroes

import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GameMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_menu)


    }


    fun launchGame(view: View) {

        val i = Intent(this, AndroidLauncher::class.java)
        this.startActivity(i)
    }
}
