package com.ara.walli;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","r4kiad213@yahoo.com",null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Enter your report");
        startActivity(Intent.createChooser(emailIntent,"Send email..."));
    }
}
