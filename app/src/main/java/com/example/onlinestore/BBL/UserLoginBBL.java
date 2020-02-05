package com.example.onlinestore.BBL;

import android.content.Intent;
import android.widget.Toast;

import com.example.onlinestore.Controller.CreateInstance;
import com.example.onlinestore.Interface.UsersApi;
import com.example.onlinestore.Login;
import com.example.onlinestore.Model.Tokenauth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLoginBBL {
    UsersApi usersApi;

    public static String token;
    public static String userid;

    CreateInstance createInstance;
    private String userEmail;
    private String userPassword;
    private String _id;
    public static Tokenauth authtoken;

    public boolean checkUser(String userEmail, String userPassword) {
        boolean isloggedin = false;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usersApi = retrofit.create(UsersApi.class);

        Call<Tokenauth> usercall = usersApi.userVerification(userEmail,userPassword);
        try{
            Response<Tokenauth> loginResponse = usercall.execute();
            if(!loginResponse.body().getMessage().isEmpty()){
               token = loginResponse.body().getToken();
               userid = loginResponse.body().getUsers().get_id();
               isloggedin=true;
            }
            else{
                isloggedin = false;
                   }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return isloggedin;

    }

}
