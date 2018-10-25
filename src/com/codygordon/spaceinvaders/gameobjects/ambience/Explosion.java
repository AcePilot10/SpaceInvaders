package com.codygordon.spaceinvaders.gameobjects.ambience;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;

public class Explosion extends GameObject {

	public static final long REPEAT_DELAY = 150;
	
	private Timer effectTimer;
	private Timer destroyTimer;
	private long duration;
	private int growFactor;
	
	private int updates = 0;
	private int peakUpdate;
	
	public Explosion(long duration, int growFactor, int startingWidth, int startingHeight) {
		this.duration = duration;
		this.growFactor = growFactor;
		effectTimer = new Timer();
		destroyTimer = new Timer();
		peakUpdate = (int)(duration / REPEAT_DELAY) / 2;
		collider.width = startingWidth;
		collider.height = startingHeight;
	}
	
	public void start() {
		effectTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				update();
			}
		}, 0, REPEAT_DELAY);
		
		destroyTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				destroy();
			}
		}, duration);
	}
	
	private void update() {
		updates++;
		if(updates < peakUpdate) {
			collider.width += growFactor;
			collider.height += growFactor;
		} else {
			collider.width -= growFactor;
			collider.height -= growFactor;
		}
	}
	
	private void destroy() {
		effectTimer.cancel();
		GameContainer.getInstance().getController().destroyGameObject(this);
	}
	
	@Override
	public void update(Graphics g) {
		super.update(g);
		g.setColor(Color.RED);
		g.fillRect(location.x, 
				location.y,
				collider.width, 
				collider.height);
	}
}