package com.codygordon.spaceinvaders.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.codygordon.spaceinvaders.interfaces.KeyEventListener;

public class FrameKeyListener extends KeyAdapter {

	private static FrameKeyListener instance;
	
	private List<KeyEventListener> listeners = new ArrayList<KeyEventListener>();
	
	public FrameKeyListener() {
		instance = this;
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Detected key pressed");
		for(KeyEventListener listener : listeners) {
			listener.KeyPressed(key);
		}
	}	
	
	public void addListener(KeyEventListener listener) {
		System.out.println("Added key listener: " + listener.getClass().getName()); 
		listeners.add(listener);
	}
	
	public static FrameKeyListener getInstance() {
		return instance;
	}
}