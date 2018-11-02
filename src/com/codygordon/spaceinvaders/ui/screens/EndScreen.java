package com.codygordon.spaceinvaders.ui.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.codygordon.spaceinvaders.game.GameContainer;

public class EndScreen extends JPanel {
   
	private static final long serialVersionUID = 1L;

	public EndScreen(int score) {
		setBackground(Color.BLACK);
		setSize(1000, 650);
		setLayout(null);
		
		JLabel lblGameOver = new JLabel("Game over");
		lblGameOver.setSize(500, 100);
		lblGameOver.setLocation(new Point(getWidth() / 2 - lblGameOver.getWidth() / 2, 10));
		lblGameOver.setFont(new Font("Arial Black", Font.PLAIN, 80));
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setForeground(Color.RED);
		add(lblGameOver);
		
		JLabel lblYourScore = new JLabel("Your Score: " + score);
		lblYourScore.setFont(new Font("Arial", Font.PLAIN, 50));
		lblYourScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourScore.setForeground(Color.WHITE);
		lblYourScore.setBackground(new Color(255, 255, 255));
		lblYourScore.setSize(400, 75);
		lblYourScore.setLocation(new Point(getWidth() / 2 - lblYourScore.getWidth() / 2, 300));
		add(lblYourScore);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setSize(200, 50);
		btnMainMenu.setLocation(new Point(getWidth() / 2 - btnMainMenu.getWidth() / 2, 450));
		add(btnMainMenu);
		btnMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameContainer.getInstance().getMainFrame().showScreen(new HomeScreen());
			}
		});
	}
}