package com.example.farshid.digikala;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.farshid.digikala.Product.Custom_show_select_list_product;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity_ShowSelectedlistProduct extends AppCompatActivity implements View.OnClickListener {

    String Cat = "", Id = "", Color = "", Cost = "", Type = "", Type2 = "",search="";
    TextView toolbar_text, ActivityShow_Select_List_Product_text_Arrange1, ActivityShow_Select_List_Product_text_Arrange2, ActivityShow_Select_List_Product_text_filter1, ActivityShow_Select_List_Product_text_filter2;
    ImageView toolbar_image,toolbar_show_select_list_product_search;
    LinearLayout linearLayout;
    LinearLayout.LayoutParams layoutParams;
    Custom_show_select_list_product custom_show_select_list_product;

    LinearLayout Activity_Show_Select_list_Product_LinearFilter, Activity_Show_Select_list_Product_LinearArrange;
    boolean flag = false,flag2=false;
    JSONObject obj;
    JSONObject obj2;
    JSONObject obj3;
    JSONObject obj4;
    JSONObject obj5;
    JSONArray jsonarray;
    JSONArray jsonarray2;
    JSONArray jsonarray3;
    JSONArray jsonarray4;
    JSONArray jsonarray5;
    String Server_Ur22 = "http://learnshipp.ir/farshid/diji/GiveListProduct.php";
    String Server_Ur33 = "http://learnshipp.ir/farshid/diji/FilterVar2.php";
    String Server_Ur44 = "http://learnshipp.ir/farshid/diji/FilterVar2.php";
    String Server_U55 = "http://learnshipp.ir/farshid/diji/search.php";


    public void Custom_list(String name, String cost, String image, String id) {
        custom_show_select_list_product = new Custom_show_select_list_product(this);
        custom_show_select_list_product.TextTitleBold.setText(name);
        custom_show_select_list_product.textTitleNormal.setText(name);
        custom_show_select_list_product.TextCost.setText(persian.PerisanNumber(cost));
        custom_show_select_list_product.Id = id;
        custom_show_select_list_product.Cat = Cat;
        Picasso.with(this).load(image).placeholder(R.drawable.image_default).into(custom_show_select_list_product.Image);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(custom_show_select_list_product);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__show_selectedlist_product);
        Bundle bundle = getIntent().getExtras();
        Cat = bundle.getString("Cat", "no");
        Color = bundle.getString("Color", "no");
        Cost = bundle.getString("Cost", "no");
        flag = bundle.getBoolean("flag", false);
        flag2 = bundle.getBoolean("flag2", false);
        search = bundle.getString("search", "");
        InitializeView();
        SetFont();
        if (flag) {
            if (!Color.equals("no")) {
                if (isInternetOn()) {
                    InitFilter();

                } else {
                    onload();
                }

            }
            if (!Cost.equals("no")) {
                if (isInternetOn()) {
                    InitFilter2();

                } else {
                    onload();
                }
            }
        }else if (flag2){

            if (isInternetOn()) {
                InitFilter4();
                toolbar_text.setText(search+"");

            } else {
                onload();
            }

        }


        else {
            if (isInternetOn()) {
                InitializeListProduct();
            } else {
                onload();
            }

        }
        toolbar_image.setOnClickListener(this);
        Activity_Show_Select_list_Product_LinearFilter.setOnClickListener(this);
        Activity_Show_Select_list_Product_LinearArrange.setOnClickListener(this);
        toolbar_show_select_list_product_search.setOnClickListener(this);


    }




    //  در صورد نبود اینترنت باز شدن اسنک بار
    private void onload() {
//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.CoorSpalish);
//        Snackbar snackbar = Snackbar.make(coordinatorLayout, "اینترنت در دسترس نیست!",
//                Snackbar.LENGTH_INDEFINITE).setActionTextColor(Color.RED).setAction("بررسی", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Check_Internet();
//            }
//        });
//        View view = snackbar.getView();
//        TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.YELLOW);
//        snackbar.show();

        new FancyGifDialog.Builder(this)
                .setTitle("مشکل در اتصال اینترنت!")
                .setMessage("لطفا دسترسی اینترنت خود را بررسی کنید.")
                .setNegativeBtnText("خروج")
                .setPositiveBtnBackground("#FF4081")
                .setPositiveBtnText("بله")
                .setNegativeBtnBackground("#FFA9A7A8")
                .setGifResource(R.drawable.no_disconnect)   //Pass your Gif here
                .isCancellable(false)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .OnNegativeClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        finish();
                        System.exit(0);
                    }

                })
                .build();

    }

    //چک کردن اینترنت
    public boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {


            return false;
        }
        return false;
    }


    private void InitFilter4() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_U55, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int count = 0;
                try {
                    obj5= new JSONObject(response);

                    jsonarray5 = obj5.getJSONArray("resp");
                    while (count < jsonarray5.length()) {
                        JSONObject jsonObject = jsonarray5.getJSONObject(count);
                        String Name = jsonObject.getString("Name");
                        String Image = jsonObject.getString("Image");
                        String Cost = jsonObject.getString("Price");
                        Cat= jsonObject.getString("Cat");
                        Id = jsonObject.getString("Id");
                        Custom_list(Name, Cost, Image, Id);
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                onload();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cat", Cat);
                params.put("search",search);
                return params;
            }
        };
        MySingleton.getInstance(Activity_ShowSelectedlistProduct.this).addtoRequestQueue(stringrequest);
    }

    private void InitFilter2() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur33, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int count = 0;
                try {
                    obj2 = new JSONObject(response);
                    jsonarray2 = obj2.getJSONArray("resp");
                    while (count < jsonarray2.length()) {
                        JSONObject jsonObject = jsonarray2.getJSONObject(count);
                        String Name = jsonObject.getString("Name");
                        String Image = jsonObject.getString("Image");
                        String Cost = jsonObject.getString("Price");
                        Id = jsonObject.getString("Id");
                        Custom_list(Name, Cost, Image, Id);
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                onload();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cat", Cat);
                params.put("Cost", Cost);
                params.put("Color", Color);
                params.put("Type", "");

                return params;
            }
        };
        MySingleton.getInstance(Activity_ShowSelectedlistProduct.this).addtoRequestQueue(stringrequest);

    }

    private void InitFilter3() {

        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur44, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int count = 0;
                try {
                    obj4 = new JSONObject(response);
                    jsonarray4 = obj4.getJSONArray("resp");
                    while (count < jsonarray4.length()) {
                        JSONObject jsonObject = jsonarray4.getJSONObject(count);
                        String Name = jsonObject.getString("Name");
                        String Image = jsonObject.getString("Image");
                        String Cost = jsonObject.getString("Price");
                        Id = jsonObject.getString("Id");
                        Custom_list(Name, Cost, Image, Id);
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                onload();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cat", Cat);
                params.put("Type", Type2);
                params.put("Cost", "");
                params.put("Color", "");
                return params;
            }
        };
        MySingleton.getInstance(Activity_ShowSelectedlistProduct.this).addtoRequestQueue(stringrequest);

    }

    private void InitFilter() {

        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur33, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int count = 0;
                try {
                    obj3 = new JSONObject(response);
                    jsonarray3 = obj3.getJSONArray("resp");
                    while (count < jsonarray3.length()) {
                        JSONObject jsonObject = jsonarray3.getJSONObject(count);
                        String Name = jsonObject.getString("Name");
                        String Image = jsonObject.getString("Image");
                        String Cost = jsonObject.getString("Price");
                        Id = jsonObject.getString("Id");
                        Custom_list(Name, Cost, Image, Id);
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                onload();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cat", Cat);
                params.put("Cost", Cost);
                params.put("Type", "");
                params.put("Color", Color);
                return params;
            }
        };
        MySingleton.getInstance(Activity_ShowSelectedlistProduct.this).addtoRequestQueue(stringrequest);

    }

    private void InitializeListProduct() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur22, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int count = 0;
                try {
                    obj = new JSONObject(response);
                    jsonarray = obj.getJSONArray("resp");
                    while (count < jsonarray.length()) {
                        JSONObject jsonObject = jsonarray.getJSONObject(count);
                        String Name = jsonObject.getString("Name");
                        String Image = jsonObject.getString("Image");
                        String Cost = jsonObject.getString("Price");
                        Id = jsonObject.getString("Id");

                        Custom_list(Name, Cost, Image, Id);
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                onload();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cat", Cat);
                return params;
            }
        };
        MySingleton.getInstance(Activity_ShowSelectedlistProduct.this).addtoRequestQueue(stringrequest);


    }

    private void SetFont() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        toolbar_text.setTypeface(tf);
        ActivityShow_Select_List_Product_text_Arrange1.setTypeface(tf);
        ActivityShow_Select_List_Product_text_Arrange2.setTypeface(tf);
        ActivityShow_Select_List_Product_text_filter1.setTypeface(tf);
        ActivityShow_Select_List_Product_text_filter2.setTypeface(tf);

    }

    private void InitializeView() {

//        TextView
        toolbar_text = findViewById(R.id.toolbar_show_select_list_product_Text);
        ActivityShow_Select_List_Product_text_Arrange1 = findViewById(R.id.ActivityShow_Select_List_Product_text_Arrange1);
        ActivityShow_Select_List_Product_text_Arrange2 = findViewById(R.id.ActivityShow_Select_List_Product_text_Arrange2);
        ActivityShow_Select_List_Product_text_filter1 = findViewById(R.id.ActivityShow_Select_List_Product_text_filter1);
        ActivityShow_Select_List_Product_text_filter2 = findViewById(R.id.ActivityShow_Select_List_Product_text_filter2);

//        ImageView
        toolbar_image = findViewById(R.id.toolbar_show_select_list_product_Exit);
        toolbar_show_select_list_product_search=findViewById(R.id.toolbar_show_select_list_product_search);

//        LinearLayout
        linearLayout = findViewById(R.id.Activity_Show_select_list_product_LinearLayout);
        Activity_Show_Select_list_Product_LinearFilter = findViewById(R.id.Activity_Show_Select_list_Product_LinearFilter);
        Activity_Show_Select_list_Product_LinearArrange = findViewById(R.id.Activity_Show_Select_list_Product_LinearArrange);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.toolbar_show_select_list_product_Exit: {
                finish();
                break;
            }
            case R.id.Activity_Show_Select_list_Product_LinearFilter: {
                Intent in = new Intent(Activity_ShowSelectedlistProduct.this, ActivityFilter.class);
                in.putExtra("Cat", Cat);
                startActivity(in);
                System.exit(0);
                finish();
                break;
            }
            case R.id.toolbar_show_select_list_product_search: {
                Intent in = new Intent(Activity_ShowSelectedlistProduct.this, Activity_search.class);
                startActivity(in);
                System.exit(0);
                finish();
                break;
            }
            case R.id.Activity_Show_Select_list_Product_LinearArrange: {
                final Dialog dialog = new Dialog(Activity_ShowSelectedlistProduct.this);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setTitle("مرتب کردن براساس");

//                FontUtils.typeface(typeface, "The title")
//                dialog.set
                dialog.setContentView(R.layout.custom_dilog);
                TextView tex1 = dialog.findViewById(R.id.textView1);
                TextView tex2 = dialog.findViewById(R.id.textView2);
                TextView tex3 = dialog.findViewById(R.id.textView3);
                TextView tex4 = dialog.findViewById(R.id.textView4);
                final RadioButton radioButton1 = dialog.findViewById(R.id.checkBox1);
                final RadioButton radioButton2 = dialog.findViewById(R.id.checkBox2);
                final RadioButton radioButton3 = dialog.findViewById(R.id.checkBox3);
                final RadioButton radioButton4 = dialog.findViewById(R.id.checkBox4);
                radioButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Type2 = "PF";
                        radioButton2.setEnabled(false);
                        radioButton3.setEnabled(false);
                        radioButton4.setEnabled(false);
                        linearLayout.removeAllViews();
                        InitFilter3();
                        dialog.dismiss();


                    }
                });
                radioButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Type2 = "ZBK";
                        radioButton1.setEnabled(false);
                        radioButton3.setEnabled(false);
                        radioButton4.setEnabled(false);
                        linearLayout.removeAllViews();
                        InitFilter3();
                        dialog.dismiss();

                    }
                });
                radioButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Type2 = "KBZ";
                        radioButton2.setEnabled(false);
                        radioButton1.setEnabled(false);
                        radioButton4.setEnabled(false);
                        linearLayout.removeAllViews();
                        InitFilter3();
                        dialog.dismiss();

                    }
                });
                radioButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Type2 = "Jad";
                        radioButton2.setEnabled(false);
                        radioButton3.setEnabled(false);
                        radioButton1.setEnabled(false);
                        linearLayout.removeAllViews();
                        InitFilter3();
                        dialog.dismiss();

                    }
                });
                Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
                tex1.setTypeface(tf);
                tex2.setTypeface(tf);
                tex3.setTypeface(tf);
                tex4.setTypeface(tf);

                dialog.show();
//                Intent in = new Intent(Activity_ShowSelectedlistProduct.this, Activity_Arrange.class);
//                startActivity(in);
                break;
            }
        }

    }
}
