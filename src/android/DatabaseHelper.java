package me.rahul.plugins.sqlDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private Context myContext;

	public DatabaseHelper(Context context) {
		super(context, sqlDB.dbname, null, 1);
		this.myContext = context;
		// TODO Auto-generated constructor stub
	}

	public void createdatabase(File dbPath) throws IOException {
		// Log.d("CordovaLog","Inside CreateDatabase = "+dbPath);
		this.getReadableDatabase();
		try {
			copyDatabase(dbPath);
		} catch (IOException e) {
			throw new Error("Create Database Exception ============================ " + e);
		}
	}

	/*
	private void copyDatabase(File database) throws IOException {
		InputStream myInput = myContext.getAssets().open("www/"+sqlDB.dbname);
		OutputStream myOutput = new FileOutputStream(database);
		byte[] buffer = new byte[1024];
		while ((myInput.read(buffer)) > -1) {
			myOutput.write(buffer);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	*/

	private void copyDatabase(File database) throws IOException {
		// dbpath = this.cordova.getActivity().getDatabasePath(dbname);
		InputStream myInput = myContext.getAssets().open("www/sql/"+sqlDB.dbname);
		OutputStream myOutput = new FileOutputStream(database);
		byte[] buffer = new byte[1024];
		while ((myInput.read(buffer)) > -1) {
			myOutput.write(buffer);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}


	private void copyFile(String filename) throws IOException {
	    AssetManager assetManager = this.getAssets();
	    InputStream in = null;
	    OutputStream out = null;
	    try {
	        in = assetManager.open(filename);
	        String newFileName = "/data/data/" + this.getPackageName() + "/files/" + filename;
	        out = new FileOutputStream(newFileName);

	        byte[] buffer = new byte[1024];
	        int read;
	        while ((read = in.read(buffer)) != -1) {
	            out.write(buffer, 0, read);
	        }
	        in.close();
	        in = null;
	        out.flush();
	        out.close();
	        out = null;
	    } catch (Exception e) {
	        Log.e("tag", e.getMessage());
	    }
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
