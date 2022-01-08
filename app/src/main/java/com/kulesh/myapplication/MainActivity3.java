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

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void makecall(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Intent.ACTION_CALL) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:07789367");
                intent.setData(uri);
                startActivity(intent);
            }else{
                // Toast.makeText(getApplicationContext(),"No Call Phone Permission",Toast.LENGTH_SHORT).show();
                String permissions[] = new String[1];
                permissions[0] = Manifest.permission.CALL_PHONE ;
                // we can use ACTION_CALL constant value also
                requestPermissions(permissions,101);
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:07789367");
                intent.setData(uri);
                startActivity(intent);
            }
        }
        }


    public void location(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission("android.permission.CAMERA") == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(intent);
            }else{
                String permissions[] = new String[]{"android.media.action.STILL_IMAGE_CAMERA"};
                requestPermissions(permissions,12);
                Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==12){
            if(grantResults[0]==0){
                Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(intent);
            }else{
                Toast.makeText(this,"Camera Permissions Error",Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    PermissionInfo permissionInfo = getPackageManager().getPermissionInfo(permissions[0], PackageManager.GET_META_DATA);
                    String permissionDetails = permissionInfo.loadLabel(getPackageManager()).toString();
                    Toast.makeText(getApplicationContext(), permissionDetails, Toast.LENGTH_SHORT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Error Try Again", Toast.LENGTH_SHORT);
            }
        }
    }
}