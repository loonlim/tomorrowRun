package com.example.tomorrowrun.tomorrow.login_register;

public class User {

        private int id;
        private String username;
    private String email;
    private String gender;
    private String fullname;
    private String phonenum;
    private String address;


    public User(int id, String username, String email, String gender, String fullname, String phonenum, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.fullname = fullname;
        this.phonenum = phonenum;
        this.address  = address;
    }
    public int getId() {return id;}
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getGender() {
        return gender;
    }
    public String getFullname() {
        return fullname;
    }
    public String getPhonenum() {
        return phonenum;
    }
    public String getAddress() {return address;} }
