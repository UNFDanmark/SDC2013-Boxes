package dk.unf.software.aar2013.gruppe9;

import android.graphics.Bitmap;

abstract class Character {
	private float pos_x, pos_y;
	private Bitmap bmp;

	public void setBitmap(Bitmap bmp) {
		this.bmp = bmp;
	}
	
	public Bitmap getBitmap() {
		return bmp;
	}
	
	public float getPosX() {
		return pos_x;
	}
	
	public float getPosY() {
		return pos_y;
	}
	
	public void setPosY(float y) {
		this.pos_y = y;
	}
	
	public void setPosX(float x) {
		this.pos_x = x;
	}
	
	public void move(float pos_x, float pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

}
