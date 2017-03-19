package com.example.loginpasswordstoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Authentication extends Activity {

	EditText loginEtx;
	Button loginBtn, changePasswordBtn;
	String password;
	final String PASSWORD_FILE = "password";
	boolean isPasswordSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// exit method to exit from other pages
				if (getIntent().getBooleanExtra("exit", false)) {
					finish();
				}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authentication);

		intialize();
		searchForExistingPassword();
		onClickListeners();
	}

	private void onClickListeners() {

		// login button
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// if password already setted
				if (isPasswordSet) {
					if (loginEtx.getText().toString().equals(password)) {
						Intent intent = new Intent(Authentication.this,
								FirstPage.class);
						startActivity(intent);
					}

					else
						Toast.makeText(Authentication.this, "Wrong Password!",
								Toast.LENGTH_SHORT).show();

				}

				else {
					if (loginEtx.getText().toString().equals("")) {
						Toast.makeText(Authentication.this,
								"Set Valid Password!", Toast.LENGTH_SHORT)
								.show();
					} else {
						try {
							OutputStreamWriter writer;
							writer = new OutputStreamWriter(openFileOutput(
									PASSWORD_FILE, Context.MODE_APPEND));

							writer.write(loginEtx.getText().toString());

							writer.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

						Toast.makeText(Authentication.this, "Password Is Set ",
								Toast.LENGTH_SHORT).show();

						Intent intent = new Intent(Authentication.this,
								FirstPage.class);
						startActivity(intent);
					}
				}

			}
		});

		// change password button
		changePasswordBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog.Builder exitAlert = new AlertDialog.Builder(
						Authentication.this);
				exitAlert
						.setMessage("Changing Password Will Delete Ur Previous Data\nAre you sure you want to Change?");
				exitAlert.setCancelable(false);

				// if yes is clicked
				exitAlert.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								File file1 = getFileStreamPath(PASSWORD_FILE);
								File file = getFileStreamPath(FileHAndling.FILENAME);
								
								file1.delete();
								file.delete();
								
								invisibleButtons();
								
								Toast.makeText(Authentication.this, "Password Cleared!",
										Toast.LENGTH_SHORT).show();
								
								loginEtx.setText("");

							}
						});

				// if no is clicked
				exitAlert.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});

				AlertDialog alert = exitAlert.create();
				alert.show();
			}
		});
	}

	private void searchForExistingPassword() {

		File file = getFileStreamPath(PASSWORD_FILE);

		if (file.exists()) {
			password = "";
			try {
				InputStream inputStream = openFileInput(PASSWORD_FILE);
				InputStreamReader streamReader = new InputStreamReader(
						inputStream);
				BufferedReader reader = new BufferedReader(streamReader);

				String s;

				while ((s = reader.readLine()) != null) {
					password += s;
				}

				inputStream.close();
			} catch (Exception e) {

			}
			
			visibleButtons();
		}

		else {
			// toggling change password button between visible invisible
			invisibleButtons();
		}

	}

	private void intialize() {
		loginEtx = (EditText) findViewById(R.id.loginEtx);

		loginBtn = (Button) findViewById(R.id.loginBtn);
		changePasswordBtn = (Button) findViewById(R.id.changePasswordBtn);

		isPasswordSet = false;
		
		Typeface type = Typeface
				.createFromAsset(getAssets(), "fonts/COMICBD.TTF");
		
		loginBtn.setTypeface(type);
		changePasswordBtn.setTypeface(type);

	}
	
	private void visibleButtons() {
		changePasswordBtn.setVisibility(View.VISIBLE);
		loginBtn.setText("Login");

		isPasswordSet = true;
	}
	
	private void invisibleButtons() {
		changePasswordBtn.setVisibility(View.GONE);
		loginBtn.setText("Set Password");

		isPasswordSet = false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		loginEtx.setText("");
		searchForExistingPassword();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.first_page, menu);
		return true;
	}
	// Function To Select Main Menu Item
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			if (item.getItemId() == R.id.about) {
				Intent intent = new Intent(getApplicationContext(), About.class);
				startActivity(intent);
			}
			
			else if(item.getItemId() == R.id.exitmenu)
				this.finish();
			
			return super.onOptionsItemSelected(item);
		}

}
