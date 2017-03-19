package com.example.loginpasswordstoring;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.widget.Toast;

public class FileHAndling {

	public static String FILENAME = "logindetails";

	Context context;
	
	public FileHAndling(Context context) {
		this.context = context;
	}

	public void save(String url, String id, String password)
			throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(
				context.openFileOutput(FILENAME, Context.MODE_APPEND));
		
		writer.write(url + "\n");
		writer.write(id + "\n");
		writer.write(password + "\n");
		
		writer.close();
		
		
		
		//Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show();

	}
	
	public void deleteFile()
	{
		FileOutputStream outputStream;
		try {
			outputStream = context.openFileOutput(FileHAndling.FILENAME, Context.MODE_PRIVATE);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
