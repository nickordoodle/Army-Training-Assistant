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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        machineGunRangeButton = (ImageButton) findViewById(R.id.machine_gun_button);
        machineGunRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        //TODO Create new range button icons


    }

}
