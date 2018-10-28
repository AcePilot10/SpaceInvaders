package com.codygordon.spaceinvaders.controllers;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.codygordon.spaceinvaders.enemies.EnemyWave;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;
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
	private EnemyWave enemyWavePreset;
		
	public GameController(GameScreen screen) {
		this.view = screen;				
		listener = new GameKeyListener();
		FrameKeyListener.getInstance().addListener(listener);
	}
	
	public void spawnNextWave() {
		EnemyWave newEnemyWave = enemyWavePreset.clone();
		spawnEnemyWave(newEnemyWave);
	}
	
	public void createPlayer(Player player) {
		this.player = player;
		initGameObject(player);
	}
	
	public void createBarrier(Barrier barrier) {
		initGameObject(barrier);
	}
	
	public void spawnEnemyWave(EnemyWave wave) {
		enemyWavePreset = wave.clone();
		this.currentWave = wave;
		this.currentWave.moveHorizontalDelay = 500;
		this.currentWave.init();
	}
	
	public void update() {
		updatePhysics();
		destroyGameObjects();
	}
	
	public synchronized void initGameObject(GameObject obj) {
		physicsObjects.add(obj);
		view.getObjectsToDraw().add(obj);
	}
	
	public void destroyGameObject(GameObject obj) {
		objectsToDestroy.add(obj);
	}
	
	private synchronized void destroyGameObjects() {
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
	
	public Player getPlayer() {
		return this.player;
	}
	
	public EnemyWave getCurrentEnemyWave() {
		return this.currentWave;
	}
}