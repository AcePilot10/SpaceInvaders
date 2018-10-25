package com.codygordon.spaceinvaders.game;

import java.util.ArrayList;
import java.util.List;

import com.codygordon.spaceinvaders.interfaces.GameObserver;

public class GameUpdater {
	
	//Updates UI
	
	private List<GameObserver> observers;
	
	public GameUpdater() {
		observers = new ArrayList<GameObserver>();
	}
	
	public void registerObserver(GameObserver observer) {
		this.observers.add(observer);
	}
	
	public void unregisterObserver(GameObserver observer) {
		this.observers.remove(this.observers.indexOf(observer));
	}
	
	public void update() {
		for(GameObserver observer : observers) {
			observer.update();
		}
	}
}