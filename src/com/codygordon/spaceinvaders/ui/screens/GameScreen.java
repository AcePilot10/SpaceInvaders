package com.codygordon.spaceinvaders.ui.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.interfaces.GameObserver;

public class GameScreen extends JPanel implements GameObserver {

	private ArrayList<GameObject> gameObjectsToDraw = new ArrayList<GameObject>();
	
	public GameScreen() {
		
		//addKeyListener(new GameKeyListener());
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("Key pressed");
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Key pressed");
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		GameContainer.getInstance().getUpdater().registerObserver(this);
		
		setFocusable(true);
		requestFocus();
		
		System.out.println("Creating game screen...");
		setBackground(Color.BLACK);
		
		JLabel lblGame = new JLabel("Game");
		lblGame.setForeground(Color.WHITE);
		add(lblGame);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(GameObject obj : gameObjectsToDraw) {
			obj.update(g);
		}
	}
	
	public ArrayList<GameObject> getObjectsToDraw() {
		return this.gameObjectsToDraw;
	}

	@Override
	public void update() {
		repaint();
	}
}