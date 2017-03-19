package com.example.loginpasswordstoring;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class About extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.about);

		String data = "Any internet user have to open account on various sites.\n\n"
				+ "It's easy to forget accout data if different different username and password is used.\n\n"
				+ "This app is created mainly to store the account information under a common password.\n\n";

		String about = "Developed by\n" + "Tanvir Ahmod Shoukhin\n"
				+ "Dept of CSE\n" + "University of Rajshahi\n"
				+ "Session : 2013-14";

		TextView aboutDataTbx = (TextView) findViewById(R.id.aboutdataTbx);
		TextView aboutTbx = (TextView) findViewById(R.id.aboutTbx);

		aboutDataTbx.setText(data);
		aboutTbx.setText(about);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
