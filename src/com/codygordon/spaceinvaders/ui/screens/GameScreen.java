package com.codygordon.spaceinvaders.ui.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.interfaces.GameObserver;
import com.codygordon.spaceinvaders.ui.screens.components.HealthDisplay;

public class GameScreen extends JPanel implements GameObserver {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<GameObject> gameObjectsToDraw = new ArrayList<GameObject>();
	
	private JLabel lblScore;
	private HealthDisplay healthDisplay;
	
	public GameScreen() {
		int width = GameContainer.getInstance().getMainFrame().getWidth();

		System.out.println("Creating game screen...");
		setBackground(Color.BLACK);
		setLayout(null);
		
		lblScore = new JLabel("Score: 000");
		lblScore.setSize(136, 26);
		lblScore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblScore.setLocation(new Point(0, 0));
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("Ariel", Font.PLAIN, 25));
		add(lblScore);
	
		healthDisplay = new HealthDisplay();
		healthDisplay.setSize(178, 58);
		healthDisplay.setLocation(width - (healthDisplay.getWidth() + 30), 0);
		add(healthDisplay);
				
		GameContainer.getInstance().getUpdater().registerObserver(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawGameObjects(g);
	}
	
	private void drawGameObjects(Graphics g) {
		try {
			for(GameObject obj : gameObjectsToDraw) {
				obj.update(g);
			}
		} catch(Exception e) { }
	}
	
	public void updateScore() {
		int score =  GameContainer.getInstance().getController().getScore();
		lblScore.setText("Score: " + score);
	}
	
	public void updateLives() {
		int lives = GameContainer.getInstance().getController().getCurrentLives();
		healthDisplay.setLives(lives);
	}
	
	public ArrayList<GameObject> getObjectsToDraw() {
		return this.gameObjectsToDraw;
	}

	@Override
	public void update() {
		repaint();
	}
}