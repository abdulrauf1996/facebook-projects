package com.qwerty.acer.facebook52;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {

    TextView check_in, txt1, txt2,txt3, status_post, name1,name2,name3,txt_post;
    EditText ed_post;
    Button btn_post;
    ListView list,list_img;
    ImageView img_view1;


    SharedPreferences sharedPreferences;
    public static final String NAME_URL = "http://services.trainees.baabtra.com/BM-41776/getting_name.php";
    public static final String KEY_EMAIL = "email";
    public static final String STATUS_URL="http://services.trainees.baabtra.com/BM-41776/status_listing.php";
    public static final String KEY_EMAIL1 = "email";



    public String sts;

    private String first_name,status,eml,string_image;

    ArrayList<String> statuslist=new ArrayList<>();


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
        eml = sharedPreferences.getString("uname", "");


        name1 = (TextView) findViewById(R.id.textView28);
        name3=(TextView)findViewById(R.id.textView36);
        name2=(TextView)findViewById(R.id.textView42);
        txt1 = (TextView) findViewById(R.id.textView30);
        txt2 = (TextView) findViewById(R.id.textView31);

        Intent intents = getIntent();
        final String value = intents.getStringExtra("sel");

        check_in = (TextView) findViewById(R.id.textView10);
        ed_post = (EditText) findViewById(R.id.editText10);
        status_post = (TextView) findViewById(R.id.textView9);
        btn_post = (Button) findViewById(R.id.button4);
        txt_post=(TextView)findViewById(R.id.textView43);

//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setCustomView(R.layout.activity_action);
//        View view = getSupportActionBar().getCustomView();


//        Toolbar parent = (Toolbar) view.getParent();
//        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
//        parent.setContentInsetsAbsolute(0, 0);

        check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentObj = new Intent(Home.this, Check.class);
                startActivity(intentObj);
            }
        });

        status_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent object = new Intent(Home.this, Status_Posting.class);
                startActivity(object);

            }
        });


        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout4);
        RelativeLayout ralative = (RelativeLayout) findViewById(R.id.post);
        RelativeLayout rlv=(RelativeLayout)findViewById(R.id.first_status);

        txt1.setText(value);
        txt2.setText(value);

        Intent intent = getIntent();
        sts = intent.getStringExtra("statusposting");
        txt_post.setText(sts);

        show_status(eml);






        if (sts != null) {
            ralative.setVisibility(View.VISIBLE);
            rlv.setVisibility(View.VISIBLE);

        } else {
            ralative.setVisibility(View.INVISIBLE);
            rlv.setVisibility(View.INVISIBLE);
        }


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

                        name1.setText(first_name);

                        name2.setText(first_name);

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
        })

        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_EMAIL, eml);
                return map;
            }
        };
        RequestQueue req1 = Volley.newRequestQueue(this);
        req1.add(stringrequest);

    }


    public void show_status(final String eml)
    {
        StringRequest strRequest=new StringRequest(Request.Method.POST, STATUS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("200") && response.contains("Success"))
                {

                    try {
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject ob=array.getJSONObject(i);
                            JSONArray values = ob.getJSONArray("Status");
                            for(int j=0;j<values.length()-1;j++)
                            {
                                status=values.getString(j);
                                statuslist.add(status);
                            }
                        }
                        CustomAdapter custom=new CustomAdapter(Home.this,statuslist);
                        list=(ListView)findViewById(R.id.listView2);
                        list.setAdapter(custom);


//
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        })

        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_EMAIL1, eml);
                return map;
            }
        };
        RequestQueue req = Volley.newRequestQueue(this);
        req.add( strRequest);

    }

    }





