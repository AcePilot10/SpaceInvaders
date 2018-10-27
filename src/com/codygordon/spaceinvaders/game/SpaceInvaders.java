package com.codygordon.spaceinvaders.game;

import com.codygordon.spaceinvaders.enemies.EnemyWave;

public class SpaceInvaders extends GameContainer {

	@Override
	public void startGame() {
		super.startGame();
		EnemyWave wave = new EnemyWave(1, 1);
		getController().spawnEnemyWave(wave);
		getController().addBarriers();
		getController().createPlayer();
	}	
}