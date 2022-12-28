package com.example.tribe;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomDialog extends Dialog {
    public CustomDialog(@NonNull Context context) {
        super(context);


        WindowManager.LayoutParams wlmp = getWindow().getAttributes();


        //Toast.makeText(getContext(), "Lottie Animation", Toast.LENGTH_SHORT).show();


        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null);
        setContentView(view);

    }


}
