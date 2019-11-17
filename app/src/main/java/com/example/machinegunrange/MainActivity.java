package com.example.machinegunrange;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.machinegunrange.ui.main.SectionsPagerAdapter;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CONTEXT = getApplicationContext();
        PACKAGE_NAME = getApplicationContext().getPackageName();

        // Instantiate Database
        db = FirebaseFirestore.getInstance();


        machineGunnerArrayList = new ArrayList<>();

        //create and set adapter
        machineGunnerListAdapter = new CustomExpandableListAdapter(CONTEXT,
                machineGunnerArrayList);


        //TODO update list from range and database
        //TODO fix query problem, cannot pull data at moment
        //Initial query request here
        db.collection("Firers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                //Log each retrieved document data
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                //populate data to controller
                                if(document.exists()){
                                    MachineGunner newGunner = document.toObject(MachineGunner.class);
                                    machineGunnerArrayList.add(newGunner);
                                }

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        Log.d("ADAPTER", "CREATED");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    public void updateList() {
        viewPager.getAdapter().notifyDataSetChanged();
    }
}