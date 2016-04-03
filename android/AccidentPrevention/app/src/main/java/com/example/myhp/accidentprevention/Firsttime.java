package com.example.myhp.accidentprevention;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by my hp on 3/2/2016.
 */
public class Firsttime extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodmain);
        findViewById(R.id.firsttime_login).setOnClickListener(this);
        findViewById(R.id.firsttime_signup).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.firsttime_login:
                Class i = null;
                try {
                    i = Class.forName("com.example.myhp.accidentprevention.Login");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent k = new Intent(getApplicationContext(), i);
                startActivity(k);
                break;
            case R.id.firsttime_signup:
                Class l = null;
                try {
                    l = Class.forName("com.example.myhp.accidentprevention.Signup");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent f = new Intent(getApplicationContext(), l);
                startActivity(f);
                break;
        }
    }
}
