package com.qwerty.acer.facebook52;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Near extends AppCompatActivity  {

    Geocoder geocoder;
    final int maxResult=5;
    String addressList[]=new String[maxResult];
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near);

        final ListView list=(ListView)findViewById(R.id.listView);






        LocationManager mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        MyLocationListner lstr = new MyLocationListner();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        Toast.makeText(Near.this, "Latitude : " + MyLocationListner.mLatitude + "Longitude :" + MyLocationListner.mLongitude, Toast.LENGTH_SHORT).show();

        geocoder = new Geocoder(Near.this, Locale.getDefault());

        try {

            List<Address> addresses;
            addresses = geocoder.getFromLocation(MyLocationListner.mLatitude, MyLocationListner.mLongitude, maxResult);

            if (addresses != null&& addresses.size()>0) {


                for (int j = 0; j < maxResult; j++) {
                    Address returnedAddress = addresses.get(j);
                    System.out.println(returnedAddress);
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < returnedAddress.getMaxAddressLineIndex();i++)
                    {
                        sb.append(returnedAddress.getAddressLine(i)).append("\n");

                    }
                    addressList[j] = sb.toString();

                }

               // adapter=new ArrayAdapter<String>(Near.this,android.R.layout.activity_list_item,addressList);
                //list.setAdapter(adapter);
//                 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                  android.R.layout.simple_list_item_1, android.R.layout.activity_list_item, addressList);

                adapter = new ArrayAdapter<String>(Near.this, android.R.layout.simple_list_item_1, addressList);
                list.setAdapter(adapter);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intents=new Intent(Near.this,Posting.class);
                //String string=list.getSelectedItem().toString();
                String val=(String)(list.getItemAtPosition(i));
                intents.putExtra("select",val);
                startActivity(intents);







            }

        });

    }
}
