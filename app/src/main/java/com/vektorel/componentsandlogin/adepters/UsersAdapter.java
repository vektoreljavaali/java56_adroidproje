package com.vektorel.componentsandlogin.adepters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vektorel.componentsandlogin.R;
import com.vektorel.componentsandlogin.models.JsonUser;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    List<JsonUser> userlist;
    Context context;
    ImageView userimage;
    TextView title;
    TextView content;
    Button btnname,btnemail,btnbirddate, btnlocation, btnpassword,btnphone;
    public UsersAdapter(Context context,List<JsonUser> userlist){
        this.context = context;
        this.userlist = userlist;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(R.layout.user_card_view,parent,false);
        MyViewHolder myresult = new MyViewHolder(view);
        return myresult;
    }
    // Burada artık gerekli setleme işlemlerini yapıuyoruz.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        userimage = holder.mView.findViewById(R.id.image_photo);
        title = holder.mView.findViewById(R.id.news_cell_txttitle);
        content = holder.mView.findViewById(R.id.news_cell_txtcontent);
        btnbirddate = holder.mView.findViewById(R.id.news_cell_btnbirdday);
        btnemail =  holder.mView.findViewById(R.id.news_cell_btnemail);
        btnlocation =  holder.mView.findViewById(R.id.news_cell_btnaddress);
        btnname =  holder.mView.findViewById(R.id.news_cell_btnusername);
        btnpassword =  holder.mView.findViewById(R.id.news_cell_btnpassword);
        btnphone =  holder.mView.findViewById(R.id.news_cell_btnphone);
        JsonUser user = userlist.get(position);
        title.setText("Müşteri Adı");
        content.setText(user.getName());
        btnname.setOnClickListener(view -> name(holder,position) );
        btnphone.setOnClickListener(view -> phone(holder,position) );
        btnpassword.setOnClickListener(view -> passowrd(holder,position) );
        btnlocation.setOnClickListener(view -> location(holder,position) );
        btnemail.setOnClickListener(view -> email(holder,position));
        btnbirddate.setOnClickListener(view -> birddate(holder,position) );
        Glide
            .with(this.context) // Context atanır
            .load(user.getUserurl()) // internet üzerinden url adresi ile çekilecek resmin URL i alınır.
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.mipmap.login_page_logo_round) // İnternetten yüklenen resim yükleninceye kadar gösterilmek istenilen resimdir.
            .into(userimage); // yüklenilen resmi ImageView a set eder.
    }
    private void name(MyViewHolder holder,int position){
        JsonUser user = userlist.get(position);
        title = holder.mView.findViewById(R.id.news_cell_txttitle);
        content = holder.mView.findViewById(R.id.news_cell_txtcontent);
        title.setText("Müşteri Adı");
        content.setText(user.getName());
    }
    private void email(MyViewHolder holder,int position){
        JsonUser user = userlist.get(position);
        title = holder.mView.findViewById(R.id.news_cell_txttitle);
        content = holder.mView.findViewById(R.id.news_cell_txtcontent);
        title.setText("Müşteri E-Posta");
        content.setText(user.getEmail());
    }
    private void location(MyViewHolder holder,int position){
        JsonUser user = userlist.get(position);
        title = holder.mView.findViewById(R.id.news_cell_txttitle);
        content = holder.mView.findViewById(R.id.news_cell_txtcontent);
        title.setText("Müşteri Adresi");
        content.setText(user.getAdres());
    }
    private void birddate(MyViewHolder holder,int position){
        JsonUser user = userlist.get(position);
        title = holder.mView.findViewById(R.id.news_cell_txttitle);
        content = holder.mView.findViewById(R.id.news_cell_txtcontent);
        title.setText("Müşteri Doğum Tarihi");
        content.setText(user.getBirddate());
    }
    private void phone(MyViewHolder holder,int position){
        JsonUser user = userlist.get(position);
        title = holder.mView.findViewById(R.id.news_cell_txttitle);
        content = holder.mView.findViewById(R.id.news_cell_txtcontent);
        title.setText("Müşteri Telefon Numarası");
        content.setText(user.getPhone());
    }
    private void passowrd(MyViewHolder holder,int position){
        JsonUser user = userlist.get(position);
        title = holder.mView.findViewById(R.id.news_cell_txttitle);
        content = holder.mView.findViewById(R.id.news_cell_txtcontent);
        title.setText("Müşteri Şifresi");
        content.setText(user.getPassword());
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        public MyViewHolder(View view){
            super(view);
            mView = view;
        }
    }
}
