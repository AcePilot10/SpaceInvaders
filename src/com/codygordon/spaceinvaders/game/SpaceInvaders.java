package com.codygordon.spaceinvaders.game;

import java.awt.Color;
import java.awt.Point;

import com.codygordon.spaceinvaders.builders.EnemyWaveBuilder;
import com.codygordon.spaceinvaders.enemies.EnemyWave;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy1;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy2;
import com.codygordon.spaceinvaders.gameobjects.enemies.EnemyShooter;
import com.codygordon.spaceinvaders.gameobjects.player.Player;

public class SpaceInvaders extends GameContainer {

	/*
	@Override
	public void startGame() {
		super.startGame();

		EnemyShooter enemyPreset = new EnemyShooter();
		
		EnemyWaveBuilder waveBuilder = new EnemyWaveBuilder();
		EnemyWave wave = waveBuilder.setRows(1)
						.setEnemiesPerRow(1)
						.setXPadding(50)
						.setYPadding(50)
						.createEnemyPreset(enemyPreset)
						.build();
		
		PlayerBuilder playerBuilder = new PlayerBuilder();
		Player player = playerBuilder
				.setColor(Color.RED)
				.setHeight(50)
				.setWidth(50)
				.setMoveSpeed(5)
				.setProjectileSpeed(15)
				.setShootDelay(800)
				.setStartingLocation(new Point(getMainFrame().getWidth() / 2, getMainFrame().getHeight() - 100))
				.build();
		
		BarrierBuilder builder = new BarrierBuilder();
		Barrier barrier = builder
				.setColor(Color.BLUE)
				.setHeight(25)
				.setWidth(75)
				.setLocation(new Point((getMainFrame().getWidth() / 2) - (75 / 2), getMainFrame().getHeight() - 200))
				.build();
		
		getController().spawnEnemyWave(wave);
		getController().createPlayer(player);
		getController().createBarrier(barrier);
	}
	*/
	
	@Override
	public void createPlayer() {
		int playerSize = 50;
		int startX = getMainFrame().getWidth() / 2 - playerSize / 2;
		int startY = getMainFrame().getHeight() - playerSize * 2;
		Player player = playerBuilder
				.setColor(Color.RED)
				.setHeight(playerSize)
				.setWidth(playerSize)
				.setMoveSpeed(5)
				.setProjectileSpeed(15)
				.setShootDelay(800)
				.setStartingLocation(new Point(startX, startY))
				.build();
		
		getController().createPlayer(player);
	}
	
	@Override
	public void createBarriers() {
		int width = 100;
		int height = 75;
		int midX = getMainFrame().getWidth() / 2 - width / 2;
		int bottomPadding = height + 200;
		int y = getMainFrame().getHeight() - bottomPadding;
		Barrier barrier1 = barrierBuilder
				.setColor(Color.BLUE)
				.setHeight(height)
				.setWidth(width)
				.setLocation(new Point(midX - width * 2, y))
				.build();
		
		Barrier barrier2 = barrierBuilder
				.setColor(Color.BLUE)
				.setHeight(height)
				.setWidth(width)
				.setLocation(new Point(midX + width * 2, y))
				.build();
		
		//getController().createBarrier(barrier1);
		//getController().createBarrier(barrier2);
	}
	
	@Override
	public void createEnemyWave() {
		EnemyShooter enemyPreset = new EnemyShooter();
		enemyPreset.setShootDelay(6000);
		
		//Enemy1 enemy1Preset = new Enemy1();
		//Enemy2 enemy2Preset = new Enemy2();
		
		EnemyWaveBuilder waveBuilder = new EnemyWaveBuilder();
		EnemyWave wave = waveBuilder.setRows(2)
						.setEnemiesPerRow(4)
						.setXPadding(50)
						.setYPadding(50)
						.setMoveHorizontalDelay(5000)
						.createEnemyPreset(enemyPreset)
						.createEnemyPreset(enemyPreset)
						.build();
			
		getController().createEnemyWave(wave);
	}
}