package com.qwerty.acer.facebook52;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Posting extends AppCompatActivity {

    public static final String POST_URL = "http://services.trainees.baabtra.com/BM-41776/check_in_page.php";
    public static final String KEY_LOCATUION = "Location";
    public static final String KEY_STATUS = "Status";
    public String adrs;

    TextView address, post, at;
    EditText sts;


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);


        at = (TextView) findViewById(R.id.textView16);
        sts = (EditText) findViewById(R.id.editText13);
        address = (TextView) findViewById(R.id.textView17);

        Intent intents = getIntent();
        adrs = intents.getStringExtra("select");
        address.setText(adrs);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_3);
        View view = getSupportActionBar().getCustomView();

        Toolbar parent = (Toolbar) view.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);

        post = (TextView) findViewById(R.id.textView20);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                status_posting();



            }

        });

    }

    private void status_posting() {

        final String my_status = sts.getText().toString();
        System.out.println(my_status);
        final String my_location = address.getText().toString();
        System.out.println(my_location);

        StringRequest request = new StringRequest(Request.Method.POST, POST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("200") && response.contains("Success")) {


                    next_page();

                } else {
                    Toast.makeText(Posting.this, "status posting is failed", Toast.LENGTH_LONG).show();
                }

            }

            private void next_page() {
                Intent intent = new Intent(Posting.this, Home.class);

                intent.putExtra("sel",adrs);
                startActivity(intent);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Posting.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_LOCATUION, my_location);
                map.put(KEY_STATUS, my_status);

                return map;
            }

        };
        RequestQueue req = Volley.newRequestQueue(Posting.this);
        req.add(request);
    }




}











































