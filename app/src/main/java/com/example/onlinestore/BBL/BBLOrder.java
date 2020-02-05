package com.example.onlinestore.BBL;
import com.example.onlinestore.Interface.UsersApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BBLOrder {
    UsersApi usersApi;

    public static String userID;
    public static String productID;
    public static Number Quantity;
    private static Date orderedDate;
    private static String orderedTime;

    public boolean UserOrders(String userrID, String pid, Date dateOrder, Integer iQuantity, String time){
        boolean isOrderd = false;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.26.0.26:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usersApi = retrofit.create(UsersApi.class);

        Call<String> ordercall = usersApi.addOrders(productID,userID,orderedDate, (Integer) Quantity,orderedTime);
       try {
           Response<String> response = ordercall.execute();
           isOrderd=true;
       } catch (IOException e) {
           e.printStackTrace();
           isOrderd=false;
       }
       return isOrderd;
    }
}
