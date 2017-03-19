package com.example.loginpasswordstoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

public class Modify extends Activity {

	EditText urlTbx, idTbx, passwordTbx;
	Button copyUrl, copyId, copyPassword;
	Button save, delete;

	String Url, Id, Password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.modify);

		initialize();
		oncClickListeners();

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}

	private void oncClickListeners() {
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String currentUrl = urlTbx.getText().toString();
				String currentId = idTbx.getText().toString();
				String currentPassword = passwordTbx.getText().toString();

				if (Url.equals(currentUrl) && Id.equals(currentId)
						&& Password.equals(currentPassword)) {
					Modify.this.finish();
				}

				else
					modifyData();

				Toast.makeText(Modify.this, "Saved Successfully",
						Toast.LENGTH_SHORT).show();

				Modify.this.finish();

			}

		});

		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				deleteData();

				Toast.makeText(Modify.this, "Deleted Successfully",
						Toast.LENGTH_SHORT).show();

				Modify.this.finish();
			}
		});

		copyUrl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				copyToClipboard(urlTbx.getText().toString());
				
				Toast.makeText(Modify.this, "Copied To Clipboard",
						Toast.LENGTH_SHORT).show();

			}
		});
		copyId.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				copyToClipboard(idTbx.getText().toString());
				
				Toast.makeText(Modify.this, "Copied To Clipboard",
						Toast.LENGTH_SHORT).show();

			}
		});
		copyPassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				copyToClipboard(passwordTbx.getText().toString());
				
				Toast.makeText(Modify.this, "Copied To Clipboard",
						Toast.LENGTH_SHORT).show();

			}
		});

	}

	private void copyToClipboard(String content) {
		ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

		ClipData data = ClipData.newPlainText("label",content);

		manager.setPrimaryClip(data);
	}

	private void initialize() {
		// edit text
		urlTbx = (EditText) findViewById(R.id.modifyUrlEtx);
		idTbx = (EditText) findViewById(R.id.modifyIdEtx);
		passwordTbx = (EditText) findViewById(R.id.modifyPassEtx);

		// copy button
		copyUrl = (Button) findViewById(R.id.modifyUrlCopybtn);
		copyId = (Button) findViewById(R.id.modifyIdCopybtn);
		copyPassword = (Button) findViewById(R.id.modifyPassCopybtn);

		// save and delete button

		save = (Button) findViewById(R.id.modifySavebtn);
		delete = (Button) findViewById(R.id.modifyDeletebtn);

		// getting intent contents
		Url = getIntent().getExtras().getString("URL");
		Id = getIntent().getExtras().getString("ID");
		Password = getIntent().getExtras().getString("PASSWORD");

		urlTbx.setText(Url);
		idTbx.setText(Id);
		passwordTbx.setText(Password);
		
		Typeface type = Typeface
				.createFromAsset(getAssets(), "fonts/COMICBD.TTF");
		
		copyUrl.setTypeface(type);
		copyId.setTypeface(type);		
		copyPassword.setTypeface(type);
		save.setTypeface(type);
		delete.setTypeface(type);

	}

	private void modifyData() {
		ArrayList<String> modifiedFile = new ArrayList<String>();

		modifiedFile.add(urlTbx.getText().toString());
		modifiedFile.add(idTbx.getText().toString());
		modifiedFile.add(passwordTbx.getText().toString());

		try {
			InputStream inputStream = openFileInput(FileHAndling.FILENAME);

			InputStreamReader streamReader = new InputStreamReader(inputStream);

			BufferedReader reader = new BufferedReader(streamReader);

			String tempURL;

			// reading url, id and password until the file ends
			while ((tempURL = reader.readLine()) != null) {
				String tempID = reader.readLine();
				String tempPassword = reader.readLine();

				if (tempURL.equals(Url) || tempID.equals(Id)
						|| tempPassword.equals(Password))
					continue;

				modifiedFile.add(tempURL);
				modifiedFile.add(tempID);
				modifiedFile.add(tempPassword);

			}
			inputStream.close();

			// deleting file

			FileHAndling obj = new FileHAndling(Modify.this);
			obj.deleteFile();

			// writing to file
			for (int i = 0; i < modifiedFile.size(); i += 3) {
				obj.save(modifiedFile.get(i), modifiedFile.get(i + 1),
						modifiedFile.get(i + 2));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void deleteData() {
		ArrayList<String> modifiedFile = new ArrayList<String>();

		try {
			InputStream inputStream = openFileInput(FileHAndling.FILENAME);

			InputStreamReader streamReader = new InputStreamReader(inputStream);

			BufferedReader reader = new BufferedReader(streamReader);

			String tempURL;

			// reading url, id and password until the file ends
			while ((tempURL = reader.readLine()) != null) {
				String tempID = reader.readLine();
				String tempPassword = reader.readLine();

				if (tempURL.equals(Url) && tempID.equals(Id)
						&& tempPassword.equals(Password))
					continue;

				modifiedFile.add(tempURL);
				modifiedFile.add(tempID);
				modifiedFile.add(tempPassword);

			}
			inputStream.close();

			// deleting file

			FileHAndling obj = new FileHAndling(Modify.this);
			obj.deleteFile();

			// writing to file
			for (int i = 0; i < modifiedFile.size(); i += 3) {
				obj.save(modifiedFile.get(i), modifiedFile.get(i + 1),
						modifiedFile.get(i + 2));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
