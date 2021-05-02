package com.example.farshid.digikala.Product;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.farshid.digikala.R;

public class Custom_RateCommint extends LinearLayout {
    public  TextView TextProgress;
    public  ProgressBar progressBar;

    public Custom_RateCommint(Context context) {
        super(context);
        init(context);
    }

    public Custom_RateCommint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public Custom_RateCommint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }
    public void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rate_commint, this, true);
        TextProgress = view.findViewById(R.id.TextRateCommint);
        progressBar = view.findViewById(R.id.rateCommint_MyProgress);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/by.ttf");
        TextProgress.setTypeface(tf);
    }

}
