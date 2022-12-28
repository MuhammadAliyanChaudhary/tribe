package com.example.tribe.ui.kidshealth.weight;

import android.view.View;
import android.widget.TextView;

public class ModelWeight {

    String age;
    String height, weight;

    public ModelWeight(String age, String height, String weight) {
        this.age = age;
        this.height = height;
        this.weight = weight;
    }


    public void setTextBabies(TextView v){

        v.setText("Healthy weight for your child is "+weight + "kg " + " and Healthy height for your child is "+height + " inches");

    }
}
