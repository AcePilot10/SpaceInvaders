package com.codygordon.spaceinvaders.builders;

import com.codygordon.spaceinvaders.game.GameLoop;

public class GameLoopBuilder {

	private boolean renderTime = true;
	private double fps = 60;
	private double ups = 1;
	
	public GameLoopBuilder setRenderFrames(boolean renderTime) {
		this.renderTime = renderTime;
		return this;
	}
	
	public GameLoopBuilder setFps(double fps) {
		this.fps = fps;
		return this;
	}
	
	public GameLoopBuilder setUps(double ups) {
		this.ups = ups;
		return this;
	}
	
	public GameLoop build() {
		GameLoop loop = new GameLoop();
		loop.fps = fps;
		loop.renderTime = renderTime;
		loop.ups = ups;
		return loop;
	}
}