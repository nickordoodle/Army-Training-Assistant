package com.example.armytrainingassistant.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.armytrainingassistant.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

/**
 * A Scores fragment containing a simple view.
 */
public class WeaponFragment extends Fragment implements OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String FRAGMENT_FILE_KEY_CHOOSER = "fragment_key_chooser";
    private String FILE_NAME;
    private static int fileID;

    public static WeaponFragment newInstance(int index, int fileID) {
        WeaponFragment fragment = new WeaponFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        WeaponFragment.fileID = fileID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //receive bundle for specifc file
        switch(WeaponFragment.fileID)
        {
            // case statements
            // machine gun range
            case 0 :
                FILE_NAME = "machine_gun_qual_standards.pdf";
                break;
            //rifle range
            case 1 :
                FILE_NAME = "rifle_qual_standards.pdf";
                break;
            //pistol range
            case 2 :
                FILE_NAME = "pistol_qual_standards.pdf";
                break;
            //grenade range
            case 3 :
                //TODO FIND M320 QUAL REGULATION AND CHANGE FILE NAME TO THIS
                FILE_NAME = "machine_gun_qual_standards.pdf";
                break;
        }

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.qual_fragment_layout, container, false);

        //set PDFview and read in pdf from asset using third party library
        PDFView pdfView = root.findViewById(R.id.pdfView);
        pdfView.fromAsset(FILE_NAME)
                .defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this.getContext()))
                .spacing(10) // in dp
                .onPageError(this)
                .load();

        return root;
    }

    @Override
    public void loadComplete(int nbPages) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void onPageError(int page, Throwable t) {

    }

}