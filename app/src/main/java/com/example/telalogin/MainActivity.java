package com.example.telalogin;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finishAffinity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();

		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
			onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true){
				@Override
				public void handleOnBackPressed(){
					finishAffinity();
				}
			});
		}

		Button logoutButton = findViewById(R.id.logoutButton);

		logoutButton.setOnClickListener(view -> {
			FirebaseAuth firebase = FirebaseAuth.getInstance();
			firebase.signOut();
			startActivity(new Intent(this, FormLogin.class));
		});
	}
}
