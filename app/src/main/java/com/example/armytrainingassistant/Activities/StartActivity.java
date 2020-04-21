package com.example.armytrainingassistant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.armytrainingassistant.R;

public class StartActivity extends AppCompatActivity {

    private static final String FRAGMENT_FILE_KEY_CHOOSER = "fragment_key_chooser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //TODO Create new range button icons
        Intent mainActivityIntent = new Intent(StartActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();

        RelativeLayout machineGunRangeLayout = findViewById(R.id.machine_gun_range_grid_relative_layout) ;
        RelativeLayout rifleGunRangeLayout = findViewById(R.id.rifle_gun_range_grid_relative_layout) ;
        RelativeLayout pistolGunRangeLayout = findViewById(R.id.pistol_gun_range_grid_relative_layout) ;
        RelativeLayout grenadeGunRangeLayout = findViewById(R.id.grenade_launcher_range_grid_relative_layout) ;

        machineGunRangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                mainActivityIntent.putExtra(FRAGMENT_FILE_KEY_CHOOSER, 0);
                startActivity(mainActivityIntent);
            }
        });

        rifleGunRangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                mainActivityIntent.putExtra(FRAGMENT_FILE_KEY_CHOOSER, 1);
                startActivity(mainActivityIntent);
            }
        });

        pistolGunRangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                mainActivityIntent.putExtra(FRAGMENT_FILE_KEY_CHOOSER, 2);
                startActivity(mainActivityIntent);
            }
        });

        grenadeGunRangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Add extra activity designator to determine which type of range
                mainActivityIntent.putExtra(FRAGMENT_FILE_KEY_CHOOSER, 3);
                startActivity(mainActivityIntent);
            }
        });


    }

}
