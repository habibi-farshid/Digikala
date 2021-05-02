package com.example.farshid.digikala.Product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.farshid.digikala.Activity_show;
import com.example.farshid.digikala.R;


public class Custom_interest_Show_list_Product extends LinearLayout {
    public TextView TextTitleBold, textTitleNormal, TextCost, custom_show_select_list_product_Text_Cost0;
    public ImageView Image;
    CardView CardView_listProduct;
    public String Cat = "", Id = "";

    public Custom_interest_Show_list_Product(Context context) {
        super(context);
        init(context);
    }

    public Custom_interest_Show_list_Product(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public Custom_interest_Show_list_Product(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_show_interest_list_product, this, true);
        TextTitleBold = view.findViewById(R.id.interest_custom_show_select_list_product_Text_Title_Bold);
        textTitleNormal = view.findViewById(R.id.interest_custom_show_select_list_product_Text_Title_Normal);
        TextCost = view.findViewById(R.id.interest_custom_show_select_list_product_Text_Cost);
        Image = view.findViewById(R.id.interest_custom_show_select_list_product_ImageProduct);
        custom_show_select_list_product_Text_Cost0 = view.findViewById(R.id.interest_custom_show_select_list_product_Text_Cost0);
        CardView_listProduct = view.findViewById(R.id.interest_CardView_listProduct);
        CardView_listProduct.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, Activity_show.class);
                in.putExtra("Id", Id);
                in.putExtra("Cat", Cat);
                context.startActivity(in);

            }
        });
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/by.ttf");
        TextTitleBold.setTypeface(tf);
        textTitleNormal.setTypeface(tf);
        TextCost.setTypeface(tf);
        custom_show_select_list_product_Text_Cost0.setTypeface(tf);


    }

}
