package com.codygordon.spaceinvaders.ui.screens.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HealthDisplay extends JPanel {


	private static final long serialVersionUID = 1L;

	private int lives = 3;

	public HealthDisplay() {
		setOpaque(false);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int width = 50;
		int height = 50;
		int xPadding = 10;
		int midX = getWidth() / 2 - width / 2;
		int leftX = midX - width - xPadding;
		int rightX = midX + width + xPadding;

		g.setColor(Color.RED);
		if(lives == 3) {
			g.fillRect(leftX, 0, width, height);
			g.fillRect(midX, 0, width, height);
			g.fillRect(rightX, 0, width, height);
		} else if(lives == 2) {
			g.fillRect(leftX, 0, width, height);
			g.fillRect(midX, 0, width, height);
		} else if(lives == 1) {
			g.fillRect(leftX, 0, width, height);
		}
	}
	
	public void setLives(int lives) {
		this.lives = lives;
		repaint();
	}
}