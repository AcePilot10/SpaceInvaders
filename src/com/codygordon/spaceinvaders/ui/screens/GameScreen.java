package com.codygordon.spaceinvaders.ui.screens;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameScreen extends JPanel {

	public GameScreen() {
		System.out.println("Creating game screen...");
		setBackground(Color.BLACK);
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		
	}
}