package com.example.tribe.ui.kidshealth.weight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tribe.R;


public class MaleTeenAgeFragment extends Fragment {

    EditText ageEditText;
    TextView description;
    Button buttonResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_male_teen_age, container, false);

        description = view.findViewById(R.id.detail_text_teen);
        ageEditText = view.findViewById(R.id.age_teen);
        buttonResult = view.findViewById(R.id.button_result_teen);


        ModelWeight[] modelWeights = new ModelWeight[12];

        modelWeights[0] = new ModelWeight("0","19.4","3.31");
        modelWeights[1] = new ModelWeight("1","21.2","4.35");
        modelWeights[2] = new ModelWeight("2","22.1","5.3");
        modelWeights[3] = new ModelWeight("3","23.6","6.03");
        modelWeights[4] = new ModelWeight("4","24.5","6.62");
        modelWeights[5] = new ModelWeight("5","25.3","7.17");
        modelWeights[6] = new ModelWeight("6","25.9","7.53");
        modelWeights[7] = new ModelWeight("7","26.5","7.9");
        modelWeights[8] = new ModelWeight("8","27.1","8.21");
        modelWeights[9] = new ModelWeight("9","27.6","8.53");
        modelWeights[10] = new ModelWeight("10","28.2","8.8");
        modelWeights[11] = new ModelWeight("11","28.7","9.03");


        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!ageEditText.getText().toString().isEmpty()){

                    int age = Integer.parseInt(ageEditText.getText().toString());

                    if(age>=13 && age<=20){

                        modelWeights[age].setTextBabies(description);

                    }
                    else{
                        Toast.makeText(getActivity(), "Please enter age between 13 to 20 years", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getActivity(), "Please enter the age of your child", Toast.LENGTH_SHORT).show();
                }


            }
        });





        return view;
    }
}