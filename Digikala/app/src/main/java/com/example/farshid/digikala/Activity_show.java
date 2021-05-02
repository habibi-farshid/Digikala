package com.example.farshid.digikala;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.farshid.digikala.Product.ProductAmazing_smiller;
import com.example.farshid.digikala.Product.ProductCat;
import com.github.hujiaweibujidao.wava.Techniques;
import com.github.hujiaweibujidao.wava.YoYo;

import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.squareup.picasso.Picasso;
import com.tfb.fbtoast.FBToast;
import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener;
import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_show extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, View.OnClickListener {
    private static final String TAG = "add";
    SliderLayout sliderShow;

    //  ArrayList
    ArrayList<String> UrlPic;
    ArrayList<String> names;
    ArrayList<String> url;
    ArrayList<Integer> myarray;
    AppBarLayout app_bar_layout;


    ImageView Im1, Im2, Im3, ActivityShow_IDHeart, Fani_Exit;

    //   String
    String id = "";
    String Server_Ur3 = "http://learnshipp.ir/farshid/diji/Gallary.php";
    String Server_Ur31 = "http://learnshipp.ir/farshid/diji/Giveinfo_product.php";
    String Server_Ur32 = "http://learnshipp.ir/farshid/diji/Cat.php";
    String Server_Ur33 = "http://learnshipp.ir/farshid/diji/GiveRate.php";
    String Server_Ur34 = "http://learnshipp.ir/farshid/diji/peshnahad.php";
    String Server_Ur = "http://learnshipp.ir/farshid/diji/interest.php";
    String Name = "";
    String Cat = "", Catmy = "";


    //    TextView
    TextView IdSinIn, ID_Hour, ID_Min, ID_Sec, ActivityShow_TextBold, ActivityShow_TextNormal, ActivityShow_IDTextToolbar;
    TextView TextDetil, TextCommint, TextColor1, TextColor2, TextGaranty1, TextGaranty2, TextEdame, TextMyPrice, Textdiji, TextSimiler, Id_Hour0, TextRating;


    //JSON
    JSONObject obj;
    JSONArray jsonarray;
    JSONObject obj3;
    JSONArray jsonarray3;
    JSONObject obj4;
    JSONArray jsonarray4;


    //    Int
    int sec = 59;
    int min = 13;
    int hour = 10;
    public int Innovation = 0, BuildQuality = 0, Parameter = 0, CoutRow = 0, CoutRow2 = 0, my = 0, i1 = 0;


    //    LinearLayout
    LinearLayout.LayoutParams layoutParams;
    LinearLayout ActivityShow_Linnear_CommintUser, Linnear_new;

    //    Class
    ProductAmazing_smiller pro_similer;
    public static Handler handler;
    LinearLayout Linnear_Cat, linear_payment;
    ProductCat cat2;
    LinearLayout.LayoutParams layoutParamsCat;

    RatingBar IdRating;

    CardView ActivityShow_CardView;

    public double Innovation2 = 0, BuildQuality2 = 0, Parameter2 = 0;
    public double Innovation3 = 0, BuildQuality3 = 0, Parameter3 = 0;
    float sumRat = 0;


    public void CreatCat(String Cat1, double a) {
        cat2 = new ProductCat(this);
        cat2.textViewcat.setText(Cat1);
        cat2.progressBar.setProgress((int) a);
        layoutParamsCat = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Linnear_Cat.addView(cat2);


    }

    public void CreateProductamazing_sell(String Id, String name, String price, String preprice, String image, String Cat1) {
        pro_similer = new ProductAmazing_smiller(this);
        String y1 = String.valueOf(persian.PerisanNumber(price)) + "تومان";
        String y2 = String.valueOf(persian.PerisanNumber(preprice)) + "تومان";
        pro_similer.Name.setText(name);
        pro_similer.PrePrice.setText(y2);
        pro_similer.Id = Id;
        pro_similer.Cat = Cat1;
        pro_similer.Price.setText(y1);
        Picasso.with(Activity_show.this).load(image).into(pro_similer.Image);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Linnear_new.addView(pro_similer);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
//        گرفتن آیدی
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("Id");
        Catmy = bundle.getString("Cat");

//        مقدار دهی ویو ها
        InitializeView();


        CheckInternet();
        if (getIntent().getData() != null) {

            ZarinPal.getPurchase(this).verificationPayment(getIntent().getData(), new OnCallbackVerificationPaymentListener() {
                @Override
                public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, PaymentRequest paymentRequest) {
                    Log.i("TAG", "onCallbackResultVerificationPayment: " + refID);
                }
            });
        }


//        لیسینر App Bar Layout
        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

                Log.i(TAG, "onOffsetChanged: " + i);
                int io = -(i);
//                if (io > 340 && io < 413) {
//                    ActivityShow_IDTextToolbar.setText(Name);
//                    ActivityShow_IDTextToolbar.setAlpha(0.5f);
//                } else
                if (io > 340) {
                    Im1.setImageResource(R.drawable.icon_store);
                    Im2.setImageResource(R.drawable.icon_search);
                    Im3.setImageResource(R.drawable.icon_go3);
                    ActivityShow_IDTextToolbar.setAlpha(1f);
                    ActivityShow_IDTextToolbar.setText(Name);

                } else {

                    Im1.setImageResource(R.drawable.icon_store2);
                    Im2.setImageResource(R.drawable.icon_search2);
                    Im3.setImageResource(R.drawable.icon_go2);
                    ActivityShow_IDTextToolbar.setText("");
                }
            }
        });
        TextDetil.setOnClickListener(this);
        TextEdame.setOnClickListener(this);
        ActivityShow_Linnear_CommintUser.setOnClickListener(this);
        Im2.setOnClickListener(this);
        linear_payment.setOnClickListener(this);
        Im3.setOnClickListener(this);
        ActivityShow_IDHeart.setOnClickListener(this);
    }

    private void CheckInternet() {
        if (isInternetOn()) {
//        SetDefaulSlider();
//        گرفتن اطلاعات از دیتا بیس و قرار دادن در سلایدر
            GetData();

//        مقدار دهس تایمر
            SetTimer();
//        مقدار دهی فونت
            SetFont();
//        مقدار دهی اطلاعات
            InitializeInf();
//        InitCat();
            InitRate();
            GiveMoshabeh();


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
                        CheckInternet();

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

    private void GiveMoshabeh() {

        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur34, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {
                    obj4 = new JSONObject(response);
                    jsonarray4 = obj4.getJSONArray("resp");
                    while (count < jsonarray4.length()) {
                        JSONObject jsonObject = jsonarray4.getJSONObject(count);
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
                params.put("Catmy", Catmy);
                params.put("id", id);
                return params;
            }
        };
        MySingleton.getInstance(Activity_show.this).addtoRequestQueue(stringrequest);


    }

    private void InitRate() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur33, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {
                    String y1 = String.valueOf(persian.PerisanNumber(String.valueOf(5)));
                    String y2 = String.valueOf(persian.PerisanNumber(String.valueOf(0)));
                    String q = y2 + "از" + y1;
                    obj3 = new JSONObject(response);
                    jsonarray3 = obj3.getJSONArray("resp");
                    while (count < jsonarray3.length()) {
                        CoutRow++;
                        my = 0;
                        i1 = 0;
                        String y11 = String.valueOf(persian.PerisanNumber(String.valueOf(5)));
                        String y21 = String.valueOf(persian.PerisanNumber(String.valueOf(0)));
                        String q1 = y21 + "از" + y11;
                        JSONObject jsonObject = jsonarray3.getJSONObject(count);
                        Innovation = Integer.parseInt(jsonObject.getString("innovation"));
                        Innovation3 = Innovation3 + Integer.parseInt(jsonObject.getString("innovation"));

                        BuildQuality = Integer.parseInt(jsonObject.getString("Buildquality"));
                        BuildQuality3 = BuildQuality3 + Integer.parseInt(jsonObject.getString("Buildquality"));

                        Parameter = Integer.parseInt(jsonObject.getString("Parameter").toString().trim());
                        Parameter3 = Parameter3 + Parameter;

                        if (Innovation >= 0) {
                            i1++;
                            my = my + Innovation;
                        }
                        if (BuildQuality >= 0) {
                            i1++;
                            my = my + BuildQuality;
                        }
                        if (Parameter >= 0) {
                            i1++;
                            my = my + Parameter;
                        }
                        float i2 = 4 * i1;
                        myarray.add(my);
                        Log.i(TAG, "myarray:" + myarray.get(count));
                        Log.i(TAG, "i2:" + i2);
                        Log.i(TAG, "My:" + my);

                        count++;

                    }
                    for (int j = 0; j < CoutRow; j++) {
                        sumRat = sumRat + myarray.get(j);
                    }
                    Log.i(TAG, "sumRat:" + sumRat);

                    if (Innovation3 >= 0) {
                        CoutRow2++;
                        Innovation2 = ((Innovation3 * 20) / (CoutRow));
//                        Innovation2 = (Innovation2 * 100);
                        Log.i(TAG, "Innovation: " + Innovation2);
                        CreatCat("نوآوری", Innovation2);
                    }
                    if (BuildQuality3 >= 0) {
                        CoutRow2++;
//                        BuildQuality2 = (BuildQuality3 / (4 * CoutRow));
                        Log.i(TAG, "BuildQuality: " + BuildQuality2);
                        BuildQuality2 = ((BuildQuality3 * 20) / (CoutRow));

//                        BuildQuality2 = (BuildQuality2 * 100);
                        Log.i(TAG, "BuildQuality: " + BuildQuality2);
                        CreatCat("کیفیت ساخت", BuildQuality2);

                    }
                    if (Parameter3 >= 0) {
                        CoutRow2++;
                        Parameter2 = ((Parameter3 * 20) / (CoutRow));

//                        Parameter2 = (Parameter3 / (4 * CoutRow));
//                        Parameter2 = (Parameter2 * 100);
                        Log.i(TAG, "Parameter2: " + Parameter2);
                        CreatCat("پارامتر تست", Parameter2);
                    }
                    double e = Parameter3 + BuildQuality3 + Innovation3;
                    if (e == 0) {
                        ActivityShow_CardView.setVisibility(View.GONE);

                    }

                    float a = (sumRat / CoutRow) / CoutRow2;
                    String str = String.format("%.2f", a);
                    String y111 = String.valueOf(persian.PerisanNumber(String.valueOf(5)));
                    String y211 = String.valueOf(persian.PerisanNumber(String.valueOf(str)));
                    String q2 = y211 + "از" + y111;
                    TextRating.setText(q2);
                    IdRating.setRating(Float.valueOf(a));
                    Log.i(TAG, "CoutRow2" + CoutRow2);

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
                params.put("Catmy", Catmy);
                params.put("id", id);
                return params;
            }
        };
        MySingleton.getInstance(Activity_show.this).addtoRequestQueue(stringrequest);

    }

    private void InitializeInf() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur31, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {

                    obj = new JSONObject(response);
                    jsonarray = obj.getJSONArray("resp");
                    while (count < jsonarray.length()) {
                        JSONObject jsonObject = jsonarray.getJSONObject(count);
                        Name = jsonObject.getString("Name");
                        String Color = jsonObject.getString("Color");
                        String Garanty = jsonObject.getString("Garanty");
                        String Price = jsonObject.getString("Price");
                        String Description = jsonObject.getString("Description");
                        Cat = jsonObject.getString("Cat");
                        TextColor2.setText(Color.toString());
                        TextGaranty2.setText(Garanty.toString());
                        TextMyPrice.setText(persian.PerisanNumber(Price) + " تومان");
                        ActivityShow_TextBold.setText(Name.toString());
                        ActivityShow_TextNormal.setText(Name.toString());
                        Textdiji.setText(Description.toString());
                        Id_Hour0.setText(Cat.toString());
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
                params.put("Id", id);
                return params;
            }
        };
        MySingleton.getInstance(Activity_show.this).addtoRequestQueue(stringrequest);


    }

    private void SetFont() {
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "Font/dima.ttf");
        TextSimiler.setTypeface(tf2);

        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        Textdiji.setTypeface(tf);
        ID_Hour.setTypeface(tf);
        ID_Min.setTypeface(tf);
        ID_Sec.setTypeface(tf);
        TextDetil.setTypeface(tf);
        TextCommint.setTypeface(tf);
        TextColor1.setTypeface(tf);
        TextColor2.setTypeface(tf);
        TextGaranty1.setTypeface(tf);
        TextGaranty2.setTypeface(tf);
        TextEdame.setTypeface(tf);
        ActivityShow_IDTextToolbar.setTypeface(tf);
        TextMyPrice.setTypeface(tf);
        ActivityShow_TextBold.setTypeface(tf);
        ActivityShow_TextNormal.setTypeface(tf);


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

    private void InitializeView() {
        linear_payment = findViewById(R.id.linear_payment);
        app_bar_layout = findViewById(R.id.app_bar_layout);
        ActivityShow_IDTextToolbar = findViewById(R.id.ActivityShow_IDTextToolbar);
        UrlPic = new ArrayList<>();
        names = new ArrayList<>();
        url = new ArrayList<>();
        Im1 = findViewById(R.id.Im1);
        Im2 = findViewById(R.id.Im2);
        Im3 = findViewById(R.id.Im3);
        sliderShow = (SliderLayout) findViewById(R.id.ActivityShow_slider);
        names.add("phone");
        names.add("shirt");
        names.add("laptop");
        names.add("laptop");
        ID_Hour = findViewById(R.id.Id_Hour2);
        ID_Min = findViewById(R.id.Id_Min2);
        ID_Sec = findViewById(R.id.Id_Sec2);
        TextDetil = findViewById(R.id.Textdetail);
        TextCommint = findViewById(R.id.Textcommint);
        TextColor1 = findViewById(R.id.Textcolor1);
        TextColor2 = findViewById(R.id.Textcolor2);
        TextGaranty1 = findViewById(R.id.TextGaranty1);
        TextGaranty2 = findViewById(R.id.TextGaranty2);
        TextEdame = findViewById(R.id.TextEdamaMatalb);
        TextMyPrice = findViewById(R.id.TextCost);
        ActivityShow_TextBold = findViewById(R.id.ActivityShow_TextBold);
        ActivityShow_TextNormal = findViewById(R.id.ActivityShow_TextNormal);
        Textdiji = findViewById(R.id.Textdiji);
        TextSimiler = findViewById(R.id.TextSimiler);
        Id_Hour0 = findViewById(R.id.Id_Hour0);
        Linnear_Cat = findViewById(R.id.Linnear_MyCat);
        TextRating = findViewById(R.id.TextRating);
        IdRating = findViewById(R.id.IdRating);
        ActivityShow_Linnear_CommintUser = findViewById(R.id.ActivityShow_Linnear_CommintUser);
        myarray = new ArrayList<>();
        Textdiji.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 100));
        Linnear_new = findViewById(R.id.Linnear_new2);
        ActivityShow_CardView = findViewById(R.id.ActivityShow_CardView);

        ActivityShow_IDHeart = findViewById(R.id.ActivityShow_IDHeart);


    }

    private void GetData() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {

                    obj = new JSONObject(response);
                    jsonarray = obj.getJSONArray("resp");
                    while (count < jsonarray.length()) {
                        JSONObject jsonObject = jsonarray.getJSONObject(count);
                        String Image = jsonObject.getString("Image");
                        String im = "http://learnshipp.ir/farshid/diji/image/Gallary/" + Image;
                        UrlPic.add(im);
                        TextSliderView textSliderView = new TextSliderView(Activity_show.this);
                        textSliderView
                                .image(im).setScaleType(BaseSliderView.ScaleType.Fit).empty(R.drawable.sliderimage)
                                .setOnSliderClickListener(Activity_show.this);
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle()
                                .putString("extra", names.get(count));
                        sliderShow.addSlider(textSliderView);
//                        UrlPic.add(Image);
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
                params.put("Id", id);
                return params;
            }
        };
        MySingleton.getInstance(Activity_show.this).addtoRequestQueue(stringrequest);


    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onClick(View view) {
        int Id = view.getId();
        switch (Id) {

            case R.id.Textdetail: {
                Intent in = new Intent(Activity_show.this, Activity_Fani.class);
                in.putExtra("Id", id);
                startActivity(in);
                break;
            }
            case R.id.TextEdamaMatalb: {
                if (TextEdame.getText().toString().equals("ادامه مطالب")) {
                    Textdiji.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextEdame.setText("بستن");
                } else {
                    Textdiji.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 100));
                    TextEdame.setText("ادامه مطالب");

                }
                break;
            }
            case R.id.ActivityShow_Linnear_CommintUser: {
                Intent in = new Intent(Activity_show.this, ActivityCommint.class);
                SharedPreferences sharedPreferences4 = getSharedPreferences("USER", MODE_PRIVATE);
                SharedPreferences.Editor editor3 = sharedPreferences4.edit();
                editor3.putString("Cat", Catmy);
                editor3.putString("id", id);
                editor3.putString("Name", Name);
                editor3.commit();
                in.putExtra("myflag", false);
                startActivity(in);
                break;
            }
            case R.id.Im2: {
                Intent in = new Intent(Activity_show.this, Activity_search.class);
                startActivity(in);
                finish();
                System.exit(0);

                break;
            }
            case R.id.Im3: {
                finish();
                System.exit(0);

                break;
            }
            case R.id.linear_payment: {

                PaymentRequest payment = ZarinPal.getPaymentRequest();


                payment.setMerchantID("71c705f8-bd37-11e6-aa0c-000c295eb8fc");
                payment.setAmount(100);
                payment.setDescription("پرداخت برای خرید موبایل");
                payment.setCallbackURL("app://app");
                payment.setMobile("09357132959");
                payment.setEmail("habibi.farshid75@gmail.com");


                ZarinPal.getPurchase(getApplicationContext()).startPayment(payment, new OnCallbackRequestPaymentListener() {
                    @Override
                    public void onCallbackResultPaymentRequest(int status, String authority, Uri paymentGatewayUri, Intent intent) {

                        startActivity(intent);
                    }
                });

                break;
            }
            case R.id.ActivityShow_IDHeart: {
                YoYo.with(Techniques.DropOut).duration(1000).playOn(ActivityShow_IDHeart);
                StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.toString().equals("Delete")) {
                            FBToast.nativeToast(Activity_show.this,"از لیست علاقه مندی ها حذف شد!", FBToast.LENGTH_LONG);

                        }
                        if (response.toString().equals("Insert")) {
                            FBToast.successToast(Activity_show.this,"به لیست علاقه مندی ها اضافه شد!", FBToast.LENGTH_LONG);

                        }

//                progressBar.setVisibility(View.GONE);

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
                        params.put("Id", id);
                        return params;
                    }
                };
                MySingleton.getInstance(Activity_show.this).addtoRequestQueue(stringrequest);

                break;
            }
        }

    }
//    }
}
