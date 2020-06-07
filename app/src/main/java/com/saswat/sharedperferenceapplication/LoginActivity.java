package com.saswat.sharedperferenceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final String KEY_LOGGED_IN = "LOGGEDIN";
    private EditText edit_email , et_pass;
    private CheckBox cb_remember_me;
    private TextView tv_register;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_email = findViewById(R.id.text_email);
        et_pass = findViewById(R.id.text_password);
        cb_remember_me = findViewById(R.id.checkbox_remember_me);
        tv_register = findViewById(R.id.tv_register_here);

        preferences = getSharedPreferences("APP_MODE" , MODE_PRIVATE);
        editor = preferences.edit();
        boolean isUserAlreadyLogged = preferences.getBoolean("LOGGEDIN",false);
        if (isUserAlreadyLogged){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }


        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });
    }

    public void onClickLogin(View view) {
        String email = edit_email.getText().toString();
        String password = et_pass.getText().toString();

        boolean isRememberme = cb_remember_me.isChecked();

        String registerUser = preferences.getString("EMAIL_ADDRESS","");
        String registerPassword = preferences.getString("PASSWORD","");
        if(isRememberme){
            editor.putBoolean("LOGGEDIN",true);
        }
        editor.apply();

        if(email.equals(registerUser) && password.equals(registerPassword)){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }else{
            Toast.makeText(this, "Error!!!!", Toast.LENGTH_SHORT).show();
        }

    }
}