package com.codygordon.spaceinvaders.util;

import java.awt.Point;

import com.codygordon.spaceinvaders.game.GameContainer;

public class ScreenUtil {

	public static boolean isOffScreen(Point location) {
		int width = GameContainer.getInstance().getMainFrame().getWidth();
		int height = GameContainer.getInstance().getMainFrame().getHeight();
		int x = location.x;
		int y = location.y;
		if(y <= 0) {
			return true;
		} else if(y >= height) {
			return true;
		} else if(x <= 0) {
			return true;
		} else if(x >= width) {
			return true;
		} else {
			return false;
		}
	}
}