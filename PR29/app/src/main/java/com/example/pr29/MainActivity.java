package com.example.pr29;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton buttonCamera;
    private ImageButton buttonMaps;
    private ImageButton buttonPhone;
    private ImageButton buttonBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCamera = findViewById(R.id.ButtonCamera);
        buttonMaps = findViewById(R.id.ButtonMaps);
        buttonPhone = findViewById(R.id.ButtonPhone);
        buttonBrowser = findViewById(R.id.ButtonBrowser);

        buttonCamera.setOnClickListener(this);
        buttonMaps.setOnClickListener(this);
        buttonPhone.setOnClickListener(this);
        buttonBrowser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ButtonCamera) {
            openCamera();
        } else if (id == R.id.ButtonMaps) {
            openMaps();
        } else if (id == R.id.ButtonPhone) {
            openPhone();
        } else if (id == R.id.ButtonBrowser) {
            openBrowser();
        }
    }

    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    private void openMaps() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=Krasnyy Prospekt,72, Novosibirsk, Novosibirskaya oblast', Russia, 630091");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void openPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }

    private void openBrowser() {
        Uri webpage = Uri.parse("https://natk.ru/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}