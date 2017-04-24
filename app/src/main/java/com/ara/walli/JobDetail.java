package com.ara.walli;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JobDetail extends AppCompatActivity {
    TextView d_name, d_jname, d_description, d_pay, d_location;
    Button abtn;
    double dpay = 0.0;
    String newString, newString2, newString3, newString4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_detail_layout);
        d_name = (TextView) findViewById(R.id.d_name);
        d_jname = (TextView) findViewById(R.id.d_jname);
        d_description = (TextView) findViewById(R.id.d_description);
        d_pay = (TextView) findViewById(R.id.d_jpay);
        d_location = (TextView) findViewById(R.id.d_jlocation);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
                newString2 = null;
            } else {
                newString = extras.getString("Address");
                newString2 = extras.getString("User");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("Address");
            newString2 = (String) savedInstanceState.getSerializable("User");
        }
        d_name.setText("Author : " + getIntent().getStringExtra("d_name"));
        d_jname.setText("Title : " + getIntent().getStringExtra("d_jname"));
        d_description.setText("Description : " + getIntent().getStringExtra("d_description"));
        d_pay.setText("Payment : " + getIntent().getDoubleExtra("d_jpay", dpay) + "$");
        d_location.setText("Location : " + getIntent().getStringExtra("d_jlocation"));
        abtn = (Button) findViewById(R.id.abtn);
        abtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JobDetail.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to accept this job?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(JobDetail.this, MapIntent.class);
                                newString3 = getIntent().getStringExtra("d_jlocation");
                                newString4 = getIntent().getStringExtra("d_name");
                                intent.putExtra("Address", newString);
                                intent.putExtra("User", newString2);
                                intent.putExtra("TargetLocation", newString3);
                                intent.putExtra("UserName", newString4);
                                startActivity(intent);
                                OnDelete(v);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    public void OnDelete(View view) {
        String ename = d_name.getText().toString();
        String jname = d_jname.getText().toString();
        String jdes = d_description.getText().toString();
        String jpay = d_pay.getText().toString();
        String jlocation = d_location.getText().toString();
        String type = "Delete";
        DatabaseInsert databaseInsert = new DatabaseInsert(this);
        databaseInsert.execute(type, ename, jname, jdes, jpay, jlocation);

    }

}
