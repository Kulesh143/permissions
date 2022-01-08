package com.kulesh.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void makecall(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Intent.ACTION_CALL)== PackageManager.PERMISSION_GRANTED){
                Intent i=new Intent(Intent.ACTION_CALL);
                Uri uri=Uri.parse("tel:0773562890");
                i.setData(uri);
                startActivity(i);
            }else {
                String permissions[]=new String[1];
                permissions[0]= Manifest.permission.CALL_PHONE;
                requestPermissions(permissions,101);
                Intent i=new Intent(Intent.ACTION_CALL);
                Uri uri=Uri.parse("tel:0773562890");
                i.setData(uri);
                startActivity(i);
            }
        }
    }

    public void camera(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission("android.permission.CAMERA")== PackageManager.PERMISSION_GRANTED){
                Intent i=new Intent("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(i);
            }else{
                String permission[]=new String[]{"android.media.action.STILL_IMAGE_CAMERA"};
                requestPermissions(permission,12);
                Intent i=new Intent("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(i);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==12){
            if(grantResults[0]==0){
                Intent i=new Intent("android.permission.CAMERA");
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(), "CAMera permission Error!", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode==101){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                try {
                    PermissionInfo permissionInfo=getPackageManager().getPermissionInfo(permissions[0],PackageManager.GET_META_DATA);
                    String permissionDetails=permissionInfo.loadLabel(getPackageManager()).toString();
                    Toast.makeText(getApplicationContext(), permissionDetails, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Error Try again!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}