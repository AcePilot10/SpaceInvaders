package com.codygordon.spaceinvaders.ui.screens.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.codygordon.spaceinvaders.assets.SpriteLoader;
import com.codygordon.spaceinvaders.game.GameContainer;

public class BackgroundImagePanel extends JPanel {
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage imgRaw = SpriteLoader.getSprite("space_background.png");
		int width = GameContainer.getInstance().getMainFrame().getWidth();
		int height = GameContainer.getInstance().getMainFrame().getHeight();
		BufferedImage imgResized = SpriteLoader.resize(imgRaw, width, height);
		g.drawImage(imgResized, 0, 0, null);
		System.out.println("Drew background");
	}
}