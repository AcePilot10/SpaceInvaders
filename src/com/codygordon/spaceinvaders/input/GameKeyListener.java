package com.codygordon.spaceinvaders.input;

import java.awt.event.KeyEvent;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.player.Player;
import com.codygordon.spaceinvaders.interfaces.KeyEventListener;

public class GameKeyListener implements KeyEventListener {

	@Override
	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			 GameContainer.getInstance().getController().getPlayer().move(Player.MOVE_LEFT);
		} else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			 GameContainer.getInstance().getController().getPlayer().move(Player.MOVE_RIGHT); 
		} else if(key == KeyEvent.VK_SPACE) {
			GameContainer.getInstance().getController().shoot();
		}
	}
}