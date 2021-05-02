package com.example.farshid.digikala;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.farshid.digikala.Product.Custom_AddCommint;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_Add_Commint extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "a";
    JSONObject obj3;
    JSONArray jsonarray3;
    String Server_Ur33 = "http://learnshipp.ir/farshid/diji/GiveRate.php";
    String Server_Ur3 = "http://learnshipp.ir/farshid/diji/AddCommint.php";

    Custom_AddCommint custom_addCommint;
    LinearLayout.LayoutParams layoutParams;
    LinearLayout linearLayout2;
    int CoutRow = 0;
    public int Innovation = 0, BuildQuality = 0, Parameter = 0, CoutRow2 = 0, my = 0, i1 = 0;
    public double Innovation2 = 0, BuildQuality2 = 0, Parameter2 = 0;
    public double Innovation3 = 0, BuildQuality3 = 0, Parameter3 = 0;
    float sumRat = 0;
    ArrayList<Float> myarray;
    LinearLayout lin1, lin2;
    TextView AddCommint_Text, Text1, Text2, Text3;
    ImageView AddCommint_Exit;
    ArrayList<String> PointCustom, titleCustom, tt;
    String StringPC = "", StringC = "", Catmy = "", user = "", IdP = "";
    String MyId = "";


    public void CreatCat(String TextRating) {
        custom_addCommint = new Custom_AddCommint(this);
        custom_addCommint.TextRating.setText(TextRating);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout2.addView(custom_addCommint);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__commint);
        SharedPreferences sharedPreferences3 = getSharedPreferences("USER", MODE_PRIVATE);
        IdP = sharedPreferences3.getString("Cat", "");
        Catmy = sharedPreferences3.getString("id", "");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        user = sharedPreferences.getString("user", "");
        InitializeView();
        SetFont();
        CheckInternet();


        lin2.setOnClickListener(this);
        lin1.setOnClickListener(this);
        AddCommint_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void CheckInternet() {
        if (isInternetOn()) {
            InitRate();

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


    private void SetFont() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        AddCommint_Text.setTypeface(tf);
        Text1.setTypeface(tf);
        Text2.setTypeface(tf);
        Text3.setTypeface(tf);


    }

    private void InitRate() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur33, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressBar.setVisibility(View.GONE);
                int count = 0;
                try {
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
                        CreatCat("نوآوری");

                    }
                    if (BuildQuality >= 0) {
                        CreatCat("کیفیت ساخت");
                    }
                    if (Parameter >= 0) {
                        CreatCat("پارامتر های تست");
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
                params.put("Id", IdP);
                return params;
            }
        };
        MySingleton.getInstance(Activity_Add_Commint.this).addtoRequestQueue(stringrequest);

    }

    private void InitializeView() {
        linearLayout2 = findViewById(R.id.linearLayout222);
        lin1 = findViewById(R.id.lin1);
        lin2 = findViewById(R.id.lin2);
        AddCommint_Exit = findViewById(R.id.AddCommint_Exit);
        AddCommint_Text = findViewById(R.id.AddCommint_Text);
        Text1 = findViewById(R.id.Text1);
        Text2 = findViewById(R.id.Text2);
        PointCustom = new ArrayList();
        Text3 = findViewById(R.id.Text3);
        titleCustom = new ArrayList<>();
        tt = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        int Id = view.getId();
        switch (Id) {
            case R.id.lin2: {
                PointCustom.clear();
                titleCustom.clear();
                int count = linearLayout2.getChildCount();
                Log.i(TAG, "Child" + count);
                for (int i = 0; i < count; i++) {
                    View v = linearLayout2.getChildAt(i);
                    custom_addCommint = (Custom_AddCommint) v;
                    PointCustom.add(String.valueOf(custom_addCommint.Count));
                    tt.add(custom_addCommint.textViewRating.getText().toString() + "");
                    Log.i(TAG, "textViewRating" + tt.get(i));
                    if (tt.get(i).equals("کیفیت ساخت")) {
                        titleCustom.add(String.valueOf("BQ"));
                    }
                    if (tt.get(i).equals("نوآوری")) {
                        titleCustom.add(String.valueOf("NO"));

                    }
                    if (tt.get(i).equals("پارامتر های تست")) {
                        titleCustom.add(String.valueOf("TP"));
                    }

                }
                StringPC = TextUtils.join(",", PointCustom);
                StringC = TextUtils.join(",", titleCustom);
                Log.i(TAG, "StringPC: " + StringPC);
                Log.i(TAG, "StringC: " + StringC);
//
                StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.toString().equals("")) {
                            Toast.makeText(Activity_Add_Commint.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(Activity_Add_Commint.this, Activity_Review.class);
                            in.putExtra("Id", response);
                            startActivity(in);
                            finish();
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
                        params.put("StringPC", StringPC);
                        params.put("StringC", StringC);
                        params.put("user", user);
                        params.put("Id", Catmy);
                        params.put("idrate", IdP);
                        return params;
                    }
                };
                MySingleton.getInstance(Activity_Add_Commint.this).addtoRequestQueue(stringrequest);
                break;
            }
            case R.id.lin1: {
                PointCustom.clear();
                titleCustom.clear();
                int count = linearLayout2.getChildCount();
                Log.i(TAG, "Child" + count);
                for (int i = 0; i < count; i++) {
                    View v = linearLayout2.getChildAt(i);
                    Custom_AddCommint custom_addCommint = (Custom_AddCommint) v;
                    PointCustom.add(String.valueOf(custom_addCommint.Count));
                    tt.add(custom_addCommint.textViewRating.getText().toString() + "");
                    Log.i(TAG, "textViewRating" + tt.get(i));
                    if (tt.get(i).equals("کیفیت ساخت")) {
                        titleCustom.add(String.valueOf("BQ"));
                    }
                    if (tt.get(i).equals("نوآوری")) {
                        titleCustom.add(String.valueOf("NO"));

                    }
                    if (tt.get(i).equals("پارامتر های تست")) {
                        titleCustom.add(String.valueOf("TP"));
                    }
                    Log.i(TAG, "Child" + PointCustom.get(i));

                }
                StringPC = TextUtils.join(",", PointCustom);
                StringC = TextUtils.join(",", titleCustom);
                Log.i(TAG, "StringPC: " + StringPC);
                Log.i(TAG, "StringC: " + StringC);
//
                StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.toString().equals("")) {
                            Toast.makeText(Activity_Add_Commint.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(Activity_Add_Commint.this, ActivityCommint.class);

                            startActivity(in);
                            finish();
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
                        params.put("StringPC", StringPC);
                        params.put("StringC", StringC);
                        params.put("user", user);
                        params.put("Id", Catmy);
                        params.put("idrate", IdP);
                        return params;
                    }
                };
                MySingleton.getInstance(Activity_Add_Commint.this).addtoRequestQueue(stringrequest);
                break;
            }
        }
    }
}
