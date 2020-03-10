package com.alejandro.ataleofheroes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatViewInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/***
 * This is the fragment that contains an image that is located in the game menu class.
 */
class FragmentActivity : Fragment() {

    /***
     * This is the onCreate function of the fragment activity. It will initialize the activity itself.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /***
     * this is the onCreateView function of the fragment. This class will inflate the layout of the
     * fragment in the game menu activity
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_fragment, container, false)
    }

}
