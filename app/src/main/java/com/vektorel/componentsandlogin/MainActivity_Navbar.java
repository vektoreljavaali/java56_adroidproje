package com.vektorel.componentsandlogin;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vektorel.componentsandlogin.databinding.ActivityMainNavbarBinding;
import com.vektorel.componentsandlogin.models.FirebaseUser;
import com.vektorel.componentsandlogin.util.StaticValues;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity_Navbar extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainNavbarBinding binding;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());
        binding = ActivityMainNavbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainActivityNavbar.toolbar);
        binding.appBarMainActivityNavbar.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_camera,R.id.nav_news,R.id.nav_messages,R.id.nav_userlist,R.id.nav_notification)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_navbar);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        try {
            listNews();
           // simplerequest();
        } catch (JSONException e) {
            Log.d("HATA...: ", "onCreate: "+ e.toString());
            e.printStackTrace();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = new FirebaseUser("Muhammet HOCA", "muhammedli55@gmail.com");
        mDatabase.child("users").child(user.id).setValue(user);
        mDatabase.push();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("HATA", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = "Bir Veri GELDİ...: "+ token;
                        Log.d("HATA", msg);
                        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

      //  agkontrol();
    }

    private static final String DEBUG_TAG = "NetworkStatusExample";
    private void agkontrol(){

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }
        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
        Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity__navbar, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_navbar);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void listNews() throws JSONException {
        JSONObject header = new JSONObject();
        //header.put("Content-Type", "application/json");
        header.put("x-rapidapi-key", "b89209a659msh0ec234f307a0d24p17b89djsn58fe7ac67670");
        header.put("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                , "https://apidojo-yahoo-finance-v1.p.rapidapi.com/auto-complete?q=tesla&region=US", header,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RESPONSE", "onResponse: " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HATA", "onErrorResponse: "+ error.toString());
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void simplerequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("SIMPLEREQ", "onResponse: "+ response.substring(0,500));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SIMPLEHATA", "onErrorResponse: "+error.toString());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}