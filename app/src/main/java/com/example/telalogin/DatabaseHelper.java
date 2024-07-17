package com.example.telalogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
	public DatabaseHelper(@Nullable Context context){
		super(context, "UserData.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase){
		sqLiteDatabase.execSQL("create table user (name text primary key, contact text, dob text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
		sqLiteDatabase.execSQL("drop table if exists user");
	}

	public boolean insertUser(String name, String contact, String dob){
		SQLiteDatabase sqLiteDatabase = getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		contentValues.put("contact", contact);
		contentValues.put("dob", dob);

		long result = sqLiteDatabase.insert("user", null, contentValues);

		return result != -1;
	}

	public boolean updateUser(String name, String contact, String dob){
		SQLiteDatabase sqLiteDatabase = getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put("contact", contact);
		contentValues.put("dob", dob);
		Cursor cursor = sqLiteDatabase.rawQuery("select * from user where name = ?", new String[]{ name });

		if(cursor.getCount() == 0){
			cursor.close();
			return false;
		}

		int result = sqLiteDatabase.update("user", contentValues, "name=?", new String[]{ name });

		return result != -1;
	}

	public boolean deleteUser(String name){
		SQLiteDatabase sqLiteDatabase = getWritableDatabase();
		Cursor cursor = sqLiteDatabase.rawQuery("select * from user where name = ?", new String[]{ name });

		if(cursor.getCount() == 0){
			cursor.close();
			return false;
		}

		int result = sqLiteDatabase.delete("user", "name=?", new String[]{ name });

		return result != -1;
	}

	public Cursor getData(){
		SQLiteDatabase sqLiteDatabase = getWritableDatabase();
		return sqLiteDatabase.rawQuery("select * from user", null);
	}
}
