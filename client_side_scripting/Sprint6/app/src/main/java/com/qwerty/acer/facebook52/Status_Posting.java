package com.qwerty.acer.facebook52;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Status_Posting extends AppCompatActivity {

    public static final String POST_URL = "http://services.trainees.baabtra.com/BM-41776/posting.php";
    public static final String KEY_POST = "Status";
    public static final String KEY_EML="email";
    public static final String UPLOAD_URL="http://services.trainees.baabtra.com/BM-41776/img_insertion.php";
    public static final String KEY_EML1="email";
    public static final String IMAGE_URL="http://services.trainees.baabtra.com/BM-41776/img_pick.php";
    public static final String KEY_EMAIL2 = "email";

    SharedPreferences sharedPreferences;

    ImageView img,imageview;
    EditText abt_photo;
    TextView txt_post,post;
    EditText ed_status;
    ImageButton goto_gallery;
    String status,eml,string_image;

    private Uri selectedImage;
    ArrayList<String> bitmaps=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status__posting);

        sharedPreferences=getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        eml=sharedPreferences.getString("uname","");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_post);
        View view = getSupportActionBar().getCustomView();


        Toolbar parent = (Toolbar) view.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);


        ed_status = (EditText) findViewById(R.id.editText14);
        txt_post = (TextView) findViewById(R.id.textView24);
        goto_gallery = (ImageButton) findViewById(R.id.imageButton15);

        goto_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int SELECT_IMAGE = 1234;
                startActivityForResult(i, SELECT_IMAGE);
            }
        });

        txt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sts_posting();
            }


        });
    }

    private void sts_posting() {
        status = ed_status.getText().toString();

        StringRequest str_request = new StringRequest(Request.Method.POST, POST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("200") && response.contains("Success")) {
                    open_sts_posting();
                } else {
                    Toast.makeText(Status_Posting.this, response, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Status_Posting.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_POST, status);
                map.put(KEY_EML,eml);
                return map;
            }
        };
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(str_request);
    }

    private void open_sts_posting() {

        String string = (String) ed_status.getText().toString();
        Intent intent = new Intent(Status_Posting.this, Home.class);
        intent.putExtra("statusposting", string);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1234:
                if (resultCode == RESULT_OK) {
                    selectedImage = data.getData();
                    String[] filepathcolumn = {MediaStore.Images.Media.DATA};
                    System.out.println(filepathcolumn);

                    Cursor cursor = getContentResolver().query(selectedImage, filepathcolumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filepathcolumn[0]);
                    final String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    setContentView(R.layout.activity_img_pick);

                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setDisplayShowCustomEnabled(true);
                    getSupportActionBar().setCustomView(R.layout.action_post);
                    View view = getSupportActionBar().getCustomView();

                    Toolbar parent = (Toolbar) view.getParent();
                    parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
                    parent.setContentInsetsAbsolute(0, 0);

                    img=(ImageView)findViewById(R.id.imageView7);
                    img.setImageBitmap(BitmapFactory.decodeFile(filePath));

                    abt_photo=(EditText)findViewById(R.id.editText15);
                    post=(TextView)findViewById(R.id.textView24);

                    post.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            uploadMultiPart();
                            show_images(eml);


                            setContentView(R.layout.activity_home);



                            RelativeLayout img_layout=(RelativeLayout)findViewById(R.id.img_list);

                            if (filePath!=null)
                            {
                                img_layout.setVisibility(View.VISIBLE);




                            }
                            else
                            {
                                img_layout.setVisibility(View.INVISIBLE);
                            }

//                            ImageView img_home;
//                             img_home=(ImageView)findViewById(R.id.imageView9);
//                            img_home.setImageBitmap(BitmapFactory.decodeFile(filePath));


                        }
                    });
          }
        }
    }

    private void uploadMultiPart() {

        String name = abt_photo.getText().toString().trim();

        //getting the actual path of the image
        String path = getPath(selectedImage);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", name)
                    .addParameter("email",eml)//Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload


        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private String getPath(Uri uri) {
        Cursor cus= getContentResolver().query(uri, null, null, null, null);
        cus.moveToFirst();
        String doc_id=cus.getString(0);
        doc_id=doc_id.substring(doc_id.lastIndexOf(":")+1);
        cus.close();

        cus=getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,MediaStore.Images.Media._ID+"= ?",new String[]{doc_id},null);
        cus.moveToFirst();

        String path=cus.getString(cus.getColumnIndex(MediaStore.Images.Media.DATA));
        cus.close();

        return path;
    }

    private void show_images(final String eml) {
        StringRequest str = new StringRequest(Request.Method.POST, IMAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.contains("200")&&response.contains("Success")) {
                    try {
                        System.out.println(response);
                        JSONArray ary = new JSONArray(response);
                        for (int i=0;i<response.length();i++)
                        {
                            JSONObject obj=ary.getJSONObject(i);
                            JSONArray array=obj.getJSONArray("Msg");
                            for (int j=0;j<array.length();j++)
                            {
                                string_image=array.getString(j);
                                System.out.println(string_image);

                                bitmaps.add(string_image);
                            }
                        }
                        ImageCustom custom_adp=new ImageCustom(Status_Posting.this,bitmaps);
                        setContentView(R.layout.activity_home);
                        ListView listView=(ListView)findViewById(R.id.listView4);
                        listView.setAdapter(custom_adp);

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_EMAIL2, eml);
                return map;

            }
        };
        RequestQueue req1 = Volley.newRequestQueue(this);
        req1.add(str);

    }



}
