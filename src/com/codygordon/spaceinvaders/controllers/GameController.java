package com.codygordon.spaceinvaders.controllers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.codygordon.spaceinvaders.enemies.EnemyWave;
import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;
import com.codygordon.spaceinvaders.gameobjects.player.Player;
import com.codygordon.spaceinvaders.input.FrameKeyListener;
import com.codygordon.spaceinvaders.input.GameKeyListener;
import com.codygordon.spaceinvaders.ui.screens.GameScreen;

public class GameController {
	
	private Player player;
	private GameScreen view;
	private GameKeyListener listener;
	
	private ArrayList<GameObject> physicsObjects = new ArrayList<GameObject>();
	private ArrayList<GameObject> objectsToDestroy = new ArrayList<GameObject>();	
	
	private EnemyWave currentWave;
		
	public GameController(GameScreen screen) {
		this.view = screen;				
		listener = new GameKeyListener();
		FrameKeyListener.getInstance().addListener(listener);
	}
	
	public void spawnEnemyWave(EnemyWave wave) {
		this.currentWave = wave;
		this.currentWave.moveHorizontalDelay = 500;
		this.currentWave.init();
	}
	
	public void createPlayer() {
		player = new Player();
		player.setSpeed(15);
		initGameObject(player);
	}
	
	public void addBarriers() {
		Barrier barrier1 = new Barrier();
		int frameHeight = GameContainer.getInstance().getMainFrame().getHeight();
		int barrierHeight = Barrier.HEIGHT;
		int barrierY = frameHeight - (barrierHeight * 2) * 2;
		barrier1.setLocation(new Point(85, barrierY));
		initGameObject(barrier1);
	}
	
	public void shoot() {
		player.shoot();
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
	
	public boolean isAlive(Enemy enemy) {
		return view.getObjectsToDraw().contains(enemy);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public EnemyWave getCurrentEnemyWave() {
		return this.currentWave;
	}
}