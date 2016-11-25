package dk.unf.software.aar2013.gruppe9;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutActivity extends Activity {

	Button b_back; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_screen);
		
		b_back = (Button) findViewById(R.id.about_b_back);
		
		b_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				exit();
			}
		});
		
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
