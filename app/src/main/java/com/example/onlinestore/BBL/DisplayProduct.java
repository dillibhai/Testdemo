package com.example.onlinestore.BBL;

import com.example.onlinestore.Interface.UsersApi;
import com.example.onlinestore.Model.ItemsDetail;
import com.example.onlinestore.url.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayProduct {

    Retrofit retrofit;
    UsersApi usersApi;

    boolean isSucess = false;
    public static List<ItemsDetail> items;

    public void createInstance() {
        retrofit = new Retrofit.Builder().baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usersApi = retrofit.create(UsersApi.class);
    }

    public List<ItemsDetail> userDetails() {
        createInstance();
        Call<List<ItemsDetail>> itemdetails = usersApi.getItemDetail();
        try {
            Response<List<ItemsDetail>> response = itemdetails.execute();
            if(response.isSuccessful()){
                items = response.body();
                return items;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}