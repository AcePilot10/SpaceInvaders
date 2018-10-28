package com.codygordon.spaceinvaders.enemies;

import java.awt.Point;

import com.codygordon.spaceinvaders.controllers.GameController;
import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;
import com.codygordon.spaceinvaders.util.ScreenUtil;

public class EnemyWave implements Cloneable {

	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	
	public int xPadding;
	public int yPadding;
	
	public long moveHorizontalDelay;
	
	private int rows;
	private int enemiesPerRow;
	private Thread moveHorizontalThread;
	private int horizontalDirection = RIGHT;

	private Enemy[][] enemies;
	public Enemy[] enemyPreset;
	
	@Override
	public EnemyWave clone() {
		try {
			return (EnemyWave) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public EnemyWave(int rows, int enemiesPerRow) {
		this.rows = rows;
		this.enemiesPerRow = enemiesPerRow;
		enemies = new Enemy[rows][enemiesPerRow];
		enemyPreset = new Enemy[rows];
	}
	
	public void init() {
		createEnemies();
		beginMoving();
	}

	public void enemyKilled() {
		if(allEnemiesAreDead()) {
			GameContainer.getInstance().getController().spawnNextWave();
		}
	}
	
	public void addEnemyPreset(int row, Enemy enemy) {
		enemyPreset[row] = enemy;
	}
	
	public void setEnemyPreset(Enemy[] enemyPreset) {
		this.enemyPreset = enemyPreset;
	}
	
	private void createEnemies() {
		for(int row = 1; row <= rows; row++) {
			Enemy enemyToClone = enemyPreset[row - 1];
			for(int col = 1; col <= enemiesPerRow; col++) {
				int x = xPadding * col;
				int y = yPadding * row;
				Enemy enemy = enemyToClone.clone();
				enemy.setLocation(new Point(x, y));
				initEnemy(enemy, row, col);
			}
		}
	}
	
	private void initEnemy(Enemy enemy, int row, int col) {
		enemies[row-1][col-1] = enemy;
		GameController controller = GameContainer.getInstance().getController();
		controller.initGameObject(enemy);
		enemy.init();
	}
		
	private void beginMoving() {
		IEnemyMove move = new IEnemyMove() {
			@Override
			public void move() {
				moveHorizontal();
			}
		};
		MoveRunnable runnable = new MoveRunnable(move, moveHorizontalDelay);
		moveHorizontalThread = new Thread(runnable);
		moveHorizontalThread.setName("Move Horizontal Thread");
		moveHorizontalThread.start();
	}
	
	private void moveHorizontal() {
		int deltaX = 0;
		switch(horizontalDirection) {
		case LEFT:
			deltaX -= xPadding;
			break;
		case RIGHT:
			deltaX += yPadding;
			break;
		}
		for(int row = 1; row <= rows; row++) {
			for(int enemyColumn = 1; enemyColumn <= enemiesPerRow; enemyColumn++) {
				Enemy enemy = enemies[row - 1][enemyColumn - 1];
				enemy.setLocation(new Point(enemy.getLocation().x + deltaX, enemy.getLocation().y)); 
			}
		}
		handleDirectionChange();
	}
	
	private void handleDirectionChange() {
		if(horizontalDirection == LEFT) {
			outerloop: 
			for(int row = 1; row <= rows; row++) {
				for(int column = 1; column <= enemiesPerRow; column++) {
					Enemy enemy = enemies[row - 1][column - 1];
					if(enemy.isAlive()) {
						
						Point enemyLocation = enemy.getLocation();
						int x = enemyLocation.x - xPadding / 2;
						int y = enemyLocation.y;
						
						if(ScreenUtil.isOffScreen(new Point(x, y))) {
							horizontalDirection = RIGHT;
							moveDown();
						}
						break outerloop;
					}
				}
			}
		} else if(horizontalDirection == RIGHT) {
			outerloop: 
			for(int row = 1; row <= rows; rows++) {
				for(int column = enemiesPerRow; column >= 1; column--) {
					Enemy enemy = enemies[row - 1][column - 1];
					if(enemy.isAlive()) {
						
						Point enemyLocation = enemy.getLocation();
						int x = enemyLocation.x + xPadding * 2;
						int y = enemyLocation.y;
						if(ScreenUtil.isOffScreen(new Point(x, y))) {
							horizontalDirection = LEFT;
							moveDown();
						}
						break outerloop;
					}
				}
			}
		}
	}
	
	private void moveDown() {
		for(int row = 1; row <= rows; row++) {
			for(int enemyColumn = 1; enemyColumn <= enemiesPerRow; enemyColumn++) {
				Enemy enemy = enemies[row - 1][enemyColumn - 1];
				enemy.setLocation(new Point(enemy.getLocation().x, enemy.getLocation().y + yPadding)); 
			}
		}
	}

	private boolean allEnemiesAreDead() {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < enemiesPerRow; col++) {
				Enemy enemy = enemies[row][col];
				if(enemy.isAlive()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public void setXPadding(int xPadding) {
		this.xPadding = xPadding;
	}
	
	public void setYPadding(int yPadding) {
		this.yPadding = yPadding;
	}

	public void stop() {
		moveHorizontalThread.interrupt();
	}
}