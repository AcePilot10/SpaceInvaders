package com.codygordon.spaceinvaders.enemies;

import java.util.HashMap;

import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;

public class EnemyWave {

	private int rows;
	private HashMap<Integer, Enemy> enemies = new HashMap<Integer, Enemy>();
	
	public int getRows() {
		return this.rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public void alignWave() {
		int currentRow;
		for(int i = 1; i <= rows; i++) {
			for(int key : enemies.keySet()) {
				enemies.get(key);
			}
		}
	}
	
	public HashMap<Integer, Enemy> getEnemeis() {
		return this.enemies;
	}
}