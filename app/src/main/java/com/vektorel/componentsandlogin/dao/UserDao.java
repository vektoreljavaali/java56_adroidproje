package com.vektorel.componentsandlogin.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.vektorel.componentsandlogin.models.User;
import com.vektorel.componentsandlogin.util.StaticValues;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDao extends SQLiteOpenHelper {
    String SQLCREATETABLE = "create table "+
            StaticValues.TBLUSER+"(" +
            StaticValues.TBLUSER_ID+" varchar(100)," +
            StaticValues.TBLUSER_NAME+" varchar(100)," +
            StaticValues.TBLUSER_USERNAME+" varchar(120)," +
            StaticValues.TBLUSER_PASSWORD+" varchar(64))";
    private Context context;

    // Bu UserDao yu hangi Activitiy, Fragmen çağırıyor ise onunu Context i buraya girilir.
    public UserDao(Context context) {
        super(context, StaticValues.TBLUSER, null, StaticValues.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLCREATETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void save(User user){
        String insert_sql = "insert into tblusers(id,name,username,password) " +
                "values('"+ UUID.randomUUID().toString() +"','"+user.getName()
                +"','"+user.getUsername()+"','"+user.getPassword()+"')";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insert_sql);
    }

    public void update(User user){
       String update_sql="update tblusers set name='"+user.getName()
               +"', username='"+user.getUsername()
               +"', password='"+user.getPassword()+"' where id="+user.getId();
       SQLiteDatabase db = this.getWritableDatabase();
       db.execSQL(update_sql);
    }

    public void delete(String userid){
        String delete_sql="delete tblusers where id="+userid;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(delete_sql);
    }

    public List<User> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] column_list = {"id","name","username","password"};
        Cursor cr = db.query("tblusers",column_list,null,null,null,null,null);
        List<User> result = new ArrayList<>();
        if(cr.getCount()>0){
            User user;
            while (cr.moveToNext()){
                user = new User(cr.getString(0),cr.getString(1),cr.getString(2),cr.getString(3));
                result.add(user);
            }
            return result;
        }else
            return new ArrayList<>();
    }

    public boolean IsPasswordTrue(String username, String password){
     List<User> list = findAll()
                            .stream()
                            .filter(x->x.getUsername().equals(username) && x.getPassword().equals(password))
                            .collect(Collectors.toList());
     if(list.size()>0)
         return true;
     else
         return false;
    }
}
