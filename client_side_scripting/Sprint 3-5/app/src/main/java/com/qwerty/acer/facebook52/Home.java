package com.qwerty.acer.facebook52;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {

    TextView check_in, txt1, txt2, name;
     SharedPreferences sharedPreferences;
    public static final String NAME_URL = "http://services.trainees.baabtra.com/BM-41776/getting_name.php";
    public static final String KEY_EMAIL = "email";

    private String email;
    private String first_name;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        sharedPreferences = getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        final String eml = sharedPreferences.getString("uname", "");



        name = (TextView) findViewById(R.id.textView28);
        txt1 = (TextView) findViewById(R.id.textView30);
        txt2 = (TextView) findViewById(R.id.textView31);
        Intent intents = getIntent();
        final String value = intents.getStringExtra("sel");
        check_in=(TextView)findViewById(R.id.textView10);



        txt1.setText(value);
        txt2.setText(value);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout4);

        if (value != null) {
            relativeLayout.setVisibility(View.VISIBLE);
        } else {
            relativeLayout.setVisibility(View.INVISIBLE);
        }



        StringRequest stringrequest = new StringRequest(Request.Method.POST, NAME_URL, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                System.out.println(response);
                JSONArray array = null;
                try {
                    array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject values = array.getJSONObject(i);
                        first_name = values.getString("first_name");
                        System.out.println(first_name);
                        name.setText(first_name);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_EMAIL, eml);
                return map;
            }
        };
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(stringrequest);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_action);
        View view = getSupportActionBar().getCustomView();


        Toolbar parent = (Toolbar) view.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);

        check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentObj = new Intent(Home.this, Check.class);
                startActivity(intentObj);
            }
        });



    }
}

