package com.alejandro.ataleofheroes

import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.view.SurfaceControl
import android.view.View
import databases.DatabaseGame
import kotlinx.android.synthetic.main.activity_game_menu.*
import services.AlertService

/***
 * Game Menu class. This is the menu that appears before the game starts and execute the MainGameClass.
 * manager -> FragmentManager parameter that allows the fragment add, remove or replace the fragment in the layout
 *  fragment -> The FragmentActivity that is inflated in the game menu class.
 *
 */
class GameMenu : AppCompatActivity() {


    private val manager: FragmentManager by lazy { this.supportFragmentManager }
    private val fragment: FragmentActivity by lazy { FragmentActivity() }

    /***
     * OnCreate function that creates the layout of the activity and implements the fragment's transactions
     * in this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_menu)

        var transaction: FragmentTransaction = manager.beginTransaction()

        transaction.replace(R.id.fragmentLayout, fragment, "logo")
        transaction.addToBackStack("logo")

        transaction.commit()



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
    /***
     * This funcition allows the user to close the app from the activity just pressing the back button from Android.
     */
    override fun onBackPressed() {
        var title: String = "Exit the game"
        var message: String = "Are you sure that do you want to exit the game?"
        var rYes: String = "Yes"
        var rNo: String = "No"


        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)

        builder.setMessage(message).setPositiveButton(rYes) {
            dialog, id -> Process.killProcess(Process.myPid())
            System.exit(1)

        }.setNegativeButton(rNo) { dialog, id -> }

        builder.create()
        builder.show()


    }


}
