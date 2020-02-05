package com.example.onlinestore;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinestore.BBL.BBLOrder;
import com.example.onlinestore.Interface.UsersApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Order extends AppCompatActivity {

    ImageView itemImage;
    EditText itemQuantity,userID,productID;
    TextView itemPrice,itemName,itemTotal;
    Button placeOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        itemImage = findViewById(R.id.itemImage);
        itemName= findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.itemPrice);
        itemQuantity = findViewById(R.id.orderQuantity);
        itemTotal = findViewById(R.id.totalprice);
        userID = findViewById(R.id.userID);
        productID = findViewById(R.id.productID);

        placeOrder = findViewById(R.id.order);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrders();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            itemName.setText(bundle.getString("productName"));
            itemPrice.setText((bundle.getString("productPrice")));
            userID.setText(bundle.getString("userID"));
            productID.setText(bundle.getString("productID"));
            String itemImages = bundle.getString("productImage");
            Picasso.with(this).load(itemImages).into(itemImage);
        }

    }

    public void addOrder(){
        String userrID = userID.getText().toString();
        String pid = productID.getText().toString();
        Integer iQuantity = 1;
        Date dateOrder = new Date();
        String time = String.valueOf(Calendar.getInstance().getTime());
        BBLOrder order = new BBLOrder();
        
        StrictMode();
        if(order.UserOrders(pid,userrID,dateOrder,iQuantity,time)){
            Toast.makeText(this, "Ordered Sucessfully !!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void StrictMode(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void addOrders(){
        String time = String.valueOf(Calendar.getInstance().getTime());
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        UsersApi usersApi = retrofit.create(UsersApi.class);

        Call<String> orderCall = usersApi.addOrders(productID.getText().toString(),userID.getText().toString(),new Date(), Integer.valueOf(itemQuantity.getText().toString()),time);
        orderCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(Order.this, "Sucessful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Order.this, "SUcessully Added", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
