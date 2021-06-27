package com.vektorel.componentsandlogin.models;


public class JsonUser {
    String name;
    String email;
    String phone;
    String birddate;
    String adres;
    String password;
    String userurl;
    public JsonUser(String userurl,String name, String email, String phone, String birddate, String adres, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birddate = birddate;
        this.adres = adres;
        this.password = password;
        this.userurl = userurl;
    }

    public String getUserurl() {
        return userurl;
    }

    public void setUserurl(String userurl) {
        this.userurl = userurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirddate() {
        return birddate;
    }

    public void setBirddate(String birddate) {
        this.birddate = birddate;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
