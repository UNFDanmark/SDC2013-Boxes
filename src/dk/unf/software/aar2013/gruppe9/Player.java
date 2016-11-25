package dk.unf.software.aar2013.gruppe9;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Player extends Character {

	private int tempScore = 0;
	private SharedPreferences prefs;
	private Editor edit;
	private int startTime, startDeaths, startNedims, tempDeaths, tempNedims, tempMaxScore = 0, tempHighscore, startHighScore, tempTotalScore;

	public Player(SharedPreferences prefs) {
		this.prefs = prefs;
		edit = this.prefs.edit();
	}

	public int getTempTotalScore() {
		return this.tempTotalScore;
	}

	public int getStartHighScore() {
		return this.startHighScore;
	}

	public void setStartHighscore(int highScore) {
		this.startHighScore = highScore;
	}

	public void setTempDeaths() {
		tempDeaths++;
	}

	public int getTempDeaths() {
		return tempDeaths;
	}

	public void setTempNedims(int i) {
		tempNedims += i;
	}

	public void setStartDeaths(int deaths) {
		this.startDeaths = deaths;
	}

	public void setStartTime(long time) {
		this.startTime = (int) time;
	}

	public void setStartNedims(int nedims) {
		this.startNedims = nedims;
	}

	public int getStartDeaths() {
		return startDeaths;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getStartNedims() {
		return startNedims;
	}

	public void setTempHighscore(int highscore) {
		this.tempHighscore = highscore;
	}
	
	public int getTempHighscore() {
		return tempHighscore;
	}

	public int getTempMaxScore() {
		return tempMaxScore;
	}

	public void setMaxScore(int maxScore) {
		this.tempMaxScore = maxScore;

		if (this.tempMaxScore > this.tempHighscore) {
			this.tempHighscore = this.tempMaxScore;
		}
	}

	public boolean getVibrate() {
		return prefs.getBoolean("vibrate", true);
	}

	public int getMaxScore() {
		return tempMaxScore;
	}

	public void setScore(int score) {
		this.tempScore += score;
	}

	public int getScore() {
		return this.tempScore;
	}

	public void gameOver() {
		if (this.tempScore > this.tempMaxScore) {
			setMaxScore(this.tempScore);
		}
		tempDeaths++;
		tempTotalScore += tempScore;
		this.tempScore = 0;
	}

	public void setHighscore(int highscore) {
		if (tempHighscore > getHighscore()) {
			edit.putInt("highscore", highscore);
			edit.commit();
		}
	}

	public int getHighscore() {
		return prefs.getInt("highscore", 0);
	}

	public void addNedims(int nedims) {
		edit.putInt("currentNedims", (startNedims + tempNedims));
		edit.commit();
	}

	public int getNedims() {
		return prefs.getInt("currentNedims", 0);
	}
	
	public int getTempNedims() {
		return this.tempNedims;
	}

	public void addTotalDeath(int death) {
		edit.putInt("totalDeaths", (getTotalDeaths() + death));
		edit.commit();
	}

	public int getTotalDeaths() {
		return prefs.getInt("totalDeaths", 0);
	}

	public void addAlltimeNedims(int nedims) {
		edit.putInt("alltimeNedims", (getAlltimeNedims() + nedims));
		edit.commit();
	}

	public int getAlltimeNedims() {
		return prefs.getInt("alltimeNedims", 0);
	}

	public void addTotalPlaytime(int time) {
		edit.putInt("totalPlaytime", (getTotalPlaytime() + ((int) System.currentTimeMillis() - time) / 1000));
		edit.commit();
	}

	public int getTotalPlaytime() {
		return prefs.getInt("totalPlaytime", 0);
	}

	public void addTotalPlays(int plays) {
		edit.putInt("totalPlays", (getTotalPlays() + plays));
		edit.commit();
	}

	public int getTotalPlays() {
		return prefs.getInt("totalPlays", 0);
	}

	public void addTotalScore(int score) {
		edit.putInt("totalScore", (getTotalScore() + tempTotalScore));
		edit.commit();
	}

	public int getTotalScore() {
		return prefs.getInt("totalScore", 0);
	}

	public void addTotalPurchases(int purchase) {
		edit.putInt("totalPurchases", (getTotalPurchases() + purchase));
		edit.commit();
	}

	public int getTotalPurchases() {
		return prefs.getInt("totalPurchases", 0);
	}

	public void setNedims() {
		// TODO Auto-generated method stub
		
	}

}
