package com.paulo.invaders;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState {
	public static final int ID = 1;

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Options\n1. Start game\n2. Resume game\n3. Exit", 100, 100);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_1)) {
			game.getState(Game.ID).init(gc, game);
			game.enterState(Game.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		} else if (gc.getInput().isKeyPressed(Input.KEY_2)) {
			game.enterState(Game.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		} else if (gc.getInput().isKeyPressed(Input.KEY_3)) {
			gc.exit();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
