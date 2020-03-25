package com.example.machinegunrange;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.machinegunrange.ui.main.ScoresFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.machinegunrange.ui.main.SectionsPagerAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;

import static android.content.ContentValues.TAG;
import static com.google.firebase.firestore.DocumentChange.Type.MODIFIED;
import static com.google.firebase.firestore.DocumentChange.Type.REMOVED;
import static com.google.firebase.firestore.core.DocumentViewChange.Type.ADDED;

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

        //Handle changes and updates to data
        db.collection("Firers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapShots,
                                @Nullable FirebaseFirestoreException e) {

                machineGunnerArrayList.clear();
                for (DocumentSnapshot shot : snapShots) {
                    machineGunnerArrayList.add(shot.toObject(MachineGunner.class));
                }

                updateList();
            }

        });


        Log.d("ADAPTER", "CREATED");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }

    public static void updateList() {
        viewPager.getAdapter().notifyDataSetChanged();
    }

}