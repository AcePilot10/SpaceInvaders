package com.codygordon.spaceinvaders.game;

import com.codygordon.spaceinvaders.builders.GameLoopBuilder;
import com.codygordon.spaceinvaders.controllers.GameController;
import com.codygordon.spaceinvaders.difficulty.GameDifficultyManager;
import com.codygordon.spaceinvaders.enemies.EnemyWave;
import com.codygordon.spaceinvaders.ui.GameFrame;
import com.codygordon.spaceinvaders.ui.screens.GameScreen;
import com.codygordon.spaceinvaders.ui.screens.HomeScreen;

public class GameContainer {

	/** Game Loop Settings **/
	public static final double FPS = 60;
	public static final double UPS = 60;
	public static final boolean RENDER_TIME = false;
	
	private static GameContainer instance;
	
	/** Reference Variables **/
	private GameUpdater updater;
	private GameLoop loop;
	private GameFrame mainFrame;
	private GameController controller;
	private GameDifficultyManager difficultyManager;
	
	public GameContainer() {
		instance = this;
		updater = new GameUpdater();
		initFrame();
	}
	
	public static GameContainer getInstance() {
		return instance;
	}
	
	/** Init Methods **/
	private void initFrame() {
		mainFrame = new GameFrame();
		mainFrame.showScreen(new HomeScreen());
	}
	
	private void initLoop() {
		GameLoopBuilder builder = new GameLoopBuilder();
		loop = builder.setRenderFrames(RENDER_TIME)
				.setFps(FPS)
				.setUps(UPS)
				.build();
		loop.run();
	}
	
	public void startGame() {
		System.out.println("Starting game...");
		GameScreen screen = new GameScreen();
		controller = new GameController(screen);
		mainFrame.showScreen(screen);
		initLoop();
		
		EnemyWave wave = new EnemyWave(2, 10);
		controller.spawnEnemyWave(wave);
	}
	
	/** Getters **/
	public GameUpdater getUpdater() {
		return this.updater;
	}
	
	public GameFrame getMainFrame() {
		return this.mainFrame;
	}
	
	public GameController getController() {
		return this.controller;
	}

	public GameDifficultyManager getDifficultyManager() {
		return difficultyManager;
	}
}