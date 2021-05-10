package com.example.farshid.digikala;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.farshid.digikala.Adapter.BuyMenu_Adapter;
import com.example.farshid.digikala.Adapter.BuyMenu_Adapter2;
import com.example.farshid.digikala.Adapter.BuyMenu_Adapter3;
import com.example.farshid.digikala.Adapter.BuyMenu_Adapter4;
import com.example.farshid.digikala.Admin.insert_post;
import com.example.farshid.digikala.Modle.BuyMenu_Model;
import com.example.farshid.digikala.Modle.BuyMenu_Model2;
import com.example.farshid.digikala.Modle.BuyMenu_Model3;
import com.example.farshid.digikala.Modle.BuyMenu_Model4;
import com.example.farshid.digikala.Product.ProductAmazing;
import com.example.farshid.digikala.Product.ProductAmazing_Sell;
import com.example.farshid.digikala.Product.ProductAmazing_Time;
import com.example.farshid.digikala.Product.ProductAmazing_fagat;
import com.example.farshid.digikala.Product.ProductCat;
import com.example.farshid.digikala.Sign.Sign;
import com.example.farshid.digikala.Sign.SignUp;
import com.github.hujiaweibujidao.wava.Techniques;
import com.github.hujiaweibujidao.wava.YoYo;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    LinearLayout LinnerShowAllProduct;
    SliderLayout sliderShow;
    ArrayList<String> UrlPic;
    ArrayList<String> names;
    LinearLayout linearLayout2, Linnear_new, Linnear_sell, LInnear_fagat_dar_diji, Linnear_Cat;
    ListView MyList, MyList2, MyList3, MyList4;
    ImageView Myimage;

    TextView TextL1, TextL2, TextL3, TextL4, TextL5, TextL6, TextL7, TextL8;
    ProductAmazing productAmazing;
    ProductAmazing_Time productAmazing_time;
    ProductAmazing_Sell productAmazing_sell;
    ProductAmazing_fagat productAmazing_fagat;
    LinearLayout.LayoutParams layoutParams, layoutParams2, layoutParams3, layoutParamsCat;
    DrawerLayout myDrawer;
    ProductCat cat2;
    String[] menus = {"ثبت نام", "ورود"};
    ArrayList<BuyMenu_Model> items;
    ArrayList<BuyMenu_Model2> items2;
    ArrayList<BuyMenu_Model3> items3;
    ArrayList<BuyMenu_Model4> items4;
    LinearLayout Linnear_Sign, Linnear_Store, Linnear_Exit;
    SharedPreferences sharedPreferences, sharedPreferencesSign;
    TextView IdSinIn, ID_Hour, ID_Min, ID_Sec;
    int sec = 59;
    int min = 13;
    int hour = 10;
    public static Handler handler;
    JSONObject obj;
    JSONArray jsonarray;
    ImageView Banner1, Banner2, Banner3, Banner4, Banner5, Banner6, MainActivity_toolbar_search;
    JSONObject obj2;
    JSONArray jsonarray2;
    JSONObject obj3;
    JSONArray jsonarray3;
    JSONObject obj4;
    JSONArray jsonarray4;

    String Server_Ur3 = "http://learnshipp.ir/farshid/diji/Recive_Shegift.php";
    String Server_Ur31 = "http://learnshipp.ir/farshid/diji/newProduct.php";
    String Server_Ur32 = "http://learnshipp.ir/farshid/diji/sellProduct.php";
    String Server_Ur33 = "http://learnshipp.ir/farshid/diji/banner.php";

    CoordinatorLayout coordinatorLayout;
    boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false;


    public void CreateProductamazing(String Id, String name, String price, String preprice, String image, String Cat1) {
        productAmazing = new ProductAmazing(this);
        productAmazing.Id = Id;
        productAmazing.Cat = Cat1;
        String y1 = String.valueOf(persian.PerisanNumber(price)) + "هزار تومان";
        String y2 = String.valueOf(persian.PerisanNumber(preprice)) + "هزار تومان";
        ProductAmazing.Name.setText(name);
        ProductAmazing.PrePrice.setText(y2);
        ProductAmazing.Price.setText(y1);
        Glide.with(MainActivity.this).load(image).placeholder(R.drawable.image_default_product).error(R.drawable.no_product_image).crossFade().into(ProductAmazing.Image);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout2.addView(productAmazing);


    }

    public void CreateProductamazing_new(String Id, String name, String price, String preprice, String image, String Cat1) {
        productAmazing_time = new ProductAmazing_Time(this);
        String y1 = String.valueOf(persian.PerisanNumber(price)) + "هزار تومان";
        String y2 = String.valueOf(persian.PerisanNumber(preprice)) + "هزار تومان";
        ProductAmazing_Time.Name.setText(name);
        productAmazing_time.Id = Id;
        productAmazing_time.Cat = Cat1;
        ProductAmazing_Time.PrePrice.setText(y2);
        ProductAmazing_Time.Price.setText(y1);
        Glide.with(MainActivity.this).load(image).placeholder(R.drawable.image_default_product).error(R.drawable.no_product_image).crossFade().into(ProductAmazing_Time.Image);
        layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Linnear_new.addView(productAmazing_time);


    }

    public void CreateProductamazing_sell(String Id, String name, String price, String preprice, String image, String Cat1) {
        productAmazing_sell = new ProductAmazing_Sell(this);
        String y1 = String.valueOf(persian.PerisanNumber(price)) + "هزار تومان";
        String y2 = String.valueOf(persian.PerisanNumber(preprice)) + "هزار تومان";
        ProductAmazing_Sell.Name.setText(name);
        productAmazing_sell.Id = Id;
        productAmazing_sell.Cat = Cat1;
        ProductAmazing_Sell.PrePrice.setText(y2);
        ProductAmazing_Sell.Price.setText(y1);
        Glide.with(MainActivity.this).load(image).placeholder(R.drawable.image_default_product).error(R.drawable.no_product_image).crossFade().into(ProductAmazing_Sell.Image);
        layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Linnear_sell.addView(productAmazing_sell);


    }

    public void CreateProductamazing_fagat(String Id, String name, String price, String preprice, String image, String Cat1) {
        productAmazing_fagat = new ProductAmazing_fagat(this);
        String y1 = String.valueOf(persian.PerisanNumber(price)) + "هزار تومان";
        String y2 = String.valueOf(persian.PerisanNumber(preprice)) + "هزار تومان";
        ProductAmazing_fagat.Name.setText(name);
        ProductAmazing_fagat.PrePrice.setText(y1);
        ProductAmazing_fagat.Price.setText(y1);
        productAmazing_fagat.Id = Id;
        productAmazing_fagat.Cat = Cat1;
        Glide.with(MainActivity.this).load(image).placeholder(R.drawable.image_default_product).error(R.drawable.no_product_image).crossFade().into(ProductAmazing_fagat.Image);

        layoutParams3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LInnear_fagat_dar_diji.addView(productAmazing_fagat);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        مقدار دهی View ها
        InitilizeView();
        InitilizeArrayList_UrlImage();
        Initialize_ListView();
        SupportToolBar();
//For Navigation
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = sharedPreferences.getString("email", "no");
        if (email.toString().equals("no")) {
            IdSinIn.setText("ورود و ثبت نام");
            //        For Sign
            sharedPreferencesSign = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = sharedPreferencesSign.edit();
            editor.putString("Sign", "SignOut");
            editor.commit();

        } else {
            IdSinIn.setText(email.toString());
        }
//        For Initialize font
        set_font();
        Check_Internet();

        MyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    myDrawer.closeDrawers();
                }
                if (i == 1) {
                    Intent in = new Intent(MainActivity.this, Activity_ListProduct.class);
                    startActivity(in);
                }

            }
        });

        MyList3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(MainActivity.this, interest.class);
                startActivity(in);

            }
        });
        MyList4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(MainActivity.this,insert_post.class);
                startActivity(in);

            }
        });
        LinnerShowAllProduct.setOnClickListener(this);
        MainActivity_toolbar_search.setOnClickListener(this);
        Linnear_Sign.setOnClickListener(this);
        Linnear_Exit.setOnClickListener(this);
        Myimage.setOnClickListener(this);

    }

    private void Check_Internet() {
        if (isInternetOn()) {
            for (int i = 0; i < UrlPic.size(); i++) {
                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView
                        .image(UrlPic.get(i)).setScaleType(BaseSliderView.ScaleType.Fit).empty(R.drawable.sliderimage)
                        .setOnSliderClickListener(this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra", names.get(i));
                sliderShow.addSlider(textSliderView);
            }
            SetTimer();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!flag1) {
                        GiveData();

                    }

                }
            }, 500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!flag2) {
                        GiveData_new();
                    }

                }
            }, 500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!flag3) {
                        GiveData_sell();
                    }

                }
            }, 500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!flag4) {
                        GiveData_fagat();

                    }

                }
            }, 500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!flag5) {
                        SetBanner();
                    }

                }
            }, 500);


        } else {
            onload();
        }

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
                        Check_Internet();

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

    private void set_font() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/dima.ttf");
        TextL1.setTypeface(tf);
        TextL2.setTypeface(tf);
        TextL3.setTypeface(tf);
        TextL4.setTypeface(tf);
        TextL5.setTypeface(tf);
        TextL6.setTypeface(tf);
        TextL7.setTypeface(tf);
        TextL8.setTypeface(tf);


        Typeface tf2 = Typeface.createFromAsset(getAssets(), "Font/irsans.ttf");

        ID_Hour.setTypeface(tf2);
        ID_Min.setTypeface(tf2);
        ID_Sec.setTypeface(tf2);
        IdSinIn.setTypeface(tf2);
        Banner1 = findViewById(R.id.bnner1);
        Banner2 = findViewById(R.id.bnner2);
        Banner3 = findViewById(R.id.bnner3);
        Banner4 = findViewById(R.id.bnner4);
        Banner5 = findViewById(R.id.bnner5);
        Banner6 = findViewById(R.id.bnner6);

    }

    private void SupportToolBar() {
        Toolbar custom_toolbar = (Toolbar) findViewById(R.id.MainActivity_toolbar);
        setSupportActionBar(custom_toolbar);
    }

    private void SetBanner() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur33, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {
                    obj4 = new JSONObject(response);
                    jsonarray4 = obj4.getJSONArray("resp");
                    while (count < jsonarray4.length()) {
                        flag5 = true;
                        JSONObject jsonObject = jsonarray4.getJSONObject(count);
                        String Name = jsonObject.getString("name");
                        switch (count) {
                            case 0: {
                                Glide.with(MainActivity.this).load(Name).placeholder(R.drawable.image_default_product).fitCenter().error(R.drawable.no_product_image).crossFade().into(Banner1);
                                break;
                            }
                            case 1: {

                                Glide.with(MainActivity.this).load(Name).placeholder(R.drawable.image_default_product).fitCenter().error(R.drawable.no_product_image).crossFade().into(Banner2);
                                break;
                            }
                            case 2: {
                                Glide.with(MainActivity.this).load(Name).placeholder(R.drawable.image_default_product).fitCenter().error(R.drawable.no_product_image).crossFade().into(Banner3);
                                break;
                            }
                            case 3: {
                                Glide.with(MainActivity.this).load(Name).placeholder(R.drawable.image_default_product).fitCenter().error(R.drawable.no_product_image).crossFade().into(Banner4);

                                break;
                            }
                            case 4: {
                                Glide.with(MainActivity.this).load(Name).placeholder(R.drawable.image_default_product).centerCrop().error(R.drawable.no_product_image).crossFade().into(Banner5);

                                break;
                            }
                            case 5: {
                                Glide.with(MainActivity.this).load(Name).placeholder(R.drawable.image_default_product).centerCrop().error(R.drawable.no_product_image).crossFade().into(Banner6);

                                break;
                            }
                        }
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
                return params;
            }
        };
        MySingleton.getInstance(MainActivity.this).addtoRequestQueue(stringrequest);

    }

    private void GiveData_fagat() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur32, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {

                    obj3 = new JSONObject(response);
                    jsonarray3 = obj3.getJSONArray("resp");
                    while (count < jsonarray3.length()) {
                        flag4 = true;
                        JSONObject jsonObject = jsonarray3.getJSONObject(count);
                        String Name = jsonObject.getString("Name");
                        String Price = jsonObject.getString("Price");
                        String PrePrice = jsonObject.getString("PrePrice");
                        String Image = jsonObject.getString("Image");
                        String Id = jsonObject.getString("Id");
                        String Cat = jsonObject.getString("Cat");
                        CreateProductamazing_fagat(Id, Name, Price, PrePrice, Image, Cat);
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
                params.put("Cat", "3");
                return params;
            }
        };
        MySingleton.getInstance(MainActivity.this).addtoRequestQueue(stringrequest);


    }

    private void GiveData_sell() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur32, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {
                    obj3 = new JSONObject(response);
                    jsonarray3 = obj3.getJSONArray("resp");
                    while (count < jsonarray3.length()) {
                        flag3 = true;
                        JSONObject jsonObject = jsonarray3.getJSONObject(count);
                        String Name = jsonObject.getString("Name");
                        String Price = jsonObject.getString("Price");
                        String PrePrice = jsonObject.getString("PrePrice");
                        String Image = jsonObject.getString("Image");
                        String Id = jsonObject.getString("Id");
                        String Cat = jsonObject.getString("Cat");
                        CreateProductamazing_sell(Id, Name, Price, PrePrice, Image, Cat);
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
                params.put("Cat", "3");
                return params;
            }
        };
        MySingleton.getInstance(MainActivity.this).addtoRequestQueue(stringrequest);


    }

    private void GiveData_new() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur31, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {

                    obj2 = new JSONObject(response);
                    jsonarray2 = obj2.getJSONArray("resp");
                    while (count < jsonarray2.length()) {
                        flag2 = true;
                        JSONObject jsonObject = jsonarray2.getJSONObject(count);
                        String Name = jsonObject.getString("Name");
                        String Price = jsonObject.getString("Price");
                        String PrePrice = jsonObject.getString("PrePrice");
                        String Image = jsonObject.getString("Image");
                        String Id = jsonObject.getString("Id");
                        String Cat = jsonObject.getString("Cat");
                        CreateProductamazing_new(Id, Name, Price, PrePrice, Image, Cat);
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
                params.put("Cat", "3");
                return params;
            }
        };
        MySingleton.getInstance(MainActivity.this).addtoRequestQueue(stringrequest);

    }

    private void GiveData() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {

                    obj = new JSONObject(response);
                    jsonarray = obj.getJSONArray("resp");
                    while (count < jsonarray.length()) {
                        flag1 = true;
                        JSONObject jsonObject = jsonarray.getJSONObject(count);
                        String Name = jsonObject.getString("Name");
                        String Id = jsonObject.getString("Id");
                        String Cat = jsonObject.getString("Cat");
                        String Price = jsonObject.getString("Price");
                        String PrePrice = jsonObject.getString("PrePrice");
                        String Image = jsonObject.getString("Image");
                        CreateProductamazing(Id, Name, Price, PrePrice, Image, Cat);
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
                params.put("Cat", "4");
                return params;
            }
        };
        MySingleton.getInstance(MainActivity.this).addtoRequestQueue(stringrequest);

    }

    private void SetTimer() {
        handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        if (sec == 0) {
                            if (min != 0) {
                                min--;
                                sec = 59;
                            } else {
                                min = 59;
                                sec = 59;
                                hour--;
                            }

                        } else {
                            sec--;
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (hour < 10) {
                                    ID_Hour.setText("0" + hour + "");

                                } else {
                                    ID_Hour.setText(hour + "");


                                }
                                if (min < 10) {
                                    ID_Min.setText("0" + min + "");

                                } else {
                                    ID_Min.setText(min + "");

                                }
                                if (sec < 10) {
                                    ID_Sec.setText("0" + sec + "");

                                } else {
                                    ID_Sec.setText(sec + "");
                                    YoYo.with(Techniques.FlipInX).duration(1000).playOn(ID_Sec);


                                }


                            }
                        });


                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
        thread.start();


    }

    private void Initialize_ListView() {
        MyList.setAdapter(new BuyMenu_Adapter(this, R.layout.simple_layout_buy, items));
        MyList2.setAdapter(new BuyMenu_Adapter2(this, R.layout.simple_layout_buy2, items2));
        MyList3.setAdapter(new BuyMenu_Adapter3(this, R.layout.simple_layout_buy3, items3));
        MyList4.setAdapter(new BuyMenu_Adapter4(this, R.layout.simple_layout_buy4, items4));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String Email = bundle.getString("email");
            IdSinIn.setText(Email.toString());

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", Email);
            editor.commit();
        }
    }

    private void InitilizeArrayList_UrlImage() {
        UrlPic.add("http://learnshipp.ir/farshid/diji/image/banner/Kif3.jpg");
        UrlPic.add("http://learnshipp.ir/farshid/diji/image/banner/Kif2.jpg");
        UrlPic.add("http://learnshipp.ir/farshid/diji/image/banner/Kif1.jpg");
        UrlPic.add("http://learnshipp.ir/farshid/diji/image/banner/Kif3.jpg");

//        ///////
        names.add("کیف مجلسی");
        names.add("shirt");
        names.add("laptop");
        names.add("pad");

    }


    private void InitilizeView() {
        MainActivity_toolbar_search = findViewById(R.id.MainActivity_toolbar_search);
        LinnerShowAllProduct = findViewById(R.id.LinnerShowAllProduct);
        sliderShow = (SliderLayout) findViewById(R.id.slider);
        Linnear_Sign = findViewById(R.id.Linnear_Sign);
        UrlPic = new ArrayList<>();
        names = new ArrayList<>();
        items2 = new ArrayList<>();
        items = new ArrayList<>();
        items3 = new ArrayList<>();
        items4 = new ArrayList<>();
        Linnear_Exit = findViewById(R.id.Linnear_Exit);
        IdSinIn = findViewById(R.id.IdSinIn);
        Linnear_Store = findViewById(R.id.Linnear_Store);
        items.add(new BuyMenu_Model("خانه", R.drawable.icon_home));
        items.add(new BuyMenu_Model("لیست دسته بندی محصولات", R.drawable.icon_cate));
        items2.add(new BuyMenu_Model2("سبد خرید", R.drawable.icon_store));
        items3.add(new BuyMenu_Model3("علاقه مندی ها", R.drawable.icon_star));
        items4.add(new BuyMenu_Model4("اضافه کردن محصول", R.drawable.icon_setting));
        items4.add(new BuyMenu_Model4("سوالات متداول", R.drawable.icon_about));
        items4.add(new BuyMenu_Model4("درباره ما", R.drawable.icon_about));


        MyList = findViewById(R.id.navigationView_ListView);
        Myimage = findViewById(R.id.ActivityMain_Menu);
        myDrawer = findViewById(R.id.Main_DrawerLayout);
        MyList2 = findViewById(R.id.navigationView_ListView2);
        MyList3 = findViewById(R.id.navigationView_ListView3);
        MyList4 = findViewById(R.id.navigationView_ListView4);

        ID_Hour = findViewById(R.id.Id_Hour);
        ID_Min = findViewById(R.id.Id_Min);
        ID_Sec = findViewById(R.id.Id_Sec);
        linearLayout2 = findViewById(R.id.Linnear_Shegaft);
        Linnear_new = findViewById(R.id.Linnear_new);
        Linnear_sell = findViewById(R.id.Linnear_sell);
        LInnear_fagat_dar_diji = findViewById(R.id.LInnear_fagat_dar_diji);


        TextL1 = findViewById(R.id.Textl1);
        TextL2 = findViewById(R.id.Textl2);
        TextL3 = findViewById(R.id.Textl3);
        TextL4 = findViewById(R.id.Textl4);
        TextL5 = findViewById(R.id.Textl5);
        TextL6 = findViewById(R.id.Textl6);
        TextL7 = findViewById(R.id.Textl7);
        TextL8 = findViewById(R.id.Textl8);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.LinnerShowAllProduct: {
                Intent in = new Intent(this, Activity_ListProduct.class);
                startActivity(in);
                break;
            }
            case R.id.ActivityMain_Menu: {
                myDrawer.openDrawer(Gravity.RIGHT);
                break;
            }
            case R.id.Linnear_Sign: {
                if (IdSinIn.getText().toString().equals("ورود و ثبت نام")) {
                    Intent in = new Intent(this, Sign.class);
                    startActivityForResult(in, 0);
                } else {
                    Linnear_Store.setVisibility(View.VISIBLE);
                    Linnear_Exit.setVisibility(View.VISIBLE);
                }

                break;
            }
            case R.id.Linnear_Exit: {
                IdSinIn.setText("ورود و ثبت نام");
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", "ورود و ثبت نام");
                editor.putString("Sign", "SignOut");
                editor.commit();
                Linnear_Store.setVisibility(View.GONE);
                Linnear_Exit.setVisibility(View.GONE);
                myDrawer.closeDrawer(Gravity.RIGHT);
                break;
            }
            case R.id.MainActivity_toolbar_search: {
                Intent in = new Intent(this, Activity_search.class);
                startActivity(in);
                break;
            }
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
