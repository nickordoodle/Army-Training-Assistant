package com.example.armytrainingassistant;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.armytrainingassistant.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    public static Context CONTEXT;
    public static FirebaseFirestore db;
    public static CustomExpandableListAdapter machineGunnerListAdapter;
    public static ViewPager viewPager;
    public static String senderEmail;
    public static String senderCred;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CONTEXT = getApplicationContext();
        PACKAGE_NAME = CONTEXT.getPackageName();

        senderEmail = getString(R.string.admin_email);
        senderCred = getString(R.string.admin_email_cred);

        // Instantiate Database
        db = FirebaseFirestore.getInstance();

        //create and set adapter
        machineGunnerListAdapter = new CustomExpandableListAdapter(CONTEXT);

        Log.d("ADAPTER", "CREATED");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);




    }

}