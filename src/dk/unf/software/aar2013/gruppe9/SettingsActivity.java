package dk.unf.software.aar2013.gruppe9;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsActivity extends Activity implements OnClickListener {

	SharedPreferences prefs;
	Editor edit;

	Button b_clear, b_back;
	CheckBox cb_vibrate, cb_debug;

	@SuppressLint("CommitPrefEdits")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);

		b_clear = (Button) findViewById(R.id.settings_b_clear);
		b_back = (Button) findViewById(R.id.settings_b_back);
		cb_vibrate = (CheckBox) findViewById(R.id.settings_cb_vibrate);
		cb_debug = (CheckBox) findViewById(R.id.settings_cb_debug);

		b_clear.setOnClickListener(this);
		b_back.setOnClickListener(this);
		cb_vibrate.setOnClickListener(this);
		cb_debug.setOnClickListener(this);

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		edit = prefs.edit();

		if (prefs.getBoolean("vibrate", true)) {
			cb_vibrate.setChecked(true);
		} else {
			cb_vibrate.setChecked(false);
		}

		if (prefs.getBoolean("debug", false)) {
			cb_debug.setChecked(true);
		} else {
			cb_debug.setChecked(false);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.settings_cb_vibrate:
			if (cb_vibrate.isChecked()) {
				edit.putBoolean("vibrate", true);
			} else {
				edit.putBoolean("vibrate", false);
			}
			edit.commit();
			break;
		case R.id.settings_cb_debug:
			if (cb_debug.isChecked()) {
				edit.putBoolean("debug", true);
			} else {
				edit.putBoolean("debug", false);
			}
			edit.commit();
			break;
		case R.id.settings_b_clear:
			clear();
			break;
		case R.id.settings_b_back:
			finish();
			break;
		default:
			break;
		}
	}

	private void clear() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to reset your statistics?").setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						edit.clear();
						edit.commit();
						if (cb_vibrate.isChecked()) {
							edit.putBoolean("vibrate", true);
						} else {
							edit.putBoolean("vibrate", false);
						}
						edit.commit();
					}
				}).setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

}
