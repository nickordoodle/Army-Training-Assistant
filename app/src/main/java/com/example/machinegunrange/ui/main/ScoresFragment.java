package com.example.machinegunrange.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.machinegunrange.CustomExpandableListAdapter;
import com.example.machinegunrange.MachineGunner;
import com.example.machinegunrange.MailSender;
import com.example.machinegunrange.MainActivity;
import com.example.machinegunrange.R;
import com.example.machinegunrange.Utilities;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Scores fragment containing a simple view.
 */
public class ScoresFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static ExpandableListView listView;
    private PageViewModel pageViewModel;
    private Button eraseButton;
    private Button exportButton;
    private View.OnClickListener eraseButtonListener;
    private View.OnClickListener exportButtonListener;

    public static ScoresFragment newInstance(int index) {
        ScoresFragment fragment = new ScoresFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        eraseButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.collection("Firers")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    document.getReference().delete();
                                }
                                MainActivity.updateList();
                                listView.invalidateViews();
                                Toast success = Toast.makeText(getActivity(),
                                        "Deleted all firers",
                                        Toast.LENGTH_SHORT);
                                success.setGravity(Gravity.CENTER, 0, 0);
                                success.show();

                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                });
            }
        };

        exportButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.sendEmailWithDialog(getContext());

            }
        };
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.scores_fragment_layout, container, false);

        //link data adapter to the list view
        listView = (ExpandableListView) root.findViewById(R.id.scores_expandable_listview);
        listView.setAdapter(MainActivity.machineGunnerListAdapter);
        listView.setGroupIndicator(null);

        //init buttons and set listeners to handle onclick
        eraseButton = root.findViewById(R.id.erase_button);
        eraseButton.setOnClickListener(eraseButtonListener);

        exportButton = root.findViewById(R.id.export_button);
        exportButton.setOnClickListener(exportButtonListener);

        return root;
    }

}