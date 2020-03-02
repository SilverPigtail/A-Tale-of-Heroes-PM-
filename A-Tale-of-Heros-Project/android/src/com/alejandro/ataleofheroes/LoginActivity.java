package com.alejandro.ataleofheroes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.badlogic.gdx.Game;

/***
 * Unused code, if the database will not have user and passoword, this class will be deleted.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    /***
     * This is a simple class that allows the user to login on the database.
     * @param view
     */
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
