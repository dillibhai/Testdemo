package com.example.onlinestore.Model;

import java.sql.Time;
import java.util.Date;

public class OrderModel {
    private String _id;
    private String productID;
    private String userID;
    private Date orderedDate;
    private int Quantity;
    private String orderedTime;

    public OrderModel(String _id, String productID, String userID, Date orderedDate, int quantity, String orderedTime) {
        this._id = _id;
        this.productID = productID;
        this.userID = userID;
        this.orderedDate = orderedDate;
        Quantity = quantity;
        this.orderedTime = orderedTime;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(String orderedTime) {
        this.orderedTime = orderedTime;
    }
}