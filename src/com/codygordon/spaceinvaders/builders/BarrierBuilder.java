package com.codygordon.spaceinvaders.builders;

import java.awt.Color;
import java.awt.Point;

import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;

public class BarrierBuilder {

	private int width;
	private int height;
	private Point location;
	private Color color;
	
	public BarrierBuilder setWidth(int width) {
		this.width = width;
		return this;
	}
	
	public BarrierBuilder setHeight(int height) {
		this.height = height;
		return this;
	}
	
	public BarrierBuilder setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public BarrierBuilder setLocation(Point location) {
		this.location = location;
		return this;
	}
	
	public Barrier build() {
		Barrier barrier = new Barrier();
		barrier.setSize(width, height);
		barrier.setColor(color);
		barrier.setLocation(location);
		return barrier;
	}	
}