package com.vektorel.componentsandlogin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vektorel.componentsandlogin.dao.UserDao;
import com.vektorel.componentsandlogin.util.StaticValues;
public class LoginActivity extends AppCompatActivity {

    EditText txt_username;
    EditText txt_password;
    TextView label_register;
    Button btn_login;
    //String username= "admin",password="12345";
    Intent openPage;
    UserDao dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Burası Çok Önemli: Bileşen Instance larını oluşturacağınız yer. Üstte Content' in tanımlanmasından
        // hemen sonra yapılmalıdır. Çünkü, R, aradığı bileşenin hangi layout & fragmet içinde olduğunu bilmeli
        // ayrıca, tanımlanmış olan layout içinde ilgili bileşen id si yok ise bileşen null olarak tanımlanır ve hata veririr.
        txt_password = findViewById(R.id.login_page_txt_password);
        txt_username = findViewById(R.id.login_page_txt_username);
        btn_login = findViewById(R.id.login_page_btn_giris);
        label_register = findViewById(R.id.login_page_label_register);
        // Android sistemlerde kullanılan interface ler içinde kodlama yapmayınız.
        // tüm kodlamalarınızı methodlar üzerinde yaparak, ilgili dinleyiciler üzerinden
        // çağırarak yapınız.
        btn_login.setOnClickListener(view -> DbLogin());
        label_register.setOnClickListener(view -> register());
        dbUser= new UserDao(this);
    }

    private void register() {
        openPage = new Intent(this,RegisterActivity.class);
        startActivity(openPage);
    }

    public void login(){
        //Log.d("0000","Login Butonu Tıklandı...: "+ txt_username.getText());
        int count = (int) StaticValues.userList.stream()
                .filter(x-> x.getUsername().equals(txt_username.getText().toString().trim())
                        && x.getPassword().equals(txt_password.getText().toString().trim())).count();
        if(count>0){

        //if(username.equals(txt_username.getText().toString().trim()) &&
        //    password.equals(txt_password.getText().toString().trim())){
            ///Toast.makeText(this,"Giriş Yapoldı", (int) 1000).show();
            // (This)Bu sayfa dan, MainActivity sayfasına geçiş için yol oluştur.
            openPage = new Intent(this, MainActivity.class);
            startActivity(openPage);
        }else
        {
            Toast.makeText(this,"Kullanıcı Adı ya da Şifre Hatalı",Toast.LENGTH_LONG).show();
        }

    }

    public void DbLogin(){
        if(dbUser.IsPasswordTrue(txt_username.getText().toString().trim(),txt_password.getText().toString()))
        {
            openPage = new Intent(this, MainActivity_Navbar.class);
            startActivity(openPage);
        }
        else
        {
            Toast.makeText(this,"Kullanıcı Adı ya da Şifre Hatalı",Toast.LENGTH_LONG).show();
        }
    }
}