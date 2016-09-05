package com.qwerty.acer.facebooknew;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText em,pswd;
    Button log,reg;
    DbHelp mydb;
    TextView tx1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DbHelp(this);

        tx1=(TextView)findViewById(R.id.textView);
        em=(EditText)findViewById(R.id.editText);
        pswd=(EditText)findViewById(R.id.editText2);
        log=(Button)findViewById(R.id.button);
        reg=(Button)findViewById(R.id.button2);

        //log.setOnClickListener(new View.OnClickListener() {
          //  @Override public void onClick(View view)
            //{
              //  boolean inserted=mydb.insertData(em.getText().toString(),pswd.getText().toString());
                //if(inserted==true)
                //{
                  //  Toast.makeText(getApplicationContext(),"email and password are inserted",Toast.LENGTH_SHORT).show();
                //}


            //}
        //});
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getData();
                if (res.getCount()==0)
                {
                    showData("error","no data is inserted");
                }
                StringBuffer str=new StringBuffer();
                while (res.moveToNext())
                {
                    str.append("id:"+res.getString(0)+"\n");
                    str.append("email:"+res.getString(1)+"\n");
                    str.append("password:"+res.getString(2)+"\n");
                }
                showData("success",str.toString());

            }


        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail=em.getText().toString();
                String pwd=pswd.getText().toString();
                String stored=mydb.getEntry(mail);
                if (pwd.equals(stored))
                {
                    Toast.makeText(getApplicationContext(), "Congrates:Login Success", Toast.LENGTH_SHORT).show();
                    Intent obj=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(obj);
                }
                else {


                    tx1.setText("Email or password does not match ");
                    tx1.setVisibility(View.VISIBLE);
                }

            }
        });

    }
    public void showData(String title,String msg)
    {
        AlertDialog.Builder alt=new AlertDialog.Builder(this);
        alt.setCancelable(true);
        alt.setMessage(msg);
        alt.setTitle(title);
        alt.show();
    }





}
