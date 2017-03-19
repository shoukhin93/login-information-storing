package com.example.loginpasswordstoring;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_first_page);
			
		
		Button add = (Button) findViewById(R.id.addbtn);
		Button view = (Button) findViewById(R.id.viewbtn);
		Button exit = (Button) findViewById(R.id.exitbtn);
		
		Typeface type = Typeface
				.createFromAsset(getAssets(), "fonts/COMICBD.TTF");
		
		add.setTypeface(type);
		view.setTypeface(type);
		exit.setTypeface(type);
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FirstPage.this, Add.class);
				startActivity(intent);
				
			}
		});
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FirstPage.this, ViewData.class);
				startActivity(intent);
				
			}
		});
		
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Authentication.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("exit", true);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}

}
