package com.example.farshid.digikala.Adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farshid.digikala.Modle.BuyMenu_Model;
import com.example.farshid.digikala.R;

import java.util.ArrayList;

public class BuyMenu_Adapter extends ArrayAdapter {

    public int resourceid;
    public Activity context;
    public ArrayList<BuyMenu_Model> object;

    public BuyMenu_Adapter(Activity context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.resourceid = resource;
        this.context = context;
        this.object = objects;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        view = this.context.getLayoutInflater().inflate(this.resourceid, null);
        ImageView img = view.findViewById(R.id.simple_buy_image);
        TextView textView = view.findViewById(R.id.simple_buy_text);
        BuyMenu_Model buyMenu_modle = object.get(position);
        textView.setText(buyMenu_modle.Text);
        img.setImageResource(buyMenu_modle.img);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/irsans.ttf");
        textView.setTypeface(tf);

        return view;

    }
}
