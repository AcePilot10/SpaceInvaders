package com.codygordon.spaceinvaders.controllers;

import java.awt.Point;
import java.util.ArrayList;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;
import com.codygordon.spaceinvaders.gameobjects.player.Player;
import com.codygordon.spaceinvaders.gameobjects.projectiles.Projectile;
import com.codygordon.spaceinvaders.input.FrameKeyListener;
import com.codygordon.spaceinvaders.input.GameKeyListener;
import com.codygordon.spaceinvaders.ui.screens.GameScreen;

public class GameController {

	public static final int PROJECTILE_SPEED = 10;
	
	private Player player;
	private GameScreen view;
	private GameKeyListener listener;
	
	private ArrayList<GameObject> physicsObjects = new ArrayList<GameObject>();
		
	public GameController(GameScreen screen) {
		this.view = screen;
		
		player = new Player();
		initGameObject(player);
		
		addBarriers();
		
		listener = new GameKeyListener();
		FrameKeyListener.getInstance().addListener(listener);
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
		int spawnX = (int)player.getLocation().getX() + (25 / 2);
		int spawnY = (int)player.getLocation().getY() - (50 / 2) - 5;
		Projectile projectile = new Projectile(PROJECTILE_SPEED);
		projectile.setLocation(new Point(spawnX, spawnY));
		view.getObjectsToDraw().add(projectile);
		initGameObject(projectile);
	}
	
	public void updatePhysics() {
		for(GameObject obj : physicsObjects) {
			for(GameObject otherObj : physicsObjects) {
				if(otherObj == obj) return;
				if(obj.getCollider().intersects(otherObj.getCollider())) {
					obj.onCollision(otherObj);
				}
			}
		}
	}
	
	public void initGameObject(GameObject obj) {
		physicsObjects.add(obj);
		view.getObjectsToDraw().add(obj);
	}
	
	public void destroyGameObject(GameObject obj) {
		physicsObjects.remove(physicsObjects.indexOf(obj));
		view.getObjectsToDraw().remove(view.getObjectsToDraw().indexOf(obj));
	}
	
	public Player getPlayer() {
		return this.player;
	}
}