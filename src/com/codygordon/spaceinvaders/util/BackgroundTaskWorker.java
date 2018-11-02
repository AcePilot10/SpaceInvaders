package com.codygordon.spaceinvaders.util;

public class BackgroundTaskWorker {

	public static void doInBackground(BackgroundTask task) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				task.doInBackground();
			}
		};
		Thread thread = new Thread(r);
		thread.setName("Background Task Worker");
		thread.start();
	}	
}