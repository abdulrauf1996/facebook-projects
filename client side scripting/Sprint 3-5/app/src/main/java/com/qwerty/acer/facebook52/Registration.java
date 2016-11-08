package com.qwerty.acer.facebook52;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {


    public static final String REG_URL="http://services.trainees.baabtra.com/BM-41776/registration.php";
    public static final String KEY_FIRSTNAME="FirstName";
    public static final String KEY_SURNAME="Surname";
    public static final String KEY_PHONEOREMAIL="Email_Phone";
    public static final String KEY_GENDER="Gender";
    public static final String KEY_DATE="Date";
    public static final String KEY_MONTH="Month";
    public static final String KEY_YEAR="Year";
    public static final String KEY_PASSWORD="Password";



    TextView tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    Button bt1;
    Spinner sp1;
    String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tx1=(TextView)findViewById(R.id.textView);
        tx2=(TextView)findViewById(R.id.textView2);
        tx3=(TextView)findViewById(R.id.textView3);
        tx4=(TextView)findViewById(R.id.textView4);
        tx5=(TextView)findViewById(R.id.textView5);
        tx6=(TextView)findViewById(R.id.textView6);
        tx7=(TextView)findViewById(R.id.textView7);
        tx8=(TextView)findViewById(R.id.textView8);

        ed1=(EditText)findViewById(R.id.editText3);
        ed2=(EditText)findViewById(R.id.editText4);
        ed3=(EditText)findViewById(R.id.editText5);
        ed4=(EditText)findViewById(R.id.editText6);
        ed5=(EditText)findViewById(R.id.editText7);
        ed6=(EditText)findViewById(R.id.editText8);
        ed7=(EditText)findViewById(R.id.editText9);

        bt1=(Button)findViewById(R.id.button3);
        sp1=(Spinner)findViewById(R.id.spinner);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                item = adapterView.getItemAtPosition(position).toString();
                System.out.println(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration();

            }
        });

    }

    private void registration() {
        final String f_name = ed1.getText().toString();
        System.out.println(f_name);
        final String s_name = ed2.getText().toString();
        System.out.println(s_name);
        final String email = ed3.getText().toString();
        System.out.println(email);
        final String date = ed4.getText().toString();
        System.out.println(date);
        final String month = ed5.getText().toString();
        System.out.println(month);
        final String year = ed6.getText().toString();
        System.out.println(year);
        final String pswd = ed7.getText().toString();
        System.out.println(pswd);


        StringRequest stringrequest = new StringRequest(Request.Method.POST, REG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.contains("200") && response.contains("Success")) {
                    Intent intent = new Intent(Registration.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Registration.this, "registration failed", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Registration.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_FIRSTNAME, f_name);
                map.put(KEY_SURNAME, s_name);
                map.put(KEY_PHONEOREMAIL, email);
                map.put(KEY_GENDER, item);
                map.put(KEY_DATE, date);
                map.put(KEY_MONTH, month);
                map.put(KEY_YEAR, year);
                map.put(KEY_PASSWORD, pswd);

                return map;
            }
        };

        RequestQueue req = Volley.newRequestQueue(this);
        req.add(stringrequest);

    }





}
