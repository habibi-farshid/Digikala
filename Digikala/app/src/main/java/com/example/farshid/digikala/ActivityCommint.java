package com.example.farshid.digikala;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
import com.example.farshid.digikala.Product.Custom_Commint;
import com.example.farshid.digikala.Product.Custom_RateCommint;
import com.example.farshid.digikala.Product.Custom_pos_neg;
import com.example.farshid.digikala.Product.fani;
import com.example.farshid.digikala.Sign.Sign;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityCommint extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ta";
    Custom_RateCommint cat22;
    //    LinearLayout
    LinearLayout Linnear_CustomCommint, Linnear_Cat, Linnear_Point, Linnear_Pos_Neg, Linnear_ratecommint2;
    LinearLayout.LayoutParams layoutParams, layoutParamsCat, layoutParamsPos_Neg, layoutParamsFani;
    LinearLayout LinnearFani, CustomComment_Linear_Pos_Neg;

    //    JSON
    JSONObject obj3;
    JSONArray jsonarray3;
    JSONObject obj1;
    JSONArray jsonarray1;
    //    Url
    String Server_Ur31 = "http://learnshipp.ir/farshid/diji/Commit.php";
    String Server_U = "http://learnshipp.ir/farshid/diji/giverate3.php";
    String Server_UComment = "http://learnshipp.ir/farshid/diji/insert_comment.php";

//    ArrayList

    ArrayList<String> title;
    ArrayList<String> matn;
    ArrayList<String> postive;
    ArrayList<String> negative;
    ArrayList<String> idlike;
    ArrayList<String> iddislike;
    ArrayList<String> name;
    ArrayList<String> Pos;
    ArrayList<String> Neg;
    ArrayList<String> bu;
    ArrayList<String> user;


    ArrayList<Integer> in;


    ArrayList<Integer> in2;
    ArrayList<String> bu2;
    ArrayList<String> pa;
    ArrayList<String> title2;
    ArrayList<String> MyVal2;
    ArrayList<Integer> SumStr;


    //    Class
    Custom_pos_neg custom_pos_neg;
    Custom_Commint custom_commint;
    Custom_RateCommint cat2;

    fani fani;
    //    RatingBar
    RatingBar ActivityComment_SumRating;
    String Iduser = "", userme = "";

    //    TextView
    TextView ActivityComment_TextSumRating, Commint_IDEdame, Commint_IDExit, ActivityCommint_TextNameProduct, ToolbarCommint_Text, Comment_CountComment;

    //    ImageView
    ImageView IDLine, ToolbarCommint_Exit;


//    FloatingActionButton

    FloatingActionButton fab;


    public int Count, Count2 = 0, Count3 = 0;
    public int Innovation = 0, BuildQuality = 0, Parameter = 0, CoutRow = 0, CoutRow2 = 0, my = 0, i1 = 0;
    float sumRat = 0;
    boolean flag = false;


    private int MyCount;
    ArrayList<Float> myarray;
    SharedPreferences sharedPreferences, sharedPreferences2, sharedPreferences3, sharedPreferences4;
    String IdPro = "", Name = "", Id = "";
    int Sum = 0, index = 0;


    public void CreatCat(String Cat1, double a) {
        cat2 = new Custom_RateCommint(this);
        cat2.TextProgress.setText(Cat1);
        cat2.progressBar.setProgress((int) a);
        layoutParamsCat = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Linnear_ratecommint2.addView(cat2);
    }

    public void CreatCat2(String Cat1, double a) {
        cat2 = new Custom_RateCommint(this);
        cat2.TextProgress.setText(Cat1);
        cat2.progressBar.setProgress((int) a);
        layoutParamsCat = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Linnear_ratecommint2.addView(cat2);


    }

    public void CreatPos_Neg(String Cat1, int a) {
        custom_pos_neg = new Custom_pos_neg(this);
        custom_pos_neg.textViewme.setText(Cat1);
        custom_pos_neg.imageViewme.setImageResource(a);
        layoutParamsPos_Neg = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        CustomComment_Linear_Pos_Neg.addView(custom_pos_neg);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commint);
        Bundle bundle = getIntent().getExtras();
        flag = bundle.getBoolean("myflag", false);
        SharedPreferences sharedPreferences3 = getSharedPreferences("USER", MODE_PRIVATE);
        IdPro = sharedPreferences3.getString("Cat", "");
        Id = sharedPreferences3.getString("id", "");
        Name = sharedPreferences3.getString("Name", "");
        SharedPreferences shard = PreferenceManager.getDefaultSharedPreferences(this);
        userme = shard.getString("user", "");
        InitializeView();
        InitilizeList();
        if (flag) {
            Linnear_CustomCommint.removeAllViews();
            InitializeInf();
        } else {
            InitializeInf();
        }
        SetFont();
        Commint_IDEdame.setOnClickListener(this);
        Commint_IDExit.setOnClickListener(this);
        ToolbarCommint_Exit.setOnClickListener(this);
        ActivityCommint_TextNameProduct.setText(Name);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ActivityCommint.this);
                String email = sharedPreferences.getString("Sign", "");
                if (email.equals("SignOut")) {
                    Intent intent = new Intent(ActivityCommint.this, Sign.class);
                    intent.putExtra("Id", Id);
                    intent.putExtra("IdP", IdPro);

                    startActivity(intent);
                } else if (email.equals("Sign")) {
                    // Click action

                    StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_UComment, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent intent = new Intent(ActivityCommint.this, Activity_Add_Commint.class);
                            intent.putExtra("Id", Id);
                            intent.putExtra("IdP", IdPro);
                            startActivity(intent);
//                            if (response.toString().equals("NO")) {
//
//                            } else {
//                                Toast.makeText(ActivityCommint.this, "شما قبل برای این محصول نظر داده اید.", Toast.LENGTH_SHORT).show();
//                            }

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
                            params.put("Id", userme);
                            return params;
                        }
                    };
                    MySingleton.getInstance(ActivityCommint.this).addtoRequestQueue(stringrequest);


                }

            }
        });

    }

    private void SetFont() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        ActivityComment_TextSumRating.setTypeface(tf);
        Commint_IDEdame.setTypeface(tf);
        Commint_IDExit.setTypeface(tf);
        ActivityCommint_TextNameProduct.setTypeface(tf);
        ToolbarCommint_Text.setTypeface(tf);

    }

    private void InitilizeList() {
        title = new ArrayList<>();
        matn = new ArrayList<>();
        postive = new ArrayList<>();
        negative = new ArrayList<>();
        idlike = new ArrayList<>();
        iddislike = new ArrayList<>();
        name = new ArrayList<>();
        Pos = new ArrayList<>();
        Neg = new ArrayList<>();
        user = new ArrayList<>();

        in = new ArrayList<>();
        bu = new ArrayList<>();
        pa = new ArrayList<>();
        title2 = new ArrayList<>();
        MyVal2 = new ArrayList<>();
        in2 = new ArrayList<>();
        bu2 = new ArrayList<>();
        SumStr = new ArrayList<>();
    }

    public void InitializeInfRate(final int MYCou, final int j1, final String IdCommint) {
        Iduser = String.valueOf(MYCou);
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_U, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int count = 0;
                try {
                    Innovation = 0;
                    BuildQuality = 0;
                    Parameter = 0;
                    Count = 0;
                    in.clear();
                    bu.clear();
                    obj3 = new JSONObject(response);
                    jsonarray3 = obj3.getJSONArray("resp");
                    while (count < jsonarray3.length()) {
                        JSONObject jsonObject = jsonarray3.getJSONObject(count);
                        Innovation = jsonObject.getInt("innovation");
                        BuildQuality = jsonObject.getInt("Buildquality");
                        Parameter = jsonObject.getInt("Parameter");
                        count++;
                    }
                    if (Innovation >= 0) {
                        Count++;
                        in.add((int) Innovation * 20);
                        bu.add("نوآوری");
                    }
                    if (BuildQuality >= 0) {
                        Count++;
                        in.add((int) BuildQuality * 20);
                        bu.add("کیفیت ساخت");
                    }
                    if (Parameter >= 0) {
                        Count++;
                        in.add((int) Parameter * 20);
                        bu.add("پارامتر تست");

                    }
                    fun(j1, bu, in, Count, Iduser, IdCommint);
                    Count = 0;
                    in.clear();
                    bu.clear();

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
                params.put("Id", String.valueOf(MYCou));
                return params;
            }
        };
        MySingleton.getInstance(ActivityCommint.this).addtoRequestQueue(stringrequest);


    }

    private void fun(int i, ArrayList<String> bun, ArrayList<Integer> inn, int Count5, String IdUser, String IdCommint) {

        int iduser = Integer.parseInt(IdUser), idcommint = Integer.parseInt(IdCommint);
        in2 = inn;
        bu2 = bun;
        custom_commint = new Custom_Commint(ActivityCommint.this);
        custom_commint.TextUser.setText(name.get(i));
        custom_commint.TextNumberLike.setText(idlike.get(i));
        custom_commint.TextNumberDislike.setText(iddislike.get(i));
        custom_commint.TextNamCommint.setText(title.get(i));
        custom_commint.TextMatnCommint.setText(matn.get(i));
        custom_commint.TextMatnCommint.setText(matn.get(i));
        custom_commint.TextMatnCommint.setText(matn.get(i));
        custom_commint.CustomComment_pos_neg_Text.setText(Pos.get(i));
        custom_commint.CustomComment_NegText.setText(Neg.get(i));
        custom_commint.iduser = iduser;
        custom_commint.idcommint = idcommint;
        for (int j = 0; j < Count5; j++) {
            cat22 = new Custom_RateCommint(ActivityCommint.this);
            cat22.TextProgress.setText(bun.get(j));
            cat22.progressBar.setProgress(inn.get(j));
            custom_commint.CustomComment_LinearProgress.addView(cat22);
        }
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Linnear_CustomCommint.addView(custom_commint);
    }

    public void InitializeInf() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur31, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int count = 0;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("title"));
                    for (int j1 = 0; j1 < jsonArray.length(); j1++) {
                        String s1 = (String) jsonArray.get(j1);
                        Log.i(TAG, "title" + s1);
                        title.add(s1);

                    }
                    JSONArray jsonArray3 = new JSONArray(jsonObject.getString("matn"));
                    for (int j1 = 0; j1 < jsonArray3.length(); j1++) {
                        String s1 = (String) jsonArray3.get(j1);
                        Log.i(TAG, "matn" + s1);
                        matn.add(s1);
                    }
                    JSONArray jsonArray4 = new JSONArray(jsonObject.getString("postive"));
                    for (int j1 = 0; j1 < jsonArray4.length(); j1++) {
                        String s1 = (String) jsonArray4.get(j1);
                        Log.i(TAG, "postive" + s1);
                        postive.add(s1);

                    }
                    JSONArray jsonArray5 = new JSONArray(jsonObject.getString("negative"));
                    for (int j1 = 0; j1 < jsonArray5.length(); j1++) {
                        String s1 = (String) jsonArray5.get(j1);
                        Log.i(TAG, "negative" + s1);
                        negative.add(s1);

                    }
                    JSONArray jsonArray6 = new JSONArray(jsonObject.getString("idlike"));
                    for (int j1 = 0; j1 < jsonArray6.length(); j1++) {
                        String s1 = (String) jsonArray6.get(j1);
                        Log.i(TAG, "idlike" + s1);
                        idlike.add(s1);

                    }
                    JSONArray jsonArray7 = new JSONArray(jsonObject.getString("iddislike"));
                    for (int j1 = 0; j1 < jsonArray7.length(); j1++) {
                        String s1 = (String) jsonArray7.get(j1);
                        Log.i(TAG, "iddislike" + s1);
                        iddislike.add(s1);

                    }
                    JSONArray jsonArray8 = new JSONArray(jsonObject.getString("name"));
                    for (int j1 = 0; j1 < jsonArray8.length(); j1++) {
                        String s1 = (String) jsonArray8.get(j1);
                        Log.i(TAG, "name" + s1);
                        name.add(s1);

                    }
                    JSONArray jsonArray9 = new JSONArray(jsonObject.getString("in"));
                    JSONArray jsonArray10 = new JSONArray(jsonObject.getString("bu"));
                    JSONArray jsonArray11 = new JSONArray(jsonObject.getString("pa"));
                    for (int j1 = 0; j1 < jsonArray9.length(); j1++) {
                        if (j1 == 0) {
                            index++;
                        }
                        String s1 = (String) jsonArray9.get(j1);
                        int s = Integer.parseInt(s1);
                        Sum = Sum + s;
                        Log.i(TAG, "SumIN" + Sum);
                    }
                    SumStr.add(Sum);
                    Sum = 0;
                    for (int j1 = 0; j1 < jsonArray10.length(); j1++) {
                        if (j1 == 0) {
                            index++;
                        }
                        String s1 = (String) jsonArray10.get(j1);
                        int s = Integer.parseInt(s1);
                        Sum = Sum + s;
                        Log.i(TAG, "SumBu" + Sum);
//                        bu.add(s1);

                    }
                    SumStr.add(Sum);
                    Sum = 0;
                    for (int j1 = 0; j1 < jsonArray11.length(); j1++) {
                        if (j1 == 0) {
                            index++;
                        }
                        String s1 = (String) jsonArray11.get(j1);
                        int s = Integer.parseInt(s1);
                        Sum = Sum + s;
                        Log.i(TAG, "SumPa" + Sum);

                    }
                    SumStr.add(Sum);
                    Sum = 0;
                    JSONArray jsonArray12 = new JSONArray(jsonObject.getString("postive"));
                    for (int j1 = 0; j1 < jsonArray12.length(); j1++) {
                        String s1 = (String) jsonArray12.get(j1);
                        Log.i(TAG, "postive" + s1);
                        Pos.add(s1);


                    }
                    JSONArray jsonArray13 = new JSONArray(jsonObject.getString("negative"));
                    for (int j1 = 0; j1 < jsonArray13.length(); j1++) {
                        String s1 = (String) jsonArray13.get(j1);
                        Log.i(TAG, "negative" + s1);
                        Neg.add(s1);

                    }
                    JSONArray jsonArray15 = new JSONArray(jsonObject.getString("Id"));
                    for (int j1 = 0; j1 < jsonArray15.length(); j1++) {
                        String s1 = (String) jsonArray15.get(j1);
                        Neg.add(s1);
                    }
                    JSONArray jsonArray14 = new JSONArray(jsonObject.getString("user"));
                    for (int j1 = 0; j1 < jsonArray14.length(); j1++) {
                        String s1 = (String) jsonArray14.get(j1);
                        Log.i(TAG, "user" + s1);
                        user.add(s1);
                        String s2 = (String) jsonArray15.get(j1);
                        InitializeInfRate(Integer.parseInt(user.get(j1)), j1, s2);
                        Log.i(TAG, "MYuser" + user.get(j1));
                    }

                    for (int j = 0; j < index; j++) {
                        sumRat = sumRat + SumStr.get(j);
                        Log.i(TAG, "SumStr" + sumRat);

                    }

                    int r = jsonArray10.length();
                    float a = (sumRat / r) / index;
                    String str = String.format("%.2f", a);
                    String y111 = String.valueOf(persian.PerisanNumber(String.valueOf(5)));
                    String y211 = String.valueOf(persian.PerisanNumber(String.valueOf(str)));
                    String q2 = y211 + "از" + y111;

                    String w = "از مجموع" + r + "رای ثبت شده";
                    Comment_CountComment.setText(w);
                    ActivityComment_TextSumRating.setText(q2);
                    ActivityComment_SumRating.setRating(Float.valueOf(a));
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
                params.put("IdRate", IdPro);
                params.put("Idpro", Id);
                return params;
            }
        };
        MySingleton.getInstance(ActivityCommint.this).addtoRequestQueue(stringrequest);


    }

    private void InitializeView() {
        Linnear_CustomCommint = findViewById(R.id.Linnear_CustomCommint);
        Linnear_Point = findViewById(R.id.Linnear_Point);
        Linnear_Cat = findViewById(R.id.Linnear_Point);
        myarray = new ArrayList<>();
        ActivityComment_SumRating = findViewById(R.id.ActivityComment_SumRating);
        ActivityComment_TextSumRating = findViewById(R.id.ActivityComment_TextSumRating);
        Commint_IDEdame = findViewById(R.id.Commint_IDEdame);
        Commint_IDExit = findViewById(R.id.Commint_IDExit);
        IDLine = findViewById(R.id.IDLine);
        Commint_IDExit.setVisibility(View.GONE);
        IDLine.setVisibility(View.GONE);
        Linnear_Point.setVisibility(View.GONE);
        ActivityCommint_TextNameProduct = findViewById(R.id.ActivityCommint_TextNameProduct);
        ToolbarCommint_Text = findViewById(R.id.ToolbarCommint_Text);
        ToolbarCommint_Exit = findViewById(R.id.ToolbarCommint_Exit);
        Linnear_Pos_Neg = findViewById(R.id.Linnear_Pos_Neg);
        Linnear_Pos_Neg.setVisibility(View.GONE);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        LinnearFani = findViewById(R.id.LinnearFani);
        Comment_CountComment = findViewById(R.id.Comment_CountComment);
//        Linnear_ratecommint2 = findViewById(R.id.Linnear_ratecommintme);
//        CustomComment_Linear_Pos_Neg=findViewById(R.id.CustomComment_Linear_Pos_Neg);


    }

    @Override
    public void onClick(View view) {
        int Id = view.getId();
        switch (Id) {
            case R.id.ToolbarCommint_Exit: {

                finish();
                System.exit(0);
                break;
            }
            case R.id.Commint_IDEdame: {

                Commint_IDExit.setVisibility(View.VISIBLE);
                IDLine.setVisibility(View.VISIBLE);
                Linnear_Point.setVisibility(View.VISIBLE);
                Linnear_Pos_Neg.setVisibility(View.VISIBLE);
                Commint_IDEdame.setVisibility(View.GONE);
                break;
            }
            case R.id.Commint_IDExit: {

                Commint_IDExit.setVisibility(View.GONE);
                IDLine.setVisibility(View.GONE);
                Linnear_Point.setVisibility(View.GONE);
                Commint_IDEdame.setVisibility(View.VISIBLE);
                Linnear_Pos_Neg.setVisibility(View.GONE);

                break;
            }

        }
    }
}
