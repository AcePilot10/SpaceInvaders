package com.codygordon.spaceinvaders.builders;

import com.codygordon.spaceinvaders.enemies.EnemyWave;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;

public class EnemyWaveBuilder {

	private EnemyWave wave;
	
	public EnemyWaveBuilder() {
		//wave = new EnemyWave();
	}
	
	public EnemyWaveBuilder setRows(int rows) {
		wave.setRows(rows);
		return this;
	}
	
	public EnemyWaveBuilder addEnemy(int row, Enemy enemy) {
		return this;
	}
	
	public EnemyWave build() {
		return wave;
	}
}