package dk.unf.software.aar2013.gruppe9;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageLoader {
	private static HashMap<String, Bitmap> bitmapMap = new HashMap<String, Bitmap>();
	private static Context con;
	private static SharedPreferences prefs;
	private static Editor edit;
	
	public static void setContext(Context con, Editor edit, SharedPreferences prefs) {
		ImageLoader.con = con;
		ImageLoader.edit = edit;
		ImageLoader.prefs = prefs;
		
		setPlayerSkin();
		addImage("mob", R.drawable.red);
	}
	
	private static void setPlayerSkin() {
		addImage("player", ImageLoader.prefs.getInt("skin", R.drawable.green));
	}

	public static void addImage(String key, int resourceId) {
			Bitmap b = BitmapFactory.decodeResource(con.getResources(), resourceId);
			bitmapMap.put(key, b);
	}
	
	public static Bitmap getBitmap(String key) {
		return bitmapMap.get(key);
	}
	
	
}
