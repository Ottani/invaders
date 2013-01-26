package com.ottani.invaders.app;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.ottani.invaders.state.Game;
import com.ottani.invaders.state.GameOver;
import com.ottani.invaders.state.MainMenu;


public class MainApp extends StateBasedGame {

	public MainApp() {
		super("invaders");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MainMenu());
		this.addState(new Game());
		this.addState(new GameOver());
		
	}


	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new MainApp());
		app.setDisplayMode(Constants.WIDTH, Constants.HEIGHT, false);
		app.setTargetFrameRate(60);
		app.start();
	}


}
