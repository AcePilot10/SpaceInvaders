package com.codygordon.spaceinvaders.controllers;

import java.util.ArrayList;

import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.ui.screens.GameScreen;

public class GameController {

	private ArrayList<GameObject> gameObjects;
	
	private GameScreen gameScreen;
	
	public GameController(GameScreen screen) {
		gameObjects = new ArrayList<GameObject>();
		this.gameScreen = screen;
	}
	
	public void addGameObject(GameObject obj) {
		gameObjects.add(obj);
	}
	
	public void removeGameObject(GameObject obj) {
		gameObjects.remove(gameObjects.indexOf(obj));
	}
	
	public GameScreen getGameScreen() {
		return this.gameScreen;
	}
}