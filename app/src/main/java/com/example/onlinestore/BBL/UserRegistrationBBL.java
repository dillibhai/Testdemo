package com.example.onlinestore.BBL;

import com.example.onlinestore.Controller.CreateInstance;
import com.example.onlinestore.Interface.UsersApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRegistrationBBL {
    UsersApi usersApi;
    String success;

    CreateInstance createInstance;

    private String _id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userImage;
    private String city;
    private String postal;
    private String userAddress1;
    private String userAddress2;

    public Response<String> userDetailsResponse;

    public UserRegistrationBBL(String userName,String userEmail,String userPassword,String userImage,String city,String postal,String userAddress1,String userAddress2) {
        this._id = _id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userImage = userImage;
        this.city = city;
        this.postal = postal;
        this.userAddress1 = userAddress1;
        this.userAddress2 = userAddress2;
    }

    public String userRegistration(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.26.0.26:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usersApi = retrofit.create(UsersApi.class);

        Call<String> usersCall = usersApi.registerUser(userName,userEmail,userPassword,userImage,city,postal,userAddress1,userAddress2);
        try{
            userDetailsResponse = usersCall.execute();
            //success =userDetailsResponse.body();
            success = userDetailsResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }
}
