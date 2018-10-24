package com.codygordon.spaceinvaders.gameobjects.enemies;

import com.codygordon.spaceinvaders.gameobjects.GameObject;

import java.awt.*;

public class Enemy extends GameObject {
	
	public int destroyReward;
	
	@Override
	public void update(Graphics g) {
		super.update(g);
	}
	
	public int getDestroyReward() {
		return this.destroyReward;
	}
}