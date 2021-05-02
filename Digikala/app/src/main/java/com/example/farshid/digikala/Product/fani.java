package com.example.farshid.digikala.Product;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.farshid.digikala.R;

public class fani extends LinearLayout {


    public static TextView Fani_Name1,Fani_Name2;

    public fani(Context context) {
        super(context);
        init(context);
    }

    public fani(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public fani(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fani, this, true);
        Fani_Name1 = view.findViewById(R.id.Fani_Name1);
        Fani_Name2 = view.findViewById(R.id.Fani_Name2);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/by.ttf");
        Fani_Name1.setTypeface(tf);
        Fani_Name2.setTypeface(tf);


    }



}
