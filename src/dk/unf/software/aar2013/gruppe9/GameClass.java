package dk.unf.software.aar2013.gruppe9;

import java.util.ArrayList;
import java.util.Random;

import android.content.SharedPreferences;
import android.graphics.Rect;

public class GameClass {

	private GameView gameView;
	private float getWidth, getHeight;
	private Player player;
	private ArrayList<Mob> mob = new ArrayList<Mob>();
	private Random random = new Random();
	private boolean gameStarted = false;
	private int randomTimeLeft, randomTimeRight;
	private int speed, spawnTime, extraSpawnTime;
	private int speedCounter = 0, speedCounterSet = 15;
	private long updateTimeLeft = 0, updateTimeRight = 0;
	private SharedPreferences prefs;
	boolean isPaused = false;
	private int difficulty;
	private Rect r1 = new Rect();
	private Rect r2 = new Rect();

	public GameClass(GameView gameView, int difficulty, SharedPreferences sharedPreferences) {
		this.gameView = gameView;
		this.difficulty = difficulty;
		this.prefs = sharedPreferences;
		setDifficulty();
	}

	private void setDifficulty() {
		switch (difficulty) {
		case 0:
			speed = 5;
			extraSpawnTime = 1000;
			spawnTime = 2000;
			break;

		case 1:
			speed = 10;
			extraSpawnTime = 150;
			spawnTime = 1000;
			break;

		case 2:
			speed = 15;
			extraSpawnTime = 50;
			spawnTime = 750;
			break;

		default:
			break;
		}
	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public void Update() {
		if (!isPaused) {
			if (gameStarted == true) {
				spawnRightTick();
				spawnLeftTick();
				moveMob();
				mobDestroy();
				mobCollision();
				if (speedCounter >= speedCounterSet) {
					switch (difficulty) {
					case 0: // Easy
						speed += 1;
						extraSpawnTime -= 50;
						spawnTime -= 75;
						getPlayer().setTempNedims(1);
						break;
					case 1: // Medium
						speed += 1;
						extraSpawnTime -= 10;
						spawnTime -= 30;
						getPlayer().setTempNedims(2);
						break;
					case 2: // Hard
						speed += 1;
						extraSpawnTime -= 5;
						spawnTime -= 25;
						getPlayer().setTempNedims(3);
						break;

					default:
						break;
					}
					speedCounter = 0;

				}
			}
		}

	}

	private void mobDestroy() {
		for (int i = 0; i < mob.size(); i++) {
			if (mob.get(i).getPosX() < 0 - mob.get(i).getBitmap().getWidth() || mob.get(i).getPosX() > getWidth) {
				mob.remove(i);
				player.setScore(1);
				speedCounter++;
			}
		}
	}

	private void mobCollision() {
		for (int i = 0; i < mob.size(); i++) {
			if (touchesPlayer(i)) {
				mob.remove(i);
				// isPaused = true;
				// ((GameActivity) context).popup(player, this);
				player.gameOver();
				
				if (player.getVibrate()) {
					gameView.vibrate(50);
				}

				setDifficulty();
				speedCounter = 0;
				mob.clear();
			}
		}
	}

	private boolean touchesPlayer(int i) {
		Mob m = mob.get(i);

		r1.set((int) m.getPosX(), (int) m.getPosY(), (int) m.getPosX() + (int) m.getBitmap().getWidth(), (int) m.getPosY() + (int) m.getBitmap().getHeight());
		r2.set((int) player.getPosX(), (int) player.getPosY(), (int) player.getPosX() + (int) player.getBitmap().getWidth(), (int) player.getPosY()
				+ (int) player.getBitmap().getHeight());

		return Rect.intersects(r1, r2);

	}

	private void moveMob() {
		for (Mob data : mob) {
			data.setPosX(data.getPosX() - data.getDirection() + data.getSpeed());
		}
	}

	private void spawnLeftTick() {
		if (updateTimeLeft == 0) {
			updateTimeLeft = System.currentTimeMillis();
			randomTimeLeft = random.nextInt(spawnTime) + extraSpawnTime;
		}
		if ((System.currentTimeMillis() - updateTimeLeft) > randomTimeLeft) {
			addMob(1);
			updateTimeLeft = 0;
		}
	}

	private void spawnRightTick() {
		if (updateTimeRight == 0) {
			updateTimeRight = System.currentTimeMillis();
			randomTimeRight = random.nextInt(spawnTime) + extraSpawnTime;
		}
		if ((System.currentTimeMillis() - updateTimeRight) > randomTimeRight) {
			addMob(-1);
			updateTimeRight = 0;
		}
	}

	public void onTouch(float x, float y) {
		if (y > 0 + player.getBitmap().getHeight() / 2 && y < getHeight - player.getBitmap().getHeight() / 2) {
			player.setPosY(y - player.getBitmap().getHeight() / 2);
		}
	}

	public ArrayList<Mob> getMob() {
		return mob;
	}

	public void setScreenSize(int x, int y) {
		this.getWidth = x;
		this.getHeight = y;
		addPlayer();
	}

	private void addPlayer() {
		player = new Player(prefs);
		player.setBitmap(ImageLoader.getBitmap("player"));
		player.setPosX(getWidth / 2 - player.getBitmap().getWidth() / 2);
		player.setPosY(getHeight / 2 - player.getBitmap().getHeight() / 2);
		
		Starting();
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	private void addMob(int i) {
		Mob m = new Mob(i * speed, i, 1);
		m.setBitmap(ImageLoader.getBitmap("mob"));
		if (i == -1) {
			m.setPosX(getWidth);
		} else if (i == 1) {
			m.setPosX(0 - m.getBitmap().getWidth());
		}
		m.setPosY(randomY(m));
		mob.add(m);
	}

	private float randomY(Mob mob) {
		return (float) random.nextInt((int) getHeight - (int) mob.getBitmap().getHeight());
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the spawnTime
	 */
	public int getSpawnTime() {
		return spawnTime;
	}

	/**
	 * @param spawnTime
	 *            the spawnTime to set
	 */
	public void setSpawnTime(int spawnTime) {
		this.spawnTime = spawnTime;
	}

	/**
	 * @return the extraSpawnTime
	 */
	public int getExtraSpawnTime() {
		return extraSpawnTime;
	}

	/**
	 * @param extraSpawnTime
	 *            the extraSpawnTime to set
	 */
	public void setExtraSpawnTime(int extraSpawnTime) {
		this.extraSpawnTime = extraSpawnTime;
	}

	/**
	 * @return the speedCounter
	 */
	public int getSpeedCounter() {
		return speedCounter;
	}

	/**
	 * @param speedCounter
	 *            the speedCounter to set
	 */
	public void setSpeedCounter(int speedCounter) {
		this.speedCounter = speedCounter;
	}
	
	public void Starting() {
		player.setStartHighscore(player.getHighscore());
		player.setStartDeaths(player.getTotalDeaths());
		player.setStartTime(System.currentTimeMillis());
		player.setStartNedims(player.getNedims());
	}
	
	public void Stopping() {
		player.setHighscore(player.getTempHighscore());
		player.addTotalScore(player.getTempTotalScore());
		player.addTotalDeath(player.getTempDeaths());
		player.addTotalPlaytime(player.getStartTime());
		player.addNedims(player.getTempNedims());
		player.addAlltimeNedims(player.getTempNedims());
	}
	
	public boolean isDebug() {
		if (prefs.getBoolean("debug", false)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getBackground() {
		return prefs.getInt("background", R.drawable.b_blue);
	}
	
	

}
