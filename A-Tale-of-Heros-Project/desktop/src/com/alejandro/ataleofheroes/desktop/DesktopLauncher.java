package com.alejandro.ataleofheroes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import runmainclass.MainGameClass;

/***
 * This class allows the program to cast the desktop configuration. It is unused
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//Uncomment this if it's needed to try the desktop configuration
		//new LwjglApplication(new MainGameClass(), config);
	}
}
