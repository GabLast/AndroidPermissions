package com.example.permissionsapptarea;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PermissionsActivity extends AppCompatActivity {

    private TextView storageTextView;
    private TextView locationTextView;
    private TextView cameraTextView;
    private TextView phoneTextView;
    private TextView contactsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        storageTextView = findViewById(R.id.storageTxtView);
        locationTextView = findViewById(R.id.locationTxtView);
        cameraTextView = findViewById(R.id.cameraTxtView);
        phoneTextView = findViewById(R.id.phoneTxtView);
        contactsTextView = findViewById(R.id.contactTxtView);
    }
}