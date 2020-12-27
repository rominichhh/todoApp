package com.rominichhh.todoapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private Button btnRegister;
    private EditText etEmail, etPassword;
    private CheckBox cbRememberCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("Iniciar Sesión");

        }

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        cbRememberCredentials = findViewById(R.id.cb_remember_credentials);

        //View
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SP_CREDENTIALS, MODE_PRIVATE);
        String savedEmail = sharedPreferences.getString(Constants.EMAIL_EXTRA, null);
        String savedPassword = sharedPreferences.getString(Constants.PASSWORD_EXTRA, null);

        if(savedEmail != null && savedPassword != null){
            startMainActivity(savedEmail, savedPassword);
        }

    }


    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            login();
        } else {
            register();
        }
    }



    public void login(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
            return;
        }

        saveCredentialsIfRequired(email, password);
        startMainActivity(email, password);
    }

    private void saveCredentialsIfRequired(String email, String password) {
        if(cbRememberCredentials.isChecked()){
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.SP_CREDENTIALS, MODE_PRIVATE);
            sharedPreferences.edit()
                    .putString(Constants.EMAIL_EXTRA, email)
                    .putString(Constants.PASSWORD_EXTRA, password)
                    .apply();
        }
    }

    private void startMainActivity(String email, String password) {
        Intent mainIntent = new Intent(this, MainActivity.class);

        mainIntent.putExtra(Constants.EMAIL_EXTRA, email);
        mainIntent.putExtra(Constants.PASSWORD_EXTRA, password);

        startActivity(mainIntent);
        finish();
    }

    private void register() {

        Intent termsIntent = new Intent(this, TermsAndConditionsActivity.class);
        startActivity(termsIntent);
        finish();
        //Toast.makeText(this, "Registrando Cuenta...", Toast.LENGTH_SHORT).show();
    }
}