package dk.unf.software.aar2013.gruppe9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DifficultyActivity extends Activity implements OnClickListener {

	Intent intent;
	Button b_easy, b_medium, b_hard, b_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.difficulty_screen);
		
		b_easy = (Button) findViewById(R.id.difficulty_b_easy);
		b_back = (Button) findViewById(R.id.difficulty_b_back);
		b_medium = (Button) findViewById(R.id.difficulty_b_medium);
		b_hard = (Button) findViewById(R.id.difficulty_b_hard);

		b_easy.setOnClickListener(this);
		b_back.setOnClickListener(this);
		b_medium.setOnClickListener(this);
		b_hard.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		intent = new Intent(getApplicationContext(), GameActivity.class);
		switch (v.getId()) {
		case R.id.difficulty_b_easy:
			intent.putExtra("ID", 0);
			startActivity(intent);
			break;
		case R.id.difficulty_b_medium:
			intent.putExtra("ID", 1);
			startActivity(intent);
			break;
		case R.id.difficulty_b_hard:
			intent.putExtra("ID", 2);
			startActivity(intent);
			break;
		case R.id.difficulty_b_back:
			finish();
			break;
		default:
			break;
		}
	}

}
