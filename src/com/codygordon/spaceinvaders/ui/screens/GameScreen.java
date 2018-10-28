package com.codygordon.spaceinvaders.ui.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.interfaces.GameObserver;

public class GameScreen extends JPanel implements GameObserver {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<GameObject> gameObjectsToDraw = new ArrayList<GameObject>();
	
	public GameScreen() {
				
		System.out.println("Creating game screen...");
		setBackground(Color.BLACK);
		
		JLabel lblGame = new JLabel("Game");
		lblGame.setForeground(Color.WHITE);
		add(lblGame);
	
		GameContainer.getInstance().getUpdater().registerObserver(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			for(GameObject obj : gameObjectsToDraw) {
				obj.update(g);
			}
		} catch(Exception e) { }
	}
	
	public ArrayList<GameObject> getObjectsToDraw() {
		return this.gameObjectsToDraw;
	}

	@Override
	public void update() {
		repaint();
	}
}