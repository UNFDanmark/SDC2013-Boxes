package dk.unf.software.aar2013.gruppe9;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StatisticsActivity extends Activity {

	TextView tv_maxScore, tv_currentNedims, tv_alltimeNedims, tv_totalPlaytime, tv_totalPlays, tv_totalDeaths, tv_totalScore, tv_totalPurchases;
	SharedPreferences prefs;			
	Editor edit;
	
	Button b_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistics_screen);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		edit = prefs.edit();
		
		b_back = (Button) findViewById(R.id.statistics_b_back);
		
		tv_maxScore = (TextView) findViewById(R.id.statistics_tv_max_score);
		tv_currentNedims = (TextView) findViewById(R.id.statistics_tv_current_nedims);
		tv_alltimeNedims = (TextView) findViewById(R.id.statistics_tv_alltime_nedims);
		tv_totalPlaytime = (TextView) findViewById(R.id.statistics_tv_total_playtime);
		tv_totalPlays = (TextView) findViewById(R.id.statistics_tv_total_plays);
		tv_totalDeaths = (TextView) findViewById(R.id.statistics_tv_total_deaths);
		tv_totalScore = (TextView) findViewById(R.id.statistics_tv_total_score);
		tv_totalPurchases = (TextView) findViewById(R.id.statistics_tv_total_purchases);
		
		tv_maxScore.setText(getPrefs("highscore"));
		tv_currentNedims.setText(getPrefs("currentNedims"));
		tv_alltimeNedims.setText(getPrefs("alltimeNedims"));
		tv_totalPlaytime.setText(getPrefs("totalPlaytime") + " seconds");
		tv_totalPlays.setText(getPrefs("totalPlays"));
		tv_totalDeaths.setText(getPrefs("totalDeaths"));
		tv_totalScore.setText(getPrefs("totalScore"));
		tv_totalPurchases.setText(getPrefs("totalPurchases"));
		
		b_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				exit();
			}
		});
	}

	private String getPrefs(String string) {
		int i;
		i = prefs.getInt(string, 0);
		return String.valueOf(i);
	}
	
	@Override
	public void onBackPressed() {
		exit();
		super.onBackPressed();
	}
	
	public void exit() {
		Intent intent = new Intent(this, TitleActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
