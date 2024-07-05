package com.example.telalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FormLogin extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_form_login);

		TextView registerLink = findViewById(R.id.register);

		registerLink.setOnClickListener(view -> {
			startActivity(new Intent(this, FormCadastro.class));
		});
	}
}
