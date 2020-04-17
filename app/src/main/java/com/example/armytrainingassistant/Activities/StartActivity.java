package com.example.armytrainingassistant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.armytrainingassistant.R;

public class StartActivity extends AppCompatActivity {

    private ImageButton machineGunRangeButton;
    private ImageButton rifleGunRangeButton;
    private ImageButton pistolGunRangeButton;
    private ImageButton grenadeGunRangeButton;

    private RelativeLayout machineGunRangeLayout;
    private RelativeLayout rifleGunRangeLayout;
    private RelativeLayout pistolGunRangeLayout;
    private RelativeLayout grenadeGunRangeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //TODO Create new range button icons
        Intent mainActivityIntent = new Intent(StartActivity.this, MainActivity.class);

        machineGunRangeLayout = (RelativeLayout) findViewById(R.id.machine_gun_range_grid_relative_layout) ;
        rifleGunRangeLayout = (RelativeLayout) findViewById(R.id.rifle_gun_range_grid_relative_layout) ;
        pistolGunRangeLayout = (RelativeLayout) findViewById(R.id.pistol_gun_range_grid_relative_layout) ;
        grenadeGunRangeLayout = (RelativeLayout) findViewById(R.id.grenade_launcher_range_grid_relative_layout) ;

        machineGunRangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                startActivity(mainActivityIntent);
            }
        });

        rifleGunRangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                startActivity(mainActivityIntent);
            }
        });

        pistolGunRangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                startActivity(mainActivityIntent);
            }
        });

        grenadeGunRangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                startActivity(mainActivityIntent);
            }
        });


    }

}
