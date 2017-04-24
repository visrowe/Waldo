package com.ara.walli;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by r4kia on 4/22/2017.
 */

public class Progress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);

        Toast.makeText(this,"Job has been accepted",Toast.LENGTH_LONG).show();
    }


}
