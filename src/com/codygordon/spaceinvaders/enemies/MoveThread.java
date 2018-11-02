package com.codygordon.spaceinvaders.enemies;

public class MoveThread extends Thread implements Runnable {

	private IEnemyMove enemyMove;
	public long delay;
	public boolean running = true;
	
	public MoveThread(IEnemyMove move, long delay) {
		this.enemyMove = move;
		this.delay = delay;
	}
	
	@Override
	public void run() {
		try {
			while(running) {
				Thread.sleep(delay);
				enemyMove.move();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}