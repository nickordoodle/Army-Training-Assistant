package com.example.armytrainingassistant.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.armytrainingassistant.Trainee;
import com.example.armytrainingassistant.MainActivity;
import com.example.armytrainingassistant.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.SetOptions;

import static android.content.ContentValues.TAG;

/**
 * A Scores fragment containing a simple view.
 */

//TODO Add string resources for layout file
public class NewTraineeFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private EditText lastNameEditText;
    private EditText firstNameEditText;
    private EditText companyEditText;
    private EditText battalionEditText;
    private Spinner rankSpinner;
    private Spinner weaponSpinner;
    private EditText scoreEditText;
    private MaterialButton addButton;


    public static NewTraineeFragment newInstance(int index) {
        NewTraineeFragment fragment = new NewTraineeFragment();
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
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validate all inputs and then submit and populate array
                if(validateInput()){

                    final Trainee newShooter = new Trainee(
                            lastNameEditText.getText().toString(),
                            firstNameEditText.getText().toString(),
                            companyEditText.getText().toString(),
                            battalionEditText.getText().toString(),
                            rankSpinner.getSelectedItem().toString(),
                            weaponSpinner.getSelectedItem().toString(),
                            Integer.parseInt(scoreEditText.getText().toString())
                    );

                    Log.d(TAG, "DocumentSnapshot added with ID: " + MainActivity.user.getUid());
                    MainActivity.db.collection("Users")
                        .document(MainActivity.user.getUid())
                        .collection("Trainees")
                        .add(newShooter)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                MainActivity.machineGunnerListAdapter.addItem(newShooter);
                                MainActivity.machineGunnerListAdapter.notifyDataSetChanged();
                                ScoresFragment.listView.invalidateViews();
                                Toast success = Toast.makeText(getActivity(),
                                        "Added " + lastNameEditText.getText().toString(),
                                        Toast.LENGTH_SHORT);
                                success.setGravity(Gravity.CENTER, 0, 0);
                                success.show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                                Log.d(TAG, "Failed to write " + newShooter.toString()
                                        + " " + MainActivity.user.getUid());

                            }
                    });

                } else {
                    //input invalid, let user know
                    Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
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
        addButton = root.findViewById(R.id.add_button);

    }


    private boolean validateInput(){

        //TODO Implement simple checks for the text and input fields
        boolean isInputValid = true;
        if(isEmpty(lastNameEditText) || isEmpty(firstNameEditText)
            || isEmpty(companyEditText) || isEmpty(battalionEditText)
            || isEmpty(scoreEditText)){

            isInputValid = false;
        }

        return isInputValid;

    }


    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }


}