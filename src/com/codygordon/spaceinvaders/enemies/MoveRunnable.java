package com.codygordon.spaceinvaders.enemies;

public class MoveRunnable implements Runnable {

	private IEnemyMove enemyMove;
	private long delay;
	
	public MoveRunnable(IEnemyMove move, long delay) {
		this.enemyMove = move;
		this.delay = delay;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(delay);
				enemyMove.move();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}