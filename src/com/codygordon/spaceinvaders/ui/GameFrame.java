package com.codygordon.spaceinvaders.ui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.codygordon.spaceinvaders.gameobjects.Player;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = -8215680680292053450L;
	
	private JPanel contentPane;
	
	private Player player;
	
	public GameFrame() {
		System.out.println("Creating game frame...");
		
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		setVisible(true);
	}
	
	public void showScreen(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().repaint();
		getContentPane().revalidate();
	}
}