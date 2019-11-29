package com.example.machinegunrange.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.machinegunrange.MachineGunner;
import com.example.machinegunrange.MainActivity;
import com.example.machinegunrange.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.machinegunrange.MainActivity.machineGunnerArrayList;

/**
 * A Scores fragment containing a simple view.
 */

//TODO Add string resources for layout file
    //TODO Inmplement amazon backend to link and edit data
public class NewShooterFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private EditText lastNameEditText;
    private EditText firstNameEditText;
    private EditText companyEditText;
    private EditText battalionEditText;
    private Spinner rankSpinner;
    private Spinner weaponSpinner;
    private EditText scoreEditText;
    private MaterialButton submitButton;


    public static NewShooterFragment newInstance(int index) {
        NewShooterFragment fragment = new NewShooterFragment();
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


    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.new_shooter_fragment_layout, container, false);

        instantiateInputFields(root);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validate all inputs and then submit and populate array
                if(validateInput()){

                    MachineGunner newShooter = new MachineGunner(
                            lastNameEditText.getText().toString(),
                            firstNameEditText.getText().toString(),
                            companyEditText.getText().toString(),
                            battalionEditText.getText().toString(),
                            rankSpinner.getSelectedItem().toString(),
                            weaponSpinner.getSelectedItem().toString(),
                            Integer.parseInt(scoreEditText.getText().toString())
                    );

                    MainActivity.db.collection("Firers")
                            .add(newShooter)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }

            }
        });

        return root;
    }

    private void instantiateInputFields(View root){

        lastNameEditText = root.findViewById(R.id.last_name_edittext);
        firstNameEditText = root.findViewById(R.id.first_name_edittext);
        companyEditText = root.findViewById(R.id.company_edittext);
        battalionEditText = root.findViewById(R.id.battalion_edittext);
        rankSpinner = root.findViewById(R.id.rank_spinner);
        weaponSpinner = root.findViewById(R.id.weapon_system_spinner);
        scoreEditText = root.findViewById(R.id.shooter_score_edittext);
        submitButton = root.findViewById(R.id.submit_shooter_button);

    }


    private boolean validateInput(){

        //TODO Implement simple checks for the text and input fields

        return true;
    }


}