package com.example.mapdemo;

import android.app.Activity;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends Activity {
    GoogleMap gMap;
    LocationManager locationMngr;
    Handler handler;
    private LocationListener locationListner;
    PolylineOptions rectOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // locationMngr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         //rectOptions = new PolylineOptions();


       gMap =((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
      // gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


       //gMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(35.2270869, -80.843167)));
       //gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.2270869, -80.843167),13));
      // gMap.addMarker(new MarkerOptions().position(new LatLng(35.2270869, -80.843167)).title("Welcome to CLT"));

       gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

        @Override
        public void onMapClick(LatLng position) {
            // TODO Auto-generated method stub
            gMap.addMarker(new MarkerOptions().position(position));
            gMap.setMyLocationEnabled(true);
           //locationMngr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListner);
            //rectOptions.add(position);

            //Polyline polyline = gMap.addPolyline(rectOptions);

        }
    });

    }
}
