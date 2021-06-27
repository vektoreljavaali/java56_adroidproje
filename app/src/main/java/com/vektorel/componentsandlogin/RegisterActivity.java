package com.vektorel.componentsandlogin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vektorel.componentsandlogin.dao.UserDao;
import com.vektorel.componentsandlogin.models.User;
import com.vektorel.componentsandlogin.util.StaticValues;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    EditText name;
    EditText username;
    EditText password;
    EditText repasswors;
    TextView btnlogin;
    Button btnregister;
    Intent openPage;
    UserDao dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.register_page_name);
        username = findViewById(R.id.register_page_username);
        password = findViewById(R.id.register_page_password);
        repasswors = findViewById(R.id.register_page_repassowrd);
        btnlogin = findViewById(R.id.register_page_btn_login);
        btnregister = findViewById(R.id.register_page_btn_register);
        dbUser = new UserDao(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void afterTextChanged(Editable editable) {
                if(!password.getText().toString().equals(repasswors.getText().toString()))
                {
                    password.setBackgroundColor(Color.GREEN);
                    repasswors.setBackgroundColor(Color.RED);
                }
                if (password.getText().toString().isEmpty()||repasswors.getText().toString().isEmpty()){
                    password.setBackgroundColor(Color.WHITE);
                    repasswors.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    private void register() {
        if(name.getText().toString().trim().isEmpty()
          || username.getText().toString().trim().isEmpty()
          || password.getText().toString().trim().isEmpty()
          || repasswors.getText().toString().trim().isEmpty()
        ){
            Toast.makeText(this,"Lütfen gerekli alanları doldurunuz...",(int) 1000).show();
        }
        else if(!password.getText().toString().equals(repasswors.getText().toString())){
            Toast.makeText(this,"Şifreler uyuşmuyor...",(int)1000).show();
        }else{
            //StaticValues.userList.add( new User(UUID.randomUUID().toString(),name.getText().toString(),username.getText().toString(),password.getText().toString()));
            dbUser.save(new User(null
                    ,name.getText().toString()
                    ,username.getText().toString()
                    ,password.getText().toString()));
            Toast.makeText(this,"Yeni Üye kaydı başaralı ile yapıldı...",(int)2000).show();
            login();
        }
    }

    private void login() {
        openPage = new Intent(this,LoginActivity.class);
        startActivity(openPage);
    }
}