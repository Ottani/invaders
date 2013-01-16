package com.paulo.invaders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	public static final int ID = 2;

	private Player player;
	private Input input;
	private EnemyPack enemyPack;
	private List<PlayerBullet> playerBullets;
	private int score;
	private Bases bases;

	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {
		input = gc.getInput();
		player = new Player();
		enemyPack = new EnemyPack();
		playerBullets = new ArrayList<PlayerBullet>(1024);
		bases = new Bases();
		score = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		enemyPack.render(g);
		player.render(g);
		for (PlayerBullet e: playerBullets) {
			e.render(g);
		}
		bases.render(g);
		g.drawString("e: "+enemyPack.getQty()+" | score: "+score, 400, 5);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		player.update(input, delta);
		enemyPack.update(delta);
		for (Iterator<PlayerBullet> it = playerBullets.iterator(); it.hasNext(); ) {
			PlayerBullet e = it.next();
			if (e.isShouldRemove()) {
				it.remove();
				continue;
			}
			e.update(delta);
			if (enemyPack.checkHit(e)) {
				score += 50;
				it.remove();
			}
			if (bases.checkHit(e)) {
				score -= 1;
				it.remove();
			}
		}
		if (input.isKeyDown(Input.KEY_SPACE)) player.shoot(playerBullets);
		if (enemyPack.getQty()<=0) {
			((GameOver)game.getState(GameOver.ID)).setResults(score, true);
			game.enterState(GameOver.ID);
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(MainMenu.ID);
		}

	}

	@Override
	public int getID() {
		return ID;
	}

}
