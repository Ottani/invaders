package com.ottani.invaders.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameOver extends BasicGameState {
	public static final int ID = 3;
	private int score;
	private boolean won;
	
	public void setResults(int score, boolean won) {
		this.score = score;
		this.won = won;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("G A M E  O V E R\n================", 100, 20);
		
		if(won) g.drawString("YOU WON!", 100, 50);
		else 	g.drawString("YOU LOST!", 100, 50);
		
		g.drawString("Points: "+score, 100, 80);
		
		g.drawString("press ESC to return...", 100, 150);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(MainMenu.ID);
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
