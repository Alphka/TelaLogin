package com.example.telalogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String databaseName = "Signup.db";

	public DatabaseHelper(@Nullable Context context){
		super(context, "Signup.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase){
		sqLiteDatabase.execSQL("create table user (email TEXT primary key, password TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
		sqLiteDatabase.execSQL("drop table if exists user");
	}

	public boolean insertData(String email, String password){
		SQLiteDatabase sqLiteDatabase = getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put("email", email);
		contentValues.put("password", password);

		long result = sqLiteDatabase.insert("user", null, contentValues);

		return result != -1;
	}

	public boolean checkEmailExists(String email){
		SQLiteDatabase sqLiteDatabase = getWritableDatabase();

		String[] args = { email };
		Cursor cursor = sqLiteDatabase.rawQuery("select * from user where email = ?", args);

		return cursor.getCount() > 0;
	}

	public boolean checkCredentials(String email, String password){
		SQLiteDatabase sqLiteDatabase = getWritableDatabase();

		String[] args = { email, password };
		Cursor cursor = sqLiteDatabase.rawQuery("select * from user where email = ? and password = ?", args);

		return cursor.getCount() > 0;
	}
}
