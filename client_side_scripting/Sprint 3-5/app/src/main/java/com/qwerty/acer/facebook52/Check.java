package com.qwerty.acer.facebook52;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Check extends AppCompatActivity {

    Button cont;
    /*Geocoder geocoder;
    final int maxResult=5;
    String addressList[]=new String[maxResult];
    private ArrayAdapter<String> adapter;
*/
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar2);
        View view=getSupportActionBar().getCustomView();


        Toolbar parent =(Toolbar) view.getParent();
        parent.setPadding(0,0,0,0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0,0);

        /*final ListView list=(ListView)findViewById(R.id.listView);

        LocationManager mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        MyLocationListner lstr = new MyLocationListner();
        if (ActivityCompat.checkSelfPermission(Check.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Check.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;

        }
        mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,lstr);
        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lstr);

*/
        cont=(Button) findViewById(R.id.button7);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent displaycheck =new Intent(Check.this,Near.class);
                startActivity(displaycheck);
            }
        });

        TextView cancel;
        cancel=(TextView)findViewById(R.id.textView14);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancellation=new Intent(Check.this,Home.class);
                startActivity(cancellation);
            }
        });

    }
}
