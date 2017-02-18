package com.example.abhishek.whereiam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity1 extends AppCompatActivity implements LocationListener {

    LocationManager ln;
    TextView t1;
    TextView t2;
    LocationProvider p1;
    Geocoder g1;
    List l1;

    double longitude;
    double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        g1=new Geocoder(this);
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
        ln=(LocationManager) getSystemService(LOCATION_SERVICE);
        String prov;
        if(ln.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
       prov  =LocationManager.NETWORK_PROVIDER;
        else
        prov=LocationManager.GPS_PROVIDER;
        ln.requestLocationUpdates(prov, 100000, 1, this);
        t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView3);
    }

      @Override
      public void onLocationChanged(Location location) {


        //  t1.setText("Address:::");
         // t2.setText(location.getLongitude()+"");
       latitude=location.getLatitude();
          longitude=location.getLongitude();

        try {
            l1=  g1.getFromLocation(latitude,longitude,1);
          } catch (IOException e) {
             // e.printStackTrace();
          }

          if(l1!=null) {
              t2.setText(l1.get(0).toString());
          }
          else
          {
              Toast.makeText(this,"null",Toast.LENGTH_LONG).show();
          }



      }

    public void get(View v)
    {

        Intent in=new Intent(Intent.ACTION_VIEW);
        in.setData(Uri.parse("geo:"+longitude+","+latitude));
        startActivity(in);

    }


    @Override
      public void onStatusChanged(String provider, int status, Bundle extras) {

      }

      @Override
      public void onProviderEnabled(String provider) {

      }

      @Override
      public void onProviderDisabled(String provider) {

      }


  }
