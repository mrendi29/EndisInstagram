package com.example.caushie.endisinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button  btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Reference the fields in login activity.
        etPassword=findViewById(R.id.etPassword);
        etUsername=findViewById(R.id.etUsername);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extract the text which is on the edit text
                String password = etPassword.getText().toString();
                String username = etUsername.getText().toString();

                login(username, password);
            }
        });

    }

    //Use parse to check if the user is existing in the database.
    private void login(final String username, final String password) {

        //Check if the user exists in our database.
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                Log.d(TAG, username + " " + password);

                if (e != null) {
                    Log.e(TAG, "Issue with login.");
                    e.printStackTrace();
                    return;
                    //TODO: Add better error handling.
                }
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        //Navigate to our main screen.
        Log.d(TAG, " Succsessfully loged in.");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        // Close the screen so when we click on back button after successfully logging in the app will exit.
        finish();

    }
}
