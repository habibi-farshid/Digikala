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

import com.example.farshid.digikala.Modle.Model_listView_FilterProduct;
import com.example.farshid.digikala.R;

import java.util.ArrayList;

public class Adapter_Listview_filterproduct extends ArrayAdapter {
    public int resourceid;
    public Activity context;
    public ArrayList<Model_listView_FilterProduct> object;

    public Adapter_Listview_filterproduct(Activity context, int resource, ArrayList objects) {
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
        TextView textView = view.findViewById(R.id.simple_list_view_filter_product);
        Model_listView_FilterProduct model_listView_filterProduct = object.get(position);
        textView.setText(model_listView_filterProduct.title);
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/irsans.ttf");
        textView.setTypeface(tf);

        return view;

    }
}
