package com.qwerty.acer.facebook52;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    public static final String KEY_PREF="sharedpref";
    public static final String LOGIN_URL = "http://services.trainees.baabtra.com/BM-41776/login_page.php";
    public static final String KEY_EMAIL = "uname";
    public static final String KEY_PASSWORD = "pwd";

    SharedPreferences sharedPreferences;

    //SharedPreferences.Editor editor = sharedPreferences.edit();

    Button create_ac, log;
    EditText eml, pswd;

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);


        log = (Button) findViewById(R.id.button);
        create_ac = (Button) findViewById(R.id.button2);
        eml = (EditText) findViewById(R.id.editText);
        pswd = (EditText) findViewById(R.id.editText2);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlog();

            }
        });
        create_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });
    }

    private void userlog() {
        email = eml.getText().toString();
        password = pswd.getText().toString();

        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString("uname",email);
        editor.putString("pwd",password);
        editor.commit();

        StringRequest stringrequest = new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("200") && response.contains("Success")) {
                    openLogedin();
                } else {
                    Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
            }


        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_EMAIL, email);
                map.put(KEY_PASSWORD, password);
                return map;
            }
        };

        RequestQueue req = Volley.newRequestQueue(this);
        req.add(stringrequest);



    }

    private void openLogedin() {

        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
    }
}