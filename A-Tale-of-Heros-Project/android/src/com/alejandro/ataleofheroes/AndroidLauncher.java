package com.alejandro.ataleofheroes;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import databases.MainDatabase;
import runmainclass.MainGameClass;

/***
 * This class execute te game menu class that is an android activity.
 */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MainGameClass(new MainDatabase(this)), config);
	}
}
