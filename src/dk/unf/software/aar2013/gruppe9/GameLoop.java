package dk.unf.software.aar2013.gruppe9;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class GameLoop extends Thread {

	private GameView view;
	private GameClass gameClass;
	private boolean running = false;

	private final long FPS = 60;

	public GameLoop(GameView view, GameClass gameClass) { // Modtag og gem context
		this.gameClass = gameClass;
		this.view = view;
	}
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	@SuppressLint("WrongCall")
	public void run() {
		long ticksPS = 1000 / FPS;
		long startTime;
		long sleepTime;
		gameClass.setGameStarted(true);
		while (running) {
			Canvas c = null;
			startTime = System.currentTimeMillis();
			try {
				c = view.getHolder().lockCanvas();
				synchronized (view.getHolder()) {
					gameClass.Update();
					view.onDraw(c);
				}
			} finally {
				if (c != null) {
					view.getHolder().unlockCanvasAndPost(c);
				}
			}
			sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
			try {
				if (sleepTime > 0) {
					sleep(sleepTime);
				} else {
					sleep(10);
				}
			} catch (Exception e) {

			}
		}
	}

}
