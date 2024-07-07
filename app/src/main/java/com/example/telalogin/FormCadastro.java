package com.example.telalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FormCadastro extends AppCompatActivity {
	private Button submitButton;
	private ProgressBar progressBar;
	private int submitTextColor;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_form_cadastro);

		Resources resources = getResources();
		Resources.Theme theme = getTheme();
		TypedValue typedValue = new TypedValue();

		EditText nameInput = findViewById(R.id.name);
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

		passwordInput.setOnEditorActionListener((view, actionId, event) -> {
			if(actionId == EditorInfo.IME_ACTION_DONE){
				submitButton.callOnClick();
				return true;
			}

			return false;
		});

		Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), "", Snackbar.LENGTH_SHORT);
		snackbar.setBackgroundTint(colorPrimary);
		snackbar.setTextColor(colorOnPrimary);

		submitButton.setOnClickListener(view -> {
			String name = nameInput.getText().toString().trim();
			String email = emailInput.getText().toString().trim();
			String password = passwordInput.getText().toString().trim();

			if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
				snackbar.setBackgroundTint(colorErrorPrimary);
				snackbar.setText(resources.getString(R.string.fillAllFields));
				snackbar.show();
				return;
			}

			SetLoading(true);

			FirebaseAuth firebase = FirebaseAuth.getInstance();

			firebase.createUserWithEmailAndPassword(email, password).addOnCompleteListener(result -> {
				int messageId;

				try{
					if(result.isSuccessful()){
						FirebaseFirestore database = FirebaseFirestore.getInstance();
						Map<String, Object> userData = new HashMap<>();

						userData.put("name", name);

						String userId = Objects.requireNonNull(firebase.getCurrentUser()).getUid();
						DocumentReference documentReference = database.collection("Users").document(userId);

						documentReference.set(userData).addOnCompleteListener(firestoreResult -> {
							if(firestoreResult.isSuccessful()){
								Log.d("database", "User data saved successfully");
								return;
							}

							Log.d("database", "Failed to save user data, error: " + firestoreResult.getException().toString());
						});

						startActivity(new Intent(this, MainActivity.class));
						SetLoading(false);
						return;
					}

					throw Objects.requireNonNull(result.getException());
				}catch(FirebaseAuthWeakPasswordException error){
					messageId = R.string.weakPassword;
				}catch(FirebaseAuthUserCollisionException error){
					messageId = R.string.accountAlreadyExists;
				}catch(FirebaseAuthInvalidCredentialsException error){
					messageId = R.string.invalidEmail;
				}catch(Exception error){
					Log.d("database", error.toString());
					messageId = R.string.somethingWentWrong;
				}

				String message = resources.getString(messageId);

				snackbar.setBackgroundTint(colorErrorPrimary);
				snackbar.setText(message);
				snackbar.show();

				SetLoading(false);
			});
		});
	}

	private void SetLoading(boolean isLoading){
		submitButton.setTextColor(isLoading ? 0 : submitTextColor);
		progressBar.setVisibility(isLoading ? ProgressBar.VISIBLE : ProgressBar.INVISIBLE);
	}
}
