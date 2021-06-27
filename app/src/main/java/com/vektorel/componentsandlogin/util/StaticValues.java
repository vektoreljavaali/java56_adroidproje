package com.vektorel.componentsandlogin.util;

import com.vektorel.componentsandlogin.models.User;

import java.util.*;

public class StaticValues {

    public static final int DB_VERSION= 1; // Db de değişiklik yapıldığında güncelleme yapmak için
                                           // kullanılan sayıdır. değişir ise db yi günceller
    // SQL kodlaması şeklinde yapılır. yeni bir tablo oluşturma konutları kullanılır.
    public static final String TBLUSER= "tblusers";
    public static final String TBLUSER_ID= "id";
    public static final String TBLUSER_NAME= "name";
    public static final String TBLUSER_USERNAME= "username";
    public static final String TBLUSER_PASSWORD= "password";


    public static List<User> userList = new ArrayList<User>();

    public static int result_count = 50;
    public static String news_url = "https://randomuser.me/api/?results="+result_count;
}
