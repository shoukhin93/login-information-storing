package com.example.loginpasswordstoring;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends Activity {

	Button save, reset;

	EditText url, id, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.adddata);

		url = (EditText) findViewById(R.id.url);
		id = (EditText) findViewById(R.id.id);
		password = (EditText) findViewById(R.id.password);

		save = (Button) findViewById(R.id.savebtn);
		reset = (Button) findViewById(R.id.resetbtn);
		
		Typeface type = Typeface
				.createFromAsset(getAssets(), "fonts/COMICBD.TTF");
		
		save.setTypeface(type);
		reset.setTypeface(type);

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				

				// if any form is empty
				if (url.getText().toString().equals("")
						|| id.getText().toString().equals("")
						|| password.getText().toString().equals("")) {

					Toast.makeText(Add.this, "Fill all fields!",
							Toast.LENGTH_SHORT).show();
					return;

				}

				FileHAndling obj = new FileHAndling(Add.this);

				try {
					// saving
					obj.save(url.getText().toString(), id.getText().toString(),
							password.getText().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Toast.makeText(Add.this, "Saved Successfully",
						Toast.LENGTH_SHORT).show();
				Add.this.finish();

			}
		});

		reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				url.setText("");
				id.setText("");
				password.setText("");

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
