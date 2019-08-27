package com.example.m240range;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button mNewRangeButton;
    Button mSavedRangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        mNewRangeButton = (Button) findViewById(R.id.new_range_button);
        mSavedRangesButton = (Button) findViewById(R.id.saved_ranges_button);

        final Intent intent = new Intent(this, MainActivity.class);

        mNewRangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        mSavedRangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
