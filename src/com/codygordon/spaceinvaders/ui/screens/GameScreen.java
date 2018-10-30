package com.codygordon.spaceinvaders.ui.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
				
		System.out.println("Creating game screen...");
		setBackground(Color.BLACK);
		setLayout(null);
		
		JLabel lblGame = new JLabel("Game");
		lblGame.setBounds(212, 11, 47, 20);
		lblGame.setForeground(Color.WHITE);
		add(lblGame);
		
		lblScore = new JLabel("Score: 000");
		lblScore.setBounds(10, 37, 122, 33);
		lblScore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("Ariel", Font.PLAIN, 25));
		add(lblScore);
	
		healthDisplay = new HealthDisplay();
		healthDisplay.setSize(178, 58);
		int width = GameContainer.getInstance().getMainFrame().getWidth();
		healthDisplay.setLocation(width - (healthDisplay.getWidth() + 30), 0);
		add(healthDisplay);
		
//		BackgroundImagePanel background = new BackgroundImagePanel();
//		background.setVisible(true);
//		background.setSize(GameContainer.getInstance().getMainFrame().getWidth(),
//						   GameContainer.getInstance().getMainFrame().getHeight());
//		background.setVisible(true);
//		add(background);
				
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