package com.example.telalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity {
	private Button submitButton;
	private ProgressBar progressBar;
	private int submitTextColor;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sign_up);

		Resources resources = getResources();
		Resources.Theme theme = getTheme();
		TypedValue typedValue = new TypedValue();
		DatabaseHelper databaseHelper = new DatabaseHelper(this);

		EditText emailInput = findViewById(R.id.email);
		EditText passwordInput = findViewById(R.id.password);
		submitButton = findViewById(R.id.submitButton);

		progressBar = findViewById(R.id.progressBar);

		submitTextColor = submitButton.getCurrentTextColor();
		progressBar.setProgressTintList(ColorStateList.valueOf(submitTextColor));

		theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
		int colorPrimary = typedValue.data;

		theme.resolveAttribute(R.attr.colorErrorPrimary, typedValue, true);
		int colorErrorPrimary = typedValue.data;

		theme.resolveAttribute(R.attr.colorOnPrimary, typedValue, true);
		int colorOnPrimary = typedValue.data;

		Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), "", Snackbar.LENGTH_SHORT);
		snackbar.setBackgroundTint(colorPrimary);
		snackbar.setTextColor(colorOnPrimary);

		submitButton.setOnClickListener(view -> {
			String email = emailInput.getText().toString().trim();
			String password = passwordInput.getText().toString().trim();

			if(email.isEmpty() || password.isEmpty()){
				snackbar.setBackgroundTint(colorErrorPrimary);
				snackbar.setText(resources.getString(R.string.fillAllFields));
				snackbar.show();
				return;
			}

			setLoading(true);

			boolean userExists = databaseHelper.checkEmailExists(email);

			if(userExists){
				snackbar.setBackgroundTint(colorErrorPrimary);
				snackbar.setText(resources.getString(R.string.accountAlreadyExists));
				snackbar.show();
				setLoading(false);
				return;
			}

			boolean userCreated = databaseHelper.insertData(email, password);

			if(userCreated){
				Toast.makeText(this, resources.getString(R.string.registeredSuccessfully), Toast.LENGTH_SHORT).show();
				startActivity(new Intent(this, MainActivity.class));
				setLoading(false);
				return;
			}

			snackbar.setBackgroundTint(colorErrorPrimary);
			snackbar.setText(resources.getString(R.string.somethingWentWrong));
			snackbar.show();
			setLoading(false);
		});
	}

	private void setLoading(boolean isLoading){
		submitButton.setTextColor(isLoading ? 0 : submitTextColor);
		progressBar.setVisibility(isLoading ? ProgressBar.VISIBLE : ProgressBar.INVISIBLE);
	}
}
