package com.codygordon.spaceinvaders.game;

public class GameLoop implements Runnable {

	public boolean running = true;
	
	public boolean renderTime = false;
	public double fps = 60;
	public double ups = 1;
	
	private Thread thread;
	
	@Override
	public void run() {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				long initialTime = System.nanoTime();
				final double timeU = 1000000000 / ups;
				final double timeF = 1000000000 / fps;
				double deltaU = 0, deltaF = 0;
				int frames = 0, ticks = 0;
				long timer = System.currentTimeMillis();
			    while (running) {

			        long currentTime = System.nanoTime();
			        deltaU += (currentTime - initialTime) / timeU;
			        deltaF += (currentTime - initialTime) / timeF;
			        initialTime = currentTime;

			        if (deltaU >= 1) {
			        	tick();
			            ticks++;
			            deltaU--;
			        }

			        if (deltaF >= 1) {
			        	updateFrames();
			            frames++;
			            deltaF--;
			        }

			        if (System.currentTimeMillis() - timer > 1000) {
			            if (renderTime) {
			                System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
			            }
			            frames = 0;
			            ticks = 0;
			            timer += 1000;
			        }
			    }	
			}
		};
		thread = new Thread(r);
		thread.start();
	}
	
	public void tick() {
		
	}
	
	public void updateFrames() {
		GameContainer.getInstance().getUpdater().update();
	}
	
	public Thread getThread() {
		return this.thread;
	}
}