package com.rominichhh.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TermsAndConditionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAcceptTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        setUpToolbar();

        btnAcceptTerms = findViewById(R.id.btn_accept_terms);
        btnAcceptTerms.setOnClickListener(this);
    }

    private void setUpToolbar() {
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_terms_and_conditions);
    }

    @Override
    public void onClick(View v) {
        accept();
    }

    private void accept() {

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
        //Toast.makeText(this, "Registrando Cuenta...", Toast.LENGTH_SHORT).show();
    }
}