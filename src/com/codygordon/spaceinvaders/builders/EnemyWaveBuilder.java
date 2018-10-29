package com.codygordon.spaceinvaders.builders;

import java.util.ArrayList;

import com.codygordon.spaceinvaders.enemies.EnemyWave;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;

public class EnemyWaveBuilder {

	private int rows = 1;
	private int enemiesPerRow = 1;
	private int xPadding = 0;
	private int yPadding = 0;
	private int horizontalDistance = 0;
	private long moveHorizontalDelay;
	private ArrayList<Enemy> enemyPreset = new ArrayList<Enemy>();
	
	public EnemyWaveBuilder setRows(int rows) {
		this.rows = rows;
		return this;
	}
	
	public EnemyWaveBuilder setEnemiesPerRow(int enemiesPerRow) {
		this.enemiesPerRow = enemiesPerRow;
		return this;
	}
	
	public EnemyWaveBuilder setXPadding(int xPadding) {
		this.xPadding = xPadding;
		return this;
	}
	
	public EnemyWaveBuilder setYPadding(int yPadding) {
		this.yPadding = yPadding;
		return this;
	}
	
	public EnemyWaveBuilder createEnemyPreset(Enemy enemy) {
		enemyPreset.add(enemy);
		return this;
	}
	
	public EnemyWaveBuilder setMoveHorizontalDelay(long delay) {
		this.moveHorizontalDelay = delay;
		return this;
	}
	
	public EnemyWaveBuilder setMoveHorizontalDistance(int distance) {
		this.horizontalDistance = distance;
		return this;
	}
	
	public EnemyWave build() {
		EnemyWave wave = new EnemyWave(rows, enemiesPerRow);
		wave.setXPadding(xPadding);
		wave.setYPadding(yPadding);
		wave.setHorizontalDelay(moveHorizontalDelay);
		wave.setMoveHorizontalDistance(horizontalDistance);
		
		Enemy[] enemyPreset = new Enemy[rows];
		enemyPreset = this.enemyPreset.toArray(enemyPreset);
		
		wave.setEnemyPreset(enemyPreset);
		
		return wave;
	}
}	