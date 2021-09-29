package com.example.permissionsapptarea;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;

public class PermissionsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

    }


    public void openStorage(View view){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//            https://stackoverflow.com/questions/58550549/how-to-use-intent-action-open-document-in-android-pie
            Snackbar snack = Snackbar.make(view,"Press OPEN to proceed", Snackbar.LENGTH_LONG);
            snack.setAction("OPEN", new ReadStorage());
            snack.show();
        }else {
            Snackbar.make(view,"No storage permissions have been granted", Snackbar.LENGTH_LONG).show();
        }

    }

    public class ReadStorage implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/*");
            startActivity(intent);
        }
    }

    public void openLocation(View view){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//           https://developers.google.com/maps/documentation/urls/android-intents
            Snackbar snack = Snackbar.make(view,"Press OPEN to proceed", Snackbar.LENGTH_LONG);
            snack.setAction("OPEN", new OpenMap());
            snack.show();
        }else {
            Snackbar.make(view,"No location permissions have been granted", Snackbar.LENGTH_LONG).show();
        }
    }

    public class OpenMap implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");

            Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        }
    }


    public void openCamera(View view){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            Snackbar snack = Snackbar.make(view,"Press OPEN to proceed", Snackbar.LENGTH_LONG);
            snack.setAction("OPEN", new openCam());
            snack.show();
        }else {
            Snackbar.make(view,"No camera permissions have been granted", Snackbar.LENGTH_LONG).show();
        }

    }

    public class openCam implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        }
    }

    public void openPhone(View view){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//          https://stackoverflow.com/questions/4275678/how-to-make-a-phone-call-using-intent-in-android
            Snackbar snack = Snackbar.make(view,"Press OPEN to proceed", Snackbar.LENGTH_LONG);
            snack.setAction("OPEN", new MakeACall());
            snack.show();
        }else {
            Snackbar.make(view,"No phone calls permissions have been granted", Snackbar.LENGTH_LONG).show();
        }

    }

    public class MakeACall implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            String uri = "tel:" + "123-456-7890";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        }
    }

    public void openContacts(View view){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//            https://stackoverflow.com/questions/9955783/how-do-i-open-contacts-when-i-click-a-button-defined-in-main-xml
            Snackbar snack = Snackbar.make(view,"Press OPEN to proceed", Snackbar.LENGTH_LONG);
            snack.setAction("OPEN", new OpenContacts());
            snack.show();
        }else {
            Snackbar.make(view,"No contacts permissions have been granted", Snackbar.LENGTH_LONG).show();
        }

    }

    public class OpenContacts implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
            startActivity(intent);
        }
    }

}