package com.codygordon.spaceinvaders.controllers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Timer;
import java.util.TimerTask;

import com.codygordon.spaceinvaders.builders.EnemyWaveBuilder;
import com.codygordon.spaceinvaders.enemies.EnemyWave;
import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;
import com.codygordon.spaceinvaders.gameobjects.player.Player;
import com.codygordon.spaceinvaders.gameobjects.projectiles.Projectile;
import com.codygordon.spaceinvaders.input.FrameKeyListener;
import com.codygordon.spaceinvaders.input.GameKeyListener;
import com.codygordon.spaceinvaders.ui.screens.GameScreen;

public class GameController {

	public static final int PROJECTILE_SPEED = 10;
	public static final long SHOOT_DELAY = 800;
	
	private Player player;
	private GameScreen view;
	private GameKeyListener listener;
	
	private ArrayList<GameObject> physicsObjects = new ArrayList<GameObject>();
	private ArrayList<GameObject> objectsToDestroy = new ArrayList<GameObject>();	
	
	private EnemyWave currentWave;
	
	private boolean canShoot = true;
	
	public GameController(GameScreen screen) {
		this.view = screen;
		
		player = new Player();
		initGameObject(player);
		
		addBarriers();
		
		Enemy testEnemy1 = new Enemy();
		EnemyWaveBuilder waveBuilder = new EnemyWaveBuilder();
		EnemyWave wave = waveBuilder.setRows(1)
						.addEnemy(1, testEnemy1)
						.build();
		spawnEnemyWave(wave);
		
		listener = new GameKeyListener();
		FrameKeyListener.getInstance().addListener(listener);
	}
	
	public void spawnEnemyWave(EnemyWave wave) {
		currentWave = wave;
		for(GameObject enemy : currentWave.getEnemeis().values()) {
			initGameObject(enemy);
		}
	}
	
	private void addBarriers() {
		Barrier barrier1 = new Barrier();
		int frameHeight = GameContainer.getInstance().getMainFrame().getHeight();
		int barrierHeight = Barrier.HEIGHT;
		int barrierY = frameHeight - (barrierHeight * 2) * 2;
		barrier1.setLocation(new Point(85, barrierY));
		initGameObject(barrier1);
	}
	
	public void shoot() {
		if(canShoot) {
			int spawnX = (int)player.getLocation().getX() + (25 / 2);
			int spawnY = (int)player.getLocation().getY() - (50 / 2) - 5;
			Projectile projectile = new Projectile(PROJECTILE_SPEED);
			projectile.setLocation(new Point(spawnX, spawnY));
			view.getObjectsToDraw().add(projectile);
			initGameObject(projectile);
			startShootDelay();
		} 
	}
	
	private void startShootDelay() {
		canShoot = false;
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				canShoot = true;
			}
		}, SHOOT_DELAY);
	}
	
	public void update() {
		updatePhysics();
		destroyGameObjects();
	}
	
	private void destroyGameObjects() {
		physicsObjects.removeAll(objectsToDestroy);
		view.getObjectsToDraw().removeAll(objectsToDestroy);
		objectsToDestroy.clear();
	}
	
	private void updatePhysics() {
		try {
			for(GameObject obj : physicsObjects) {
				for(GameObject otherObj : physicsObjects) {
					if(otherObj != obj) { 
						if(obj.getCollider().intersects(otherObj.getCollider())) {
							obj.onCollision(otherObj);
						}
					}
				}
			}
		} catch(ConcurrentModificationException e) { }
	}
	
	public void initGameObject(GameObject obj) {
		physicsObjects.add(obj);
		view.getObjectsToDraw().add(obj);
	}
	
	public void destroyGameObject(GameObject obj) {
		objectsToDestroy.add(obj);
	}
	
	public Player getPlayer() {
		return this.player;
	}
}