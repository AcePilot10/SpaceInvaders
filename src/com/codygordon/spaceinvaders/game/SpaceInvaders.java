package com.codygordon.spaceinvaders.game;

import java.awt.Color;
import java.awt.Point;

import com.codygordon.spaceinvaders.builders.EnemyWaveBuilder;
import com.codygordon.spaceinvaders.enemies.EnemyWave;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy1;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy2;
import com.codygordon.spaceinvaders.gameobjects.player.Player;

public class SpaceInvaders extends GameContainer {
	
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
				.setProjectileSpeed(5)
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
		
		getController().createBarrier(barrier1);
		getController().createBarrier(barrier2);
	}
	
	@Override
	public void createEnemyWave() {
		Enemy1 enemy1 = new Enemy1();
		Enemy2 enemy2 = new Enemy2();
				
		enemy1.setWidth(35);
		enemy1.setHeight(35); 
		enemy2.setWidth(35);
		enemy2.setHeight(35);
		
		enemy1.setShootDelay(5000);
		enemy2.setShootDelay(5000);
		
		enemy1.setShootChance(25f);
		enemy2.setShootChance(25f);
		
		enemy1.setProjectileSpeed(5);
		enemy2.setProjectileSpeed(5);
		
		EnemyWaveBuilder waveBuilder = new EnemyWaveBuilder();
		EnemyWave wave = waveBuilder.setRows(2)
						.setEnemiesPerRow(5)
						.setXPadding(50)
						.setYPadding(50)
						.setMoveHorizontalDistance(50)
						.setMoveHorizontalDelay(1000)
						.createEnemyPreset(enemy1)
						.createEnemyPreset(enemy2)
						.build();
			
		getController().createEnemyWave(wave);
	}
}