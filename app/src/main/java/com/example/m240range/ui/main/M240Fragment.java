package com.example.m240range.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.m240range.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

/**
 * A Scores fragment containing a simple view.
 */
public class M240Fragment extends Fragment implements OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String FILE_NAME = "m240_qual.pdf";
    private PDFView pdfView;

    public static M240Fragment newInstance(int index) {
        M240Fragment fragment = new M240Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.m240_fragment_layout, container, false);

        //set PDFview and read in pdf from asset using third party library
        pdfView = root.findViewById(R.id.pdfView);
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