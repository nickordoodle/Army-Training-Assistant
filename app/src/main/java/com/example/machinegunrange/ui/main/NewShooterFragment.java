package com.example.machinegunrange.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.machinegunrange.R;

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
    private Button submitButton;



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

        submitButton = (Button) root.findViewById(R.id.submit_shooter_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validate all inputs and then submit and populate array
            }
        });

        return root;
    }


}