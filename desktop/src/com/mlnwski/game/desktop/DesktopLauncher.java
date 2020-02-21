package com.mlnwski.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mlnwski.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.x = 300;
		config.y = 100;
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Main(), config);
	}
}
