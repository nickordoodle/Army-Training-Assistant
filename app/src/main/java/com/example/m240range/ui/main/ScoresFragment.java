package com.example.m240range.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.m240range.CustomExpandableListAdapter;
import com.example.m240range.MachineGunner;
import com.example.m240range.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Scores fragment containing a simple view.
 */
public class ScoresFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    ExpandableListView listView;
    ExpandableListAdapter listViewAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    private PageViewModel pageViewModel;

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
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.scores_fragment_layout, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        ArrayList<MachineGunner> adapterDataList = new ArrayList<>();
        adapterDataList.add(gunnerOne);
        adapterDataList.add(gunnerTwo);

        listView = (ExpandableListView) view.findViewById(R.id.scores_expandable_listview);
        ExpandableListAdapter listAdapter = new CustomExpandableListAdapter(getContext(),
                adapterDataList);
        listView.setAdapter(listAdapter);
        listView.setGroupIndicator(null);

    }

}