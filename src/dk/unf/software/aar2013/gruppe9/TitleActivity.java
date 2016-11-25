package dk.unf.software.aar2013.gruppe9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TitleActivity extends Activity implements OnClickListener {

	Intent intent;
	Button b_start, b_highScore, b_settings, b_about, b_exit, b_shop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title_screen);

		b_start = (Button) findViewById(R.id.b_start);
		b_highScore = (Button) findViewById(R.id.b_highScore);
		b_settings = (Button) findViewById(R.id.b_settings);
		b_about = (Button) findViewById(R.id.b_about);
		b_exit = (Button) findViewById(R.id.b_exit);
		b_shop = (Button) findViewById(R.id.b_shop);

		b_start.setOnClickListener(this);
		b_highScore.setOnClickListener(this);
		b_settings.setOnClickListener(this);
		b_about.setOnClickListener(this);
		b_exit.setOnClickListener(this);
		b_shop.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_start:
			startAct(DifficultyActivity.class);
			break;
		case R.id.b_highScore:
			startAct(StatisticsActivity.class);
			break;
		case R.id.b_settings:
			startAct(SettingsActivity.class);
			break;
		case R.id.b_about:
			startAct(AboutActivity.class);
			break;
		case R.id.b_shop:
			startAct(ShopActivity.class);
			break;
		case R.id.b_exit:
			finish();
			break;
		default:
			break;
		}

	}

	private void startAct(Class<?> activity) {
		intent = new Intent(getApplicationContext(), activity);
		startActivity(intent);
	}

}
