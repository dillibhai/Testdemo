package com.example.onlinestore;

import com.example.onlinestore.BBL.DisplayProduct;
import com.example.onlinestore.BBL.UserLoginBBL;
import com.example.onlinestore.BBL.UserRegistrationBBL;
import com.example.onlinestore.Model.ItemsDetail;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public abstract class Logintest {

    @Test
    public void testLogin() {
        UserLoginBBL loginBBL = new UserLoginBBL();
        Boolean result = loginBBL.checkUser("aryalaashish121@gmail.com", "changedpass");
        Assert.assertEquals(true, result);
    }

    @Test
    public void RegisterUserTest() {
        UserRegistrationBBL registrationBBL = new UserRegistrationBBL("Zonal", "zo@gmail.com", "passs", "image.jpg", "Zora", "99220", "address1", "address2");


    }
    @Test
    public void testDetails(){
        DisplayProduct displayProduct = new DisplayProduct();
        List<ItemsDetail> itemsDetailList = displayProduct.userDetails();
        String productName = itemsDetailList.get(0).getProductName();
        Assert.assertEquals("Acer E15",productName);

    }
}
