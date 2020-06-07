package com.saswat.sharedperferenceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    public static final String KEY_FULL_NAME = "FULLNAME";
    private EditText et_fname , et_remail , et_rpassword , et_rphone_no;
    private TextView tv_login;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_fname = findViewById(R.id.et_full_name);
        et_remail = findViewById(R.id.et_remail);
        et_rpassword = findViewById(R.id.et_rpassword);
        et_rphone_no = findViewById(R.id.et_phone_no);
        tv_login = findViewById(R.id.tv_login_here);

        preferences = getSharedPreferences("APP_MODE",MODE_PRIVATE);
        editor = preferences.edit();



        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

    }

    public void onClickRegister(View view) {
        String fullname = et_fname.getText().toString();
        String email = et_remail.getText().toString();
        String password = et_rpassword.getText().toString();
        String phoneno = et_rphone_no.getText().toString();

        if (fullname.length()>1 && email.length()>1 && password.length()>6) {
            editor.putString(KEY_FULL_NAME, fullname);
            editor.putString("EMAIL_ADDRESS", email);
            editor.putString("PASSWORD", password);
            editor.putString("PHONE_NO", phoneno);
            editor.apply();
            Toast.makeText(RegisterActivity.this, "User Register !!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RegisterActivity.this, "Enter Values In the Respected Fields !!! ", Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        finish();

    }
}