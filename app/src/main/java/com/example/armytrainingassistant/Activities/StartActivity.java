package com.example.armytrainingassistant.Activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.armytrainingassistant.R;

public class StartActivity extends AppCompatActivity {

    private ImageButton machineGunRangeButton;
    private ImageButton rifleGunRangeButton;
    private ImageButton pistolGunRangeButton;
    private ImageButton grenadeGunRangeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        machineGunRangeButton = (ImageButton) findViewById(R.id.machine_gun_button);
        rifleGunRangeButton = (ImageButton) findViewById(R.id.m4_m16_gun_range_button);
        pistolGunRangeButton = (ImageButton) findViewById(R.id.m9_m17_range_button);
        grenadeGunRangeButton = (ImageButton) findViewById(R.id.m320_m203_range_button);

        //TODO Create new range button icons

        Intent mainActivityIntent = new Intent(StartActivity.this, MainActivity.class);

        machineGunRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Add extra activity designator to determine which type of range
                startActivity(mainActivityIntent);
            }
        });

        rifleGunRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                startActivity(mainActivityIntent);
            }
        });

        pistolGunRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                startActivity(mainActivityIntent);
            }
        });

        grenadeGunRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                startActivity(mainActivityIntent);
            }
        });



    }

}
