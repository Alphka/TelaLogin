package com.example.telalogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
	DatabaseHelper sqliteDatabase = new DatabaseHelper(this);

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		EditText nameInput = findViewById(R.id.name);
		EditText contactInput = findViewById(R.id.contact);
		EditText dobInput = findViewById(R.id.dob);

		Button insertButton = findViewById(R.id.insertButton);
		Button updateButton = findViewById(R.id.updateButton);
		Button deleteButton = findViewById(R.id.deleteButton);
		Button getAllButton = findViewById(R.id.getAllButton);

		Resources resources = getResources();
		Resources.Theme theme = getTheme();
		TypedValue typedValue = new TypedValue();

		theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
		int colorPrimary = typedValue.data;

		theme.resolveAttribute(R.attr.colorErrorPrimary, typedValue, true);
		int colorErrorPrimary = typedValue.data;

		theme.resolveAttribute(R.attr.colorOnPrimary, typedValue, true);
		int colorOnPrimary = typedValue.data;

		Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), "", Snackbar.LENGTH_SHORT);
		snackbar.setBackgroundTint(colorPrimary);
		snackbar.setTextColor(colorOnPrimary);

		insertButton.setOnClickListener(view -> {
			String name = nameInput.getText().toString().trim();
			String contact = contactInput.getText().toString().trim();
			String dob = dobInput.getText().toString().trim();

			boolean success = sqliteDatabase.insertUser(name, contact, dob);

			if(!success){
				snackbar.setBackgroundTint(colorErrorPrimary);
				snackbar.setText(resources.getString(R.string.failedToCreateUser));
				snackbar.show();
				return;
			}

			snackbar.setBackgroundTint(colorPrimary);
			snackbar.setText(resources.getString(R.string.userCreatedSuccessfully));
			snackbar.show();
		});

		updateButton.setOnClickListener(view -> {
			String name = nameInput.getText().toString().trim();
			String contact = contactInput.getText().toString().trim();
			String dob = dobInput.getText().toString().trim();

			boolean success = sqliteDatabase.updateUser(name, contact, dob);

			if(!success){
				snackbar.setBackgroundTint(colorErrorPrimary);
				snackbar.setText(resources.getString(R.string.failedToUpdateUserData));
				snackbar.show();
				return;
			}

			snackbar.setBackgroundTint(colorPrimary);
			snackbar.setText(resources.getString(R.string.userDataUpdatedSuccessfully));
			snackbar.show();
		});

		deleteButton.setOnClickListener(view -> {
			String name = nameInput.getText().toString().trim();

			boolean success = sqliteDatabase.deleteUser(name);

			if(!success){
				snackbar.setBackgroundTint(colorErrorPrimary);
				snackbar.setText(resources.getString(R.string.failedToDeleteUser));
				snackbar.show();
				return;
			}

			snackbar.setBackgroundTint(colorPrimary);
			snackbar.setText(resources.getString(R.string.userDeletedSuccessfully));
			snackbar.show();
		});

		getAllButton.setOnClickListener(view -> {
			Cursor cursor = sqliteDatabase.getData();

			if(cursor.getCount() == 0){
				cursor.close();
				snackbar.setBackgroundTint(colorErrorPrimary);
				snackbar.setText(resources.getString(R.string.noUserFoundInDatabase));
				snackbar.show();
				return;
			}

			StringBuffer buffer = new StringBuffer();

			while(cursor.moveToNext()){
				buffer.append(resources.getString(R.string.nameInput) + ": " + cursor.getString(0) + "\n");
				buffer.append(resources.getString(R.string.contactInput) + ": " + cursor.getString(1) + "\n");
				buffer.append(resources.getString(R.string.dobInput) + ": " + cursor.getString(2) + "\n");
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setCancelable(true);
			builder.setTitle(resources.getString(R.string.userEntries));
			builder.setMessage(buffer.toString());
			builder.show();
		});
	}
}
