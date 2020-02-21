package com.mlnwski.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mlnwski.game.view.GameScreen;

public class Main extends Game {
		Screen gameScreen;
		@Override
		public void create() {
			gameScreen = new GameScreen();
			setScreen(gameScreen);
		}
	}
