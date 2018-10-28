package com.codygordon.spaceinvaders.game;

public class Program {
	
	private static Program instance;
	
	private GameContainer game;
	
	public static void main(String[] args) {
		System.out.println("Space Invaders is starting...");
		new Program();
	}
	
	public Program() {
		instance = this;
		initGame();
	}	
	
	private void initGame() {
		game = new SpaceInvaders();
	}
	
	public Program getInstance() {
		return instance;
	}
	
	public GameContainer getGame() {
		return this.game;
	}
}