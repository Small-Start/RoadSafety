package com.example.myhp.accidentprevention.volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleySingleton {
    public static VolleySingleton myInstance = null;
    private RequestQueue requestQueue;

    private VolleySingleton(){
        requestQueue = Volley.newRequestQueue(ApplicationWrapper.getAppContext());
    }

    public static VolleySingleton getMyInstance() {
        if (myInstance==null){
            myInstance = new VolleySingleton();
        }
        return myInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
