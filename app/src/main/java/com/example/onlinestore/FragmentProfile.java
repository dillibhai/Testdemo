package com.example.onlinestore;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinestore.Interface.UsersApi;
import com.example.onlinestore.Model.ItemsDetail;
import com.example.onlinestore.Model.UserDetails;
import com.example.onlinestore.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {
    SharedPreferences preferencesLogout;
    SharedPreferences.Editor editor;
    Button login;
    ImageView userimage,updateUserDetails;
    TextView username, useremail;
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    //api
    UsersApi usersApi;



    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View profileView = inflater.inflate(R.layout.fragment_profile, container, false);
        final SharedPreferences preferences=getActivity().getSharedPreferences("UserData",0);
        String tok=preferences.getString("token",null);
        String uid = preferences.getString("userid",null);
        //Toast.makeText(getContext(), "User id: "+uid, Toast.LENGTH_SHORT).show();
        if(tok==null||tok.equals("")){
            closefragment();
            Intent intent = new Intent(getActivity(),Login.class);
            startActivity(intent);
        }else{
            loaduserdata();
        }

        //user details from xml file
        userimage = profileView.findViewById(R.id.user_profile_photo);
        username = profileView.findViewById(R.id.user_profile_name);
        useremail = profileView.findViewById(R.id.user_profile_email);
        updateUserDetails = profileView.findViewById(R.id.updateProfileDetail);

        updateUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userUpdate = new Intent(getContext(),UpdateProfile.class);
                startActivity(userUpdate);
            }
        });
        login = profileView.findViewById(R.id.login);

        // login code i.e Delete user token value for again relogging in..
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesLogout = (getActivity()).getSharedPreferences("UserData",0);
                editor = preferencesLogout.edit();
                editor.putString("token", "");
                editor.putString("uid", "");
                editor.commit();
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });
        return profileView;
    }


    private void closefragment() {

        getActivity().getFragmentManager().popBackStack();
    }

    //loading users data here......
    private void loaduserdata(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usersApi = retrofit.create(UsersApi.class);
        SharedPreferences preferences=getActivity().getSharedPreferences("UserData",0);
        String userid = preferences.getString("uid",null);
      //  Toast.makeText(getContext(), "Bellow one user id: "+userid, Toast.LENGTH_LONG).show();
        final Call<UserDetails> userdata= usersApi.profiledata(userid);
        userdata.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                UserDetails userdetails = response.body();
                username.setText(userdetails.getUserName());
                useremail.setText(userdetails.getUserEmail());
                StrictMode();
                try{
                    String imgurl = BASE_URL+userdetails.getUserImage();
                    URL url = new URL(imgurl);
                    userimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                    // updateimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Toast.makeText(getContext(), "Failure here : "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
}
    private void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

}
