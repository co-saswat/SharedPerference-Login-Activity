package com.saswat.sharedperferenceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView tv_user_name , tv_user_email;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv_user_name = findViewById(R.id.tv_user_full_name);
        tv_user_email = findViewById(R.id.tv_user_email);

        preferences = getSharedPreferences("APP_MODE",MODE_PRIVATE);
        editor = preferences.edit();

        String userName = preferences.getString(RegisterActivity.KEY_FULL_NAME,"");
        String userEmail = preferences.getString("EMAIL_ADDRESS","");

        tv_user_name.setText(userName);
        tv_user_email.setText(userEmail);
    }

    public void onClickLogout(View view) {
        editor.putBoolean(LoginActivity.KEY_LOGGED_IN,false);

        editor.putString("PASSWORD","");
        editor.putString("EMAIL_ADDRESS","");
        editor.apply();


        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        finish();
    }
}