package com.example.caushie.endisinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button  btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword=findViewById(R.id.etPassword);
        etUsername=findViewById(R.id.etUsername);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
