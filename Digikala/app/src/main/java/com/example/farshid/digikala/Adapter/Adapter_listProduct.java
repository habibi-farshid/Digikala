package com.example.farshid.digikala.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.farshid.digikala.Activity_ShowSelectedlistProduct;
import com.example.farshid.digikala.Modle.Model_listProduct;
import com.example.farshid.digikala.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_listProduct extends RecyclerView.Adapter<Adapter_listProduct.ViewHolder> {
    private Context mContext;
    private ArrayList<Model_listProduct> AModel;



    public Adapter_listProduct(Context context, ArrayList<Model_listProduct> List) {
        mContext = context;
        AModel = List;
    }

    @Override
    public Adapter_listProduct.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.sample_listproduct, parent, false);
        Adapter_listProduct.ViewHolder viewHolder = new Adapter_listProduct.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Model_listProduct model = AModel.get(position);
//        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "font/irsans.ttf");
//        holder.TextNote.setTypeface(tf);
        final String  Cat = model.getCat();
        holder.Text.setText(model.getName());
        Picasso.with(mContext)
                .load(model.getImage()).into(holder.image);
        holder.ListProduct_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(mContext, Activity_ShowSelectedlistProduct.class);
                in.putExtra("Cat",Cat);
                mContext.startActivity(in);

            }
        });
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "Font/by.ttf");
        holder.Text.setTypeface(tf);

    }

    @Override
    public int getItemCount() {
        return AModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Text;
        ImageView image;
        LinearLayout ListProduct_LinearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            Text = itemView.findViewById(R.id.samplelistproduct_text);
            image = itemView.findViewById(R.id.samplelistproduct_image);
            ListProduct_LinearLayout = itemView.findViewById(R.id.ListProduct_LinearLayout);

        }
    }

}
