package com.example.telalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.content.Intent;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
	private Button submitButton;
	private ProgressBar progressBar;
	private int submitTextColor;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		Resources resources = getResources();
		Resources.Theme theme = getTheme();
		TypedValue typedValue = new TypedValue();
		DatabaseHelper databaseHelper = new DatabaseHelper(this);

		TextView registerLink = findViewById(R.id.register);
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

		passwordInput.setOnEditorActionListener((view, actionId, event) -> {
			if(actionId == EditorInfo.IME_ACTION_DONE){
				submitButton.callOnClick();
				return true;
			}

			return false;
		});

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
				if(databaseHelper.checkCredentials(email, password)){
					startActivity(new Intent(this, MainActivity.class));
					setLoading(false);
					return;
				}

				snackbar.setBackgroundTint(colorErrorPrimary);
				snackbar.setText(resources.getString(R.string.incorrectPassword));
				snackbar.show();

				return;
			}

			snackbar.setBackgroundTint(colorErrorPrimary);
			snackbar.setText(resources.getString(R.string.accountNotFound));
			snackbar.show();

			setLoading(false);
		});

		registerLink.setOnClickListener(view -> {
			startActivity(new Intent(this, SignUpActivity.class));
		});
	}

	private void setLoading(boolean isLoading){
		submitButton.setTextColor(isLoading ? 0 : submitTextColor);
		progressBar.setVisibility(isLoading ? ProgressBar.VISIBLE : ProgressBar.INVISIBLE);
	}
}
