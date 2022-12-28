package com.example.tribe.ui.kidshealth;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tribe.R;

public class KidsHealth extends Fragment {


    ImageButton backButton;
    CardView male, female;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kids_health, container, false);

        backButton=view.findViewById(R.id.imageButton);
        male = view.findViewById(R.id.male_btn);
        female = view.findViewById(R.id.female_btn);



        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_kidshealth_to_male);

            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_kidshealth_to_female);

            }
        });










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