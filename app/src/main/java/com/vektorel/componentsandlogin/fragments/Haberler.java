package com.vektorel.componentsandlogin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vektorel.componentsandlogin.R;
import com.vektorel.componentsandlogin.adepters.UsersAdapter;
import com.vektorel.componentsandlogin.models.JsonUser;
import com.vektorel.componentsandlogin.util.StaticValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Haberler extends Fragment {

    List<JsonUser> userlist;
    String TAG = "BILDIRIM";
    RecyclerView userListRec;
    Context context;
    public Haberler() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment_haberler, container, false);
        userListRec = view.findViewById(R.id.rc_user_list);
        context = view.getContext();
        RequestQueue queue = Volley.newRequestQueue(context);
        userlist = new ArrayList<>();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET,StaticValues.news_url ,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("results");
                            JsonUser user;
                            for (int i=0; i<array.length();i++){ // Json Listesini tek tek dönüyoruz.
                                JSONObject item = (JSONObject) array.get(i);
                                JSONObject name = item.getJSONObject("name");
                                JSONObject dob = item.getJSONObject("dob");
                                JSONObject location = item.getJSONObject("location");
                                JSONObject login = item.getJSONObject("login");
                                JSONObject picture = item.getJSONObject("picture");

                                user = new JsonUser(
                                        picture.getString("large"),
                                        name.getString("title")+" "+ name.getString("first")+" "+name.getString("last"),
                                        item.getString("email"),
                                        item.getString("phone"),
                                        dob.getString("date"),
                                        location.getString("country")+ " "+location.getString("city"),
                                        login.getString("password")
                                );
                                userlist.add(user);
                            }
                           ListeyiYukle();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "onErrorResponse: "+ error.toString());
            }
        });
        // Kod yukarıdan aşağıya işletilir.
        // anca async olan kodlar, arka planda çalışır ve kod akışının içerisinde sonlanır.
        // XXXX this.ListeyiYukle();
        queue.add(objectRequest);
        return view;
    }

    private void ListeyiYukle() {
        UsersAdapter usersAdapter = new UsersAdapter(this.context,userlist);
        userListRec.setAdapter(usersAdapter);
        // Bunu Unutmuşum
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        userListRec.setLayoutManager(linearLayoutManager);

    }


}