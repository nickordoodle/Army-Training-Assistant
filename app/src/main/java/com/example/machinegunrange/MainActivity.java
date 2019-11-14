package com.example.machinegunrange;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.machinegunrange.ui.main.SectionsPagerAdapter;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    public static Context CONTEXT;
    public static FirebaseFirestore db;
    public static ArrayList<MachineGunner> machineGunnerArrayList;
    public static ExpandableListAdapter machineGunnerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CONTEXT = getApplicationContext();
        PACKAGE_NAME = getApplicationContext().getPackageName();

        // Instantiate Database
        db = FirebaseFirestore.getInstance();

        //TODO update list from range and database
        MachineGunner gunnerOne = new MachineGunner("johnson", "nick",
                "BCO"
                ,"2nd Inf"
                , "1LT"
                , "M249"
                , 157);

        MachineGunner gunnerTwo = new MachineGunner("morty", "rick",
                "ACO"
                ,"2nd Inf"
                , "SGT"
                , "M240"
                , 130);
        machineGunnerArrayList = new ArrayList<>();
        machineGunnerArrayList.add(gunnerOne);
        machineGunnerArrayList.add(gunnerTwo);

        machineGunnerListAdapter = new CustomExpandableListAdapter(CONTEXT,
                machineGunnerArrayList);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
}