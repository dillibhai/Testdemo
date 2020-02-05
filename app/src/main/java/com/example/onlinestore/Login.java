package com.example.onlinestore;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinestore.BBL.UserLoginBBL;
import com.example.onlinestore.Interface.UsersApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import BroadcastReceiver.BroadcastReceiver;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button bt_login;
    UsersApi api;
    TextView linkRegister;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    BroadcastReceiver broadcastExample = new BroadcastReceiver(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        linkRegister = findViewById(R.id.linkRegister);

        bt_login = findViewById(R.id.btn_login);
        bt_login.setOnClickListener(this);
        linkRegister.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
         validation_Login();
          //login();

        }

        if(v.getId() == R.id.linkRegister){
            Intent register = new Intent(Login.this,Register.class);
            startActivity(register);
        }

    }
    public void validation_Login() {
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Please enter username");
            username.requestFocus();
            vibration_Mobile(500);
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Please enter password");
            password.requestFocus();
            vibration_Mobile(500);
        }
        else{
            login();
        }
    }

    public void vibration_Mobile(int duration){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(duration);

    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this, "You havenot Logged in Yet.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }

    public void login(){
        String userEmail = username.getText().toString();
        String userPassword = password.getText().toString();
        UserLoginBBL loginBBL = new UserLoginBBL();

        if (loginBBL.checkUser(userEmail,userPassword)){
            preferences = (Login.this).getSharedPreferences("UserData",0);
               editor = preferences.edit();
            Toast.makeText(Login.this, ",Logged in", Toast.LENGTH_SHORT).show();
            vibration_Mobile(500);

                  editor.putString("token", loginBBL.token);
                  editor.putString("uid", loginBBL.userid);
                  editor.commit();
                  Intent intent = new Intent(Login.this, MainActivity.class);
                  startActivity(intent);
                  finish();
        }
        else{
            Toast.makeText(Login.this,"ERROR Ensure Your Email and passwor is correct",Toast.LENGTH_LONG).show();
            vibration_Mobile(500);
        }
    }

    public void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy=new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastExample,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastExample);
    }
}
