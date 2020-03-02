package com.alejandro.ataleofheroes

import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import services.AlertService

/***
 * Game Menu class. This is the menu that appears before the game starts and execute the MainGameClass.
 */
class GameMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_menu)


    }


    /***
     * This is the onClick event of the start game button. This function execute the game and the toast service.
     */
    fun launchGame(view: View) {

        var intentS: Intent = Intent(this, AlertService::class.java)
        //intentS.putExtra("svcMessage", "Starting game...")
        startService(intentS)
        val i = Intent(this, AndroidLauncher::class.java)
        stopService(intentS)
        this.startActivity(i)

    }
}
