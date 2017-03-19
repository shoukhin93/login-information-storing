package com.example.loginpasswordstoring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewData extends Activity {
	
	BaseAdapter adapter;
	ArrayList<ListViewItem> arrayList;
	
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.viewdata);
		
		initialize();
		fileReadAndShowData();
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				
				String tempUrl = ((TextView) arg1.findViewById(R.id.listviewURL))
				.getText().toString();
				
				String tempId = ((TextView) arg1.findViewById(R.id.listviewID))
						.getText().toString();
				
				String tempPass = ((TextView) arg1.findViewById(R.id.listviewPassword))
						.getText().toString();
				
				Intent intent = new Intent(ViewData.this, Modify.class);
				intent.putExtra("URL", tempUrl);
				intent.putExtra("ID", tempId);
				intent.putExtra("PASSWORD", tempPass);
				
				startActivity(intent);
				
				
			}
		});
	}

	private void fileReadAndShowData() {
		arrayList.clear();
		try {
			InputStream inputStream = openFileInput(FileHAndling.FILENAME);
			
			InputStreamReader streamReader = new InputStreamReader(inputStream);
			
			BufferedReader reader = new BufferedReader(streamReader);
			
			String tempURL;
			
			//reading url, id and password until the file ends
			while((tempURL = reader.readLine())!= null)
			{
				String tempID = reader.readLine();
				String tempPassword = reader.readLine();
				
				//making a listview item and adding to arraylist to
				ListViewItem item = new ListViewItem(tempURL, tempID, tempPassword);
				arrayList.add(item);
				
			}
			inputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(arrayList);
		adapter.notifyDataSetChanged();
		
	}

	private void initialize() {
		arrayList = new ArrayList<ListViewItem>();
		listView = (ListView) findViewById(R.id.listView1);
		
		adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				if (convertView == null) {
					convertView = inflater.inflate(R.layout.listview, null);
				}

				TextView one = (TextView) convertView
						.findViewById(R.id.listviewURL);

				TextView two = (TextView) convertView
						.findViewById(R.id.listviewID);

				TextView three = (TextView) convertView
						.findViewById(R.id.listviewPassword);

				one.setText(arrayList.get(position).getUrl());
				two.setText(arrayList.get(position).getId());
				three.setText(arrayList.get(position).getPassword());

				return convertView;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return arrayList.get(position);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return arrayList.size();
			}
		};

		listView.setAdapter(adapter);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		fileReadAndShowData();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
