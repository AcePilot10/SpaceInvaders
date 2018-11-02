package com.codygordon.spaceinvaders.game;

import com.codygordon.spaceinvaders.builders.BarrierBuilder;
import com.codygordon.spaceinvaders.builders.EnemyWaveBuilder;
import com.codygordon.spaceinvaders.builders.GameBuilder;
import com.codygordon.spaceinvaders.builders.GameLoopBuilder;
import com.codygordon.spaceinvaders.builders.PlayerBuilder;
import com.codygordon.spaceinvaders.controllers.GameController;
import com.codygordon.spaceinvaders.ui.GameFrame;
import com.codygordon.spaceinvaders.ui.screens.GameScreen;
import com.codygordon.spaceinvaders.ui.screens.HomeScreen;

public class GameContainer {

	private static GameContainer instance;

	private double fps = 60;
	private double ups = 20;
	private boolean renderTime = false;
	
	private GameUpdater updater;
	private GameLoop loop;
	private GameFrame mainFrame;
	private GameController controller;
	
	protected BarrierBuilder barrierBuilder = GameBuilder.getBarrierBuilder();
	protected PlayerBuilder playerBuilder = GameBuilder.getPlayerBuilder();
	protected EnemyWaveBuilder enemyWaveBuilder = GameBuilder.getEnemyWaveBuilder();
	
	public GameContainer() {
		instance = this;
		updater = new GameUpdater();
		initFrame();
	}
	
	public static GameContainer getInstance() {
		return instance;
	}
	
	private void initFrame() {
		mainFrame = new GameFrame();
		mainFrame.showScreen(new HomeScreen());
	}
	
	private void initLoop() {
		GameLoopBuilder builder = new GameLoopBuilder();
		loop = builder.setRenderFrames(renderTime)
				.setFps(fps)
				.setUps(ups)
				.build();
		loop.run();
	}
	
	public void startGame() {
		reset();
		System.out.println("Starting game...");
		GameScreen screen = new GameScreen();
		controller = new GameController(screen);
		createBarriers();
		createPlayer();
		createEnemyWave();
		mainFrame.showScreen(screen);
		initLoop();
	}
	
	private void reset() {
		if(loop != null) {
			loop.getThread().interrupt();
		}
		
		if(controller != null) {
			controller.destroy();
		}
	}
	
	public void createBarriers() { }
	public void createPlayer() { }
	public void createEnemyWave() { }
	
	public void pauseGameLoop() {
		loop.running = false;
	}
	
	public void resumeGameLoop() {
		loop.running = true;
	}
	
	public GameUpdater getUpdater() {
		return this.updater;
	}
	
	public GameFrame getMainFrame() {
		return this.mainFrame;
	}
	
	public GameController getController() {
		return this.controller;
	}
	
	public void setFps(double fps) {
		this.fps = fps;
	}
	
	public void setUps(double ups) {
		this.ups = ups;
	}
}