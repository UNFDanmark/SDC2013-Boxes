package dk.unf.software.aar2013.gruppe9;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class ShopActivity extends Activity implements OnClickListener {

	SharedPreferences prefs;
	Editor edit;
	ImageButton b_green, b_zebra, b_face, b_pig, b_rainbow;
	ImageButton b_blue, b_dark, b_white, b_white2, b_bluetile;

	TextView tv_green, tv_zebra, tv_face, tv_pig, tv_rainbow;
	TextView tv_blue, tv_dark, tv_white, tv_white2, tv_bluetile;
	TextView tv_currentNedims;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_screen);

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		edit = prefs.edit();

		b_green = (ImageButton) findViewById(R.id.shop_b_green);
		b_zebra = (ImageButton) findViewById(R.id.shop_b_zebra);
		b_face = (ImageButton) findViewById(R.id.shop_b_face);
		b_pig = (ImageButton) findViewById(R.id.shop_b_pig);
		b_rainbow = (ImageButton) findViewById(R.id.shop_b_rainbow);

		b_blue = (ImageButton) findViewById(R.id.shop_b_blue);
		b_dark = (ImageButton) findViewById(R.id.shop_b_dark);
		b_white = (ImageButton) findViewById(R.id.shop_b_white);
		b_white2 = (ImageButton) findViewById(R.id.shop_b_white2);
		b_bluetile = (ImageButton) findViewById(R.id.shop_b_bluetile);

		b_green.setOnClickListener(this);
		b_zebra.setOnClickListener(this);
		b_face.setOnClickListener(this);
		b_pig.setOnClickListener(this);
		b_rainbow.setOnClickListener(this);

		b_blue.setOnClickListener(this);
		b_dark.setOnClickListener(this);
		b_white.setOnClickListener(this);
		b_white2.setOnClickListener(this);
		b_bluetile.setOnClickListener(this);
		
		tv_currentNedims = (TextView) findViewById(R.id.tv_nedims);
		tv_currentNedims.setText("" + prefs.getInt("currentNedims", 0));

		// tv_currentNedims.setText(getPrefs("currentNedims"));
	}

	private String getPrefs(String string) {
		int i;
		i = prefs.getInt(string, 0);
		return String.valueOf(i);
	}

	private void setPlayerSkin(int value) {
		b_green.setBackgroundColor(Color.TRANSPARENT);
		b_zebra.setBackgroundColor(Color.TRANSPARENT);
		b_face.setBackgroundColor(Color.TRANSPARENT);
		b_pig.setBackgroundColor(Color.TRANSPARENT);
		b_rainbow.setBackgroundColor(Color.TRANSPARENT);
		edit.putInt("skin", value);
		edit.commit();
	}

	private void setBackground(int value) {
		b_blue.setBackgroundColor(Color.TRANSPARENT);
		b_dark.setBackgroundColor(Color.TRANSPARENT);
		b_white.setBackgroundColor(Color.TRANSPARENT);
		b_white2.setBackgroundColor(Color.TRANSPARENT);
		b_bluetile.setBackgroundColor(Color.TRANSPARENT);
		edit.putInt("background", value);
		edit.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shop_b_green:
			setPlayerSkin(R.drawable.green);
			b_green.setBackgroundColor(Color.BLUE);
			break;
		case R.id.shop_b_zebra:
			setPlayerSkin(R.drawable.zebra);
			b_zebra.setBackgroundColor(Color.BLUE);
			break;
		case R.id.shop_b_rainbow:
			setPlayerSkin(R.drawable.rainbow);
			b_rainbow.setBackgroundColor(Color.BLUE);
			break;
		case R.id.shop_b_pig:
			setPlayerSkin(R.drawable.pig);
			b_pig.setBackgroundColor(Color.BLUE);
			break;
		case R.id.shop_b_face:
			setPlayerSkin(R.drawable.face);
			b_face.setBackgroundColor(Color.BLUE);
			break;
		case R.id.shop_b_blue:
			setBackground(R.drawable.b_blue);
			b_blue.setBackgroundColor(Color.BLUE);
			break;
		case R.id.shop_b_bluetile:
			setBackground(R.drawable.b_bluetile);
			b_bluetile.setBackgroundColor(Color.BLUE);
			break;
		case R.id.shop_b_white:
			setBackground(R.drawable.b_white);
			b_white.setBackgroundColor(Color.BLUE);
			break;
		case R.id.shop_b_white2:
			setBackground(R.drawable.b_white2);
			b_white2.setBackgroundColor(Color.BLUE);
			break;
		case R.id.shop_b_dark:
			setBackground(R.drawable.b_dark);
			b_dark.setBackgroundColor(Color.BLUE);
			break;

		default:
			break;
		}
	}

	private void pay(int i) {
		if (i > prefs.getInt("currentNedims", 0)) {
			edit.putInt("currentNedims", prefs.getInt("currentNedims", 0) - i);
		}
		
	}

}
