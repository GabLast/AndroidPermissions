package com.example.permissionsapptarea;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextView cancelView;
    private TextView continueView;
    private Switch storage;
    private Switch location;
    private Switch camera;
    private Switch phone;
    private Switch contacts;

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

    }

    public void killApp()
    {
        this.finish();
    }


}