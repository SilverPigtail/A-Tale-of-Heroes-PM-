package com.alejandro.ataleofheroes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.badlogic.gdx.Game;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void loginFun(View view) {

        EditText nameField = (EditText)findViewById(R.id.nameField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);

        if(nameField.getText().toString().equalsIgnoreCase("admin") && passwordField.getText().toString().equalsIgnoreCase("password")){

            Toast loginSuccess = Toast.makeText(this, "Login Successful...", Toast.LENGTH_LONG);
            loginSuccess.show();

            Intent goToSelect = new Intent(this, GameMenu.class);
            startActivity(goToSelect);
        }

    }
}
