package com.example.permissionsapptarea;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView cancelView;
    private TextView continueView;
    private Switch storage;
    private Switch location;
    private Switch camera;
    private Switch phone;
    private Switch contacts;
    private List<String> reqPerms = new ArrayList<>();
    private final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cancelView = findViewById(R.id.cancelTxtView);
        continueView = findViewById(R.id.continueTxtView);
        storage = findViewById(R.id.storageSwitch);
        location = findViewById(R.id.locationSwitch);
        camera = findViewById(R.id.cameraSwitch);
        phone = findViewById(R.id.phoneSwitch);
        contacts = findViewById(R.id.contactSwitch);
        verifyPermissions();

//        continueView.setOnClickListener(view -> {
//            onContinue();
//        });
    }

    public void killApp(View view) {

        if(view.getId() == cancelView.getId())
        {
            finishAndRemoveTask();
        }
    }

    public void onContinue(View view) {

        if(view.getId() == continueView.getId())
        {
            if (storage.isChecked() &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                reqPerms.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (location.isChecked() &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                reqPerms.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (camera.isChecked() &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                reqPerms.add(Manifest.permission.CAMERA);
            }
            if (phone.isChecked() &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
                reqPerms.add(Manifest.permission.CALL_PHONE);
            }
            if (contacts.isChecked() &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
                reqPerms.add(Manifest.permission.READ_CONTACTS);
            }

            if(!reqPerms.isEmpty())
            {
                ActivityCompat.requestPermissions(this, reqPerms.toArray(new String[reqPerms.size()]), PERMISSION_REQUEST_CODE);
            }else {
                Intent intent = new Intent(getApplicationContext(), PermissionsActivity.class);
                startActivity(intent);

            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(getApplicationContext(), PermissionsActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(MainActivity.this, "All permissions were denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void verifyPermissions(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
           storage.setChecked(true);

        }
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location.setChecked(true);

        }
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            camera.setChecked(true);

        }
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
           phone.setChecked(true);

        }
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            contacts.setChecked(true);

        }
    }

    public void permissionGranted(View view){

        if(view.getId() == R.id.storageSwitch &&
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

            storage.setChecked(true);
            Snackbar.make(view,"Storage permissions can't be revoked from here", Snackbar.LENGTH_LONG).show();

        }else if(view.getId() == R.id.locationSwitch &&
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            location.setChecked(true);
            Snackbar.make(view,"Location permissions can't be revoked from here", Snackbar.LENGTH_LONG).show();

        }else if(view.getId() == R.id.cameraSwitch &&
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

            camera.setChecked(true);
            Snackbar.make(view,"Camera permissions can't be revoked from here", Snackbar.LENGTH_LONG).show();

        }else if(view.getId() == R.id.phoneSwitch &&
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

            phone.setChecked(true);
            Snackbar.make(view,"Phone calls permissions can't be revoked from here", Snackbar.LENGTH_LONG).show();

        }else if(view.getId() == R.id.contactSwitch &&
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){

            contacts.setChecked(true);
            Snackbar.make(view,"Contacts access permissions can't be revoked from here", Snackbar.LENGTH_LONG).show();
        }

    }
}