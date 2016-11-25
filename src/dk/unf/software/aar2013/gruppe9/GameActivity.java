package dk.unf.software.aar2013.gruppe9;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;

public class GameActivity extends Activity {

	public GameView gameView;
	SharedPreferences prefs;
	Editor edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // Fjerner action bar
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		edit = prefs.edit();
		
		ImageLoader.setContext(this, edit, prefs);
		
		edit.putInt("totalPlays", (prefs.getInt("totalPlays", 0) + 1));
		edit.commit();
		
		Intent intent = getIntent();
		
		int id = intent.getExtras().getInt("ID");

		gameView = new GameView(this, getSystemService(Context.VIBRATOR_SERVICE), id, PreferenceManager.getDefaultSharedPreferences(this));
		
		setContentView(gameView);

	}

	// public void popup(final Player player, GameClass gc) {
	//
	// GameActivity.this.runOnUiThread(new Runnable() {
	// public void run() {
	// AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
	// builder.setMessage("Score: " +
	// player.getScore()).setCancelable(false).setPositiveButton("Main Menu",
	// new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// dialog.cancel();
	// }
	// }).setNegativeButton("Retry", new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// dialog.cancel();
	// }
	// });
	// AlertDialog alert = builder.create();
	// alert.show();
	//
	// }
	// });
	//
	// }

	@Override
	protected void onPause() {
		gameView.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		gameView.resume();
		super.onResume();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		gameView.backPressed();
		super.onBackPressed();
	}

}
