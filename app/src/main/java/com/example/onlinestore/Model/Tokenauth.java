package com.example.onlinestore.Model;

public class Tokenauth {

    private String token;
    private String _id;
    private UserDetails users;
    private  String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public UserDetails getUsers() {
        return users;
    }

    public void setUsers(UserDetails users) {
        this.users = users;
    }

    public Tokenauth(String token, UserDetails users, String _id) {
        this.token = token;
        this.users = users;
        this._id = _id;
    }


}