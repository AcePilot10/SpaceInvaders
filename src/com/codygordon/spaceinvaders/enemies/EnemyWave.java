package com.codygordon.spaceinvaders.enemies;

import java.awt.Point;
import java.util.ArrayList;

import com.codygordon.spaceinvaders.controllers.GameController;
import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;
import com.codygordon.spaceinvaders.util.ScreenUtil;

public class EnemyWave implements Cloneable {

	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	
	private int xPadding;
	private int yPadding;
	private long baseMoveHorizontalDelay;
	private long currentMoveHorizontalDelay;
	private int rows;
	private int enemiesPerRow;
	private MoveThread moveHorizontalThread;
	private int moveHorizontalDistance;
	private int horizontalDirection = RIGHT;
	private double speedIncreasePerKill;
	
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
		increaseSpeed();
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
	
	public void stop() {
		moveHorizontalThread.running = false;
	}
	
	private void increaseSpeed() {
		double factor = (baseMoveHorizontalDelay * speedIncreasePerKill) - baseMoveHorizontalDelay;
		long newSpeed = Math.round(currentMoveHorizontalDelay - factor);
		if(newSpeed <= 0) {
			newSpeed = 500;
		}
		currentMoveHorizontalDelay = newSpeed;
		moveHorizontalThread.delay = currentMoveHorizontalDelay;
		System.out.println("New horizontal move delay: " + currentMoveHorizontalDelay);
	}
	
	private void createEnemies() {
		this.currentMoveHorizontalDelay = baseMoveHorizontalDelay;
		int startingX = xPadding * rows * 2;
		for(int row = 1; row <= rows; row++) {
			Enemy enemyToClone = enemyPreset[row - 1];
			for(int col = 1; col <= enemiesPerRow; col++) {
				int x = xPadding * col + startingX;
				int y = yPadding * row;
				Enemy enemy = enemyToClone.clone();
				enemy.name = "Enemy " + row + ", " + col;
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
		moveHorizontalThread = new MoveThread(move, currentMoveHorizontalDelay);
		moveHorizontalThread.setName("Move Horizontal Thread");
		moveHorizontalThread.start();
	}
	
	private void moveHorizontal() {
		int currentDirection = horizontalDirection;
		handleDirectionChange();
		if(currentDirection != horizontalDirection) {
			return;
		}
		int deltaX = 0;
		switch(horizontalDirection) {
		case LEFT:
			deltaX -= moveHorizontalDistance;
			break;
		case RIGHT:
			deltaX += moveHorizontalDistance;
			break;
		}
		for(int row = 1; row <= rows; row++) {
			for(int enemyColumn = 1; enemyColumn <= enemiesPerRow; enemyColumn++) {
				Enemy enemy = enemies[row - 1][enemyColumn - 1];
				enemy.setLocation(new Point(enemy.getLocation().x + deltaX, enemy.getLocation().y)); 
			}
		}
	}
	
	private void handleDirectionChange() {
		if(horizontalDirection == LEFT) {
			outerloop: 
			for(int row = 1; row <= rows; row++) {
				for(int column = 1; column <= enemiesPerRow; column++) {
					Enemy enemy = enemies[row - 1][column - 1];
					if(enemy.isAlive()) {
						Point enemyLocation = enemy.getLocation();
						int x = enemyLocation.x - moveHorizontalDistance / 2;
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
						int x = enemyLocation.x + moveHorizontalDistance * 2;
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
	
	public ArrayList<Enemy> getEnemies() {
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < enemiesPerRow; col++) {
				Enemy enemy = enemies[row][col];
				enemyList.add(enemy);
			}
		}
		return enemyList;
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

	public void setHorizontalDelay(long delay) {
		this.baseMoveHorizontalDelay = delay;
	}
	
	public int getMoveHorizontalDistance() {
		return this.moveHorizontalDistance;
	}
	
	public void setMoveHorizontalDistance(int distance) {
		this.moveHorizontalDistance = distance;
	}
	
	public double getSpeedIncreasePerKill() {
		return this.speedIncreasePerKill;
	}
	
	public void setSpeedIncreasePerKill(double value) {
		this.speedIncreasePerKill = value;
	}
	
	public Thread getMoveHorizontalThread() {
		return this.moveHorizontalThread;
	}
}