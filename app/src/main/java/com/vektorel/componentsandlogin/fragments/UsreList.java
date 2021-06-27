package com.vektorel.componentsandlogin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.vektorel.componentsandlogin.R;
import com.vektorel.componentsandlogin.dao.UserDao;
import com.vektorel.componentsandlogin.models.User;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public class UsreList extends Fragment {

    ListView myuserlist;
    UserDao dbuser;
    Context context;
    List<String> iller = new ArrayList<>();

    public UsreList() {
        iller.add("Ankara");
        iller.add("İstanbul");
        iller.add("Samsun");
        iller.add("Bursa");
        iller.add("Hatay");
        iller.add("Trabzon");
        iller.add("Giresun");
        iller.add("Sivas");
        iller.add("Zonguldak");
        iller.add("Antalya");

    }

    /**
     * Bu Fragmenti çağıran bir activity kendi context ini bu fragment e verebilir.
     * @param context
     */
    public UsreList(Context context){
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_usre_list, container, false);
       this.context = inflater.getContext();
       dbuser = new UserDao(this.context);
       myuserlist = view.findViewById(R.id.list_user);
        List<String> datas = new ArrayList<>();
        for (User user: dbuser.findAll()) {
            datas.add(user.getName());
        }
        // Burada listview için gerekli adapter ile tasarım yapıyoruz.
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, android.R.id.text1,iller);
        myuserlist.setAdapter(arrayAdapter);
        myuserlist.setOnItemClickListener((adapterView, view1, i, l) -> onClikList(i));
       return view;
    }

    /**
     * ListView içindeki item lardan hangisiner tıklanıldığını bildirir.
     * @param i
     */
    private void onClikList(int i) {
        User user = dbuser.findAll().get(i);
        Toast.makeText(this.context,"Kullanıcıadı...: "+user.getUsername() + " Şifre...: "+user.getPassword(),(int) 5000).show();
    }

    public int toplam(int s1,int s2){
        return s1+s2;
    }
    public int toplam2(int s1,int s2){
        int topla = s1+s2;
        return topla;
    }

}