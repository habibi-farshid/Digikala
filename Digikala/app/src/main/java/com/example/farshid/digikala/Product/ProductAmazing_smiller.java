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
import android.widget.TextView;

import com.example.farshid.digikala.Activity_show;
import com.example.farshid.digikala.R;

public class ProductAmazing_smiller extends LinearLayout{

    public static TextView Price;
    public static TextView PrePrice;
    public static TextView Name;
    public static ImageView Image;
    public String Id;
    public String Cat;
    public static LinearLayout Linear_Mysell;


    public ProductAmazing_smiller(Context context) {
        super(context);
        init(context);
    }

    public ProductAmazing_smiller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public ProductAmazing_smiller(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_amazing_similar, this, true);
        Price = view.findViewById(R.id.amazing_price_Similar);
        PrePrice = view.findViewById(R.id.amazing_preprice_Similar);
        Name = view.findViewById(R.id.amazing_name_Similar);
        Image = view.findViewById(R.id.amazing_image_Similar);
        Linear_Mysell = findViewById(R.id.Linear_MySimilar);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/irsans.ttf");
        Price.setTypeface(tf);
        PrePrice.setTypeface(tf);
        Name.setTypeface(tf);
        Linear_Mysell.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, Activity_show.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("Id", Id);
                in.putExtra("Cat", Cat);
                context.startActivity(in);
            }
        });


    }

}
