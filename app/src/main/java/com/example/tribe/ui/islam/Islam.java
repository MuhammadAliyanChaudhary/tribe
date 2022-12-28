package com.example.tribe.ui.islam;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.tribe.R;


public class Islam extends Fragment {

    ImageButton backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_islam, container, false);


        backButton=view.findViewById(R.id.imageButton);















        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity()!=null)
                    getActivity().onBackPressed();
            }
        });


        return view;
    }
}