package dk.unf.software.aar2013.gruppe9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

@SuppressLint("ViewConstructor")
public class GameView extends SurfaceView implements OnTouchListener {

	private SurfaceHolder holder;
	private GameLoop gameLoop;
	private GameClass gameClass;
	private Paint paint;
	private Object vibrate;
	private Rect rect = new Rect();
	private Bitmap bm;
	private Bitmap newBm;
	private boolean firstRun = true;
	private boolean isDebug;

	public GameView(Context context, Object vibrate, int difficulty, SharedPreferences sharedPreferences) {
		super(context);
		this.vibrate = vibrate;
		setOnTouchListener(this);
		gameClass = new GameClass(this, difficulty, sharedPreferences);
		gameLoop = new GameLoop(this, gameClass); // Sender this (context) med
		paint = new Paint();
		paint.setTextSize(20);
		paint.setColor(android.graphics.Color.WHITE);

		Options opt = new Options();

		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inScaled = false;
		bm = BitmapFactory.decodeResource(getResources(), gameClass.getBackground(), opt);

		holder = getHolder();

		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoop.setRunning(false);
				while (retry) {
					try {
						gameLoop.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@SuppressLint("WrongCall")
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				gameLoop.setRunning(true);
				gameLoop.start();
				Canvas c = holder.lockCanvas(null);
				onDraw(c);
				holder.unlockCanvasAndPost(c);

			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

			}
		});

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (canvas == null) {
			return;
		}
		
		if (firstRun) {
			rect.set(0, 0, canvas.getWidth(), canvas.getHeight());
			newBm = Bitmap.createScaledBitmap(bm, getWidth(), getHeight(), false);
			firstRun = false;
			isDebug = gameClass.isDebug();
		}

		// Tegner baggrund
		drawBackground(canvas);

		// Tegner player
		canvas.drawBitmap(gameClass.getPlayer().getBitmap(), gameClass.getPlayer().getPosX(), gameClass.getPlayer().getPosY(), null);

		// Tegner alle characters
		for (Character chars : gameClass.getMob()) {
			canvas.drawBitmap(chars.getBitmap(), chars.getPosX(), chars.getPosY(), null);
		}

		canvas.drawText("Max Score: " + gameClass.getPlayer().getTempMaxScore(), 20, 30, paint);
		canvas.drawText("Score: " + gameClass.getPlayer().getScore(), 20, 50, paint);
		canvas.drawText("Nedims: " + (gameClass.getPlayer().getTempNedims() + gameClass.getPlayer().getStartNedims()), 20, getHeight() - 30, paint);
		
		// Tegner debug tekst hvis aktiveret
		if (isDebug) {
			canvas.drawText("Mobs: " + gameClass.getMob().size(), 20, 70, paint);
			canvas.drawText("Speed: " + gameClass.getSpeed(), 20, 90, paint);
			canvas.drawText("Spawn: " + gameClass.getExtraSpawnTime() + " - " + (gameClass.getExtraSpawnTime() + gameClass.getExtraSpawnTime()), 20, 110, paint);
			canvas.drawText("Count: " + gameClass.getSpeedCounter(), 20, 130, paint);
		}
	}

	private void drawBackground(Canvas canvas) {
		canvas.drawBitmap(newBm, 0, 0, null);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		gameClass.onTouch(event.getX(), event.getY());
		return true;
	}

	
	
	public void pause() {
		//gameLoop.setRunning(false);
		gameClass.Stopping();
	}

	public void resume() {
		gameLoop.setRunning(true);
		gameLoop = new GameLoop(this, gameClass);
	}

	public void vibrate(int ms) {
		Vibrator v = (Vibrator) vibrate;
		v.vibrate(ms);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		gameClass.setScreenSize(w, h);
	}

	public void backPressed() {
		//gameLoop.setRunning(false);
	}

}
