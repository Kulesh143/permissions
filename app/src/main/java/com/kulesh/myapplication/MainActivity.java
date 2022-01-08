package com.kulesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dial(View view) {
        Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:1234567"));
        startActivity(i);
    }

    public void loca(View view) {
        Intent ii=new Intent(Intent.ACTION_VIEW,Uri.parse("geo:-27.560341,152.957733"));
        startActivity(ii);
    }

    public void web(View view) {
        Intent o=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
        startActivity(o);
    }

    public void sendm(View view) {
        Intent p=new Intent(Intent.ACTION_SEND);
        p.setType("text/plain");
        p.setData(Uri.parse("smsto:1234567"));
        p.putExtra("sms-body","hello");
        startActivity(p);
    }

    public void exp(View view) {
        Intent i=new Intent(this,MainActivity2.class);
        i.putExtra("e","Kulesh");
        i.putExtra("r","Uchiha");
        startActivity(i);
    }

    public void appgo(View view) {
//        Intent i=new Intent("com.kulesh.myapplication");
        Intent i=new Intent(Intent.ACTION_VIEW);

        startActivity(i);
    }

    public void shutdown(View view) {
        Intent i=new Intent(Intent.ACTION_SHUTDOWN);
        if(i.resolveActivity(getPackageManager())!=null){
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), "App Not Found!!!", Toast.LENGTH_SHORT).show();
        }
    }
}