package com.example.myhp.accidentprevention;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;


/**
 * Created by my hp on 3/2/2016.
 */
public class Login extends AppCompatActivity implements View.OnClickListener{
SharedPreferences user_info;
   String username,password;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
user_info=getSharedPreferences("userinfo",0);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_login);
        setSupportActionBar(t);

        findViewById(R.id.b_login).setOnClickListener(this);
        pd=new ProgressDialog(Login.this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading....");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.b_login:
              /*  Class i = null;
                try {
                    i = Class.forName("com.example.myhp.bloodbank.Bloodmain");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent k = new Intent(getApplicationContext(), i);
                startActivity(k);
                break;*/
                pd.show();
                username=((EditText)findViewById(R.id.edit_login_username)).getText().toString();
                password=((EditText)findViewById(R.id.edit_login_password)).getText().toString();
                String url = "http://bloodbanksys.esy.es/bloodbank/login.php";
            /*    JSONObject obj=new JSONObject();
                try {
                    obj.put("username",username);
                    obj.put("password",password);
                    obj.put("pno",pno);
                    obj.put("bloodgroup",bloodgroup);
                    obj.put("email",email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


// Request a string response from the provided URL.
                JsonObjectRequest jsObjRequest = null;

                jsObjRequest = new JsonObjectRequest
                        (Request.Method.POST, url,obj, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                //try {
                                    //mTextView.setText(response.getString("respMsg"));
                             //   } catch (JSONException e) {
                                 //   e.printStackTrace();
                             //   }
                                Toast.makeText(Signup.this,"its working"+response.toString(),Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub
                                Toast.makeText(Signup.this,"its not working",Toast.LENGTH_LONG).show();
                            }


                        })
                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/json;charset=utf-8");
                        params.put("Accept", "application/json");

                        return params;
                    }
                };

                int socketTimeout = 7000;//30 seconds - change to what you want
                RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                jsObjRequest.setRetryPolicy(policy);


// Add the request to the RequestQueue.
                if(jsObjRequest!=null)
                    queue.add(jsObjRequest);
                break;
        }*/

                //Creating a string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                pd.hide();
                                Toast.makeText(Login.this,response, Toast.LENGTH_LONG).show();
                                SharedPreferences sp=getSharedPreferences("user",0);
                                SharedPreferences.Editor editor=sp.edit();
                                editor.putString("username",username);
                                editor.commit();

                                Class i = null;
                                try {
                                    i = Class.forName("com.example.myhp.accidentprevention.MainActivity");
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                Intent k = new Intent(getApplicationContext(), i);
                                startActivity(k);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //You can handle error here if you want
                                pd.hide();
                                Toast.makeText(Login.this, "its not working", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        //Adding parameters to request
                       params.put("username",username);
                        params.put("password",password);

                        //returning parameter
                        return params;
                    }
                };

                //Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

                int socketTimeout = 20000;//20 seconds - change to what you want
                RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                stringRequest.setRetryPolicy(policy);
                break;
        }
        }

}
