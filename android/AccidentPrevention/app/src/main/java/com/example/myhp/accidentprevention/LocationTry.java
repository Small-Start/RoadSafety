package com.example.myhp.accidentprevention;

/**
 * Created by my hp on 4/3/2016.
 */

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocationTry extends AppCompatActivity implements LocationListener,TextToSpeech.OnInitListener{
    LocationManager locManager;
    LocationListener li;
    String id="abc";
    TextView tv_accident,tv_location;
    String username;
    String detail;
    ProgressDialog pd;
    String location;
    double distance;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationtry);
        tv_accident = (TextView) findViewById(R.id.tv_locationtry_accident);
tv_location=(TextView) findViewById(R.id.tv_locationtry_location);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_location);
        setSupportActionBar(t);
      //  pd=new ProgressDialog(getApplicationContext(),ProgressDialog.STYLE_SPINNER);
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //li = new speed();
       // calllocation(54.61,88.36);
        username=getSharedPreferences("user",0).getString("username","kani");
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
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        //locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,);
    }

    @Override
    public void onLocationChanged(Location location) {
        Float thespeed = location.getSpeed();
        Double lat=location.getLatitude();
        Double lng=location.getLongitude();
         // tv.setText("Location -"+String.valueOf(lat)+String.valueOf(lng)+"\n Speed: "+String.valueOf(thespeed));
        calllocation(lat,lng);
        Log.v("speed", String.valueOf(thespeed));
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


    public void calllocation(final Double lat,final Double lng) {
        //pd.show();
        String url = "http://bloodbanksys.esy.es/bloodbank/safety.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                   //     pd.hide();
if(response.equals("Safe Area"))
    tv_location.setText("Safe Area");
                        else {
    try {
        JSONArray ar = new JSONArray(response);
        JSONObject obj=ar.getJSONObject(0);
        location = obj.getString("location");
        distance = obj.getDouble("metres");
        detail = obj.getString("crashDetail");
        if(!id.equals(obj.getString("id"))) {

            id = obj.getString("id");
            tv_location.setText(location);
            tv_accident.setText(detail);

            givewarning();
        }

    } catch (JSONException e) {
        e.printStackTrace();
    }
    Log.v("JSON",response);

}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                       // pd.hide();
                        Toast.makeText(getApplicationContext(), "its not working", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("username",username);
                params.put("lat",String.valueOf(lat));
                params.put("lng",String.valueOf(lng));

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        int socketTimeout = 20000;//20 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
    }

    private void givewarning() {

        tts=new TextToSpeech(getApplicationContext(), this);

    }

    @Override
    public void onInit(int status) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak("You have a accident prone zone at "+location,TextToSpeech.QUEUE_FLUSH,null,"up");
        }
        else {

        }

    }
}