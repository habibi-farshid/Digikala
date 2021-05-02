package com.example.farshid.digikala.Product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farshid.digikala.Activity_show;
import com.example.farshid.digikala.R;

public class ProductCat extends LinearLayout {

    public  TextView textViewcat;
    public float pro;
    public   ProgressBar progressBar;


    public ProductCat(Context context) {
        super(context);
        init(context);
    }

    public ProductCat(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public ProductCat(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cat, this, true);
        textViewcat = view.findViewById(R.id.TextCat);
        progressBar = view.findViewById(R.id.MyProgress);
//        progressBar.setProgress((int) pro);


        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/irsans.ttf");
        textViewcat.setTypeface(tf);


    }

}
