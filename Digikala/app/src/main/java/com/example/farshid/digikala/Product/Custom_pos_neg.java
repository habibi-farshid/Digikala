package com.example.farshid.digikala.Product;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.farshid.digikala.R;

public class Custom_pos_neg extends LinearLayout {
    public static ImageView imageViewme;
    public static TextView textViewme;
    public Custom_pos_neg(Context context) {
        super(context);
        init(context);
    }

    public Custom_pos_neg(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public Custom_pos_neg(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }
    public void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cusom_pos_neg, this, true);
        textViewme = view.findViewById(R.id.Custom_pos_neg_Text);
        imageViewme = view.findViewById(R.id.Custom_pos_neg_ImageView);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/by.ttf");
        textViewme.setTypeface(tf);
    }
}
