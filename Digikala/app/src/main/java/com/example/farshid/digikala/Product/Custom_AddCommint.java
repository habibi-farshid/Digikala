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
import com.hedgehog.ratingbar.RatingBar;

public class Custom_AddCommint extends LinearLayout {
    public  TextView TextRating,textViewRating,Text3;
    public  RatingBar MyRating;
    public  int Count=0;
    public Custom_AddCommint(Context context) {
        super(context);
        init(context);
    }

    public Custom_AddCommint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public Custom_AddCommint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }
    public void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.costum_add_commint, this, true);
        TextRating = view.findViewById(R.id.AddCommint_TextRating);
//        TextNameProgress = view.findViewById(R.id.Commint_MyProgress);
        MyRating=view.findViewById(R.id.MyRating);
        textViewRating=view.findViewById(R.id.AddCommint_TextRating);
        Text3=view.findViewById(R.id.AddCommint_CountRating);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/by.ttf");
        TextRating.setTypeface(tf);
        textViewRating.setTypeface(tf);
        Text3.setTypeface(tf);
        MyRating.setOnRatingChangeListener(
                new RatingBar.OnRatingChangeListener() {
                    @Override
                    public void onRatingChange(float RatingCount) {
                        Count = (int) RatingCount;
                        String ra= String.valueOf(RatingCount);
//                        textViewRating.setText(ra.toString());
                    }
                }
        );


    }

}
