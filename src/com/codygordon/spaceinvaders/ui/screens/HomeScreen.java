package com.codygordon.spaceinvaders.ui.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.codygordon.spaceinvaders.game.GameContainer;

public class HomeScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	public HomeScreen() {
		System.out.println("Creating home screen...");
		setBackground(Color.BLACK);
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Space Invaders");
		lblTitle.setBounds(311, 0, 293, 52);
		lblTitle.setAlignmentX(CENTER_ALIGNMENT);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 43));
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setLocation(251, 249);
		btnPlay.setAlignmentX(CENTER_ALIGNMENT);
		btnPlay.setSize(353, 36);
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playButtonClicked();
			}
		});
		add(btnPlay);
	}
	
	private void playButtonClicked() {
		GameContainer.getInstance().startGame();
	}
}