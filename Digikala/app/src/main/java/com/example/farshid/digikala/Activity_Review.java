package com.example.farshid.digikala;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.farshid.digikala.Product.ProductCat;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_Review extends AppCompatActivity implements View.OnClickListener {

    JSONObject obj3;
    JSONArray jsonarray3;
    ArrayList<String> param;
    String Server_Ur33 = "http://learnshipp.ir/farshid/diji/GiveRate2.php";
    String Server_Ur3 = "http://learnshipp.ir/farshid/diji/savecommint.php";
    String Id = "";
    ProductCat cat2;
    LinearLayout ActivvityNagd_Linear;
    LinearLayout.LayoutParams layoutParamsCat;
    TextView Text1, Text2, Text3, Text4, Text5;
    EditText EdtTopicNagd, EdtStrengths, EdtWeakpoint, EdtDescriptionNagd;
    Button Submit;
    ImageView icontoolbar;

    public int Innovation = 0, BuildQuality = 0, Parameter = 0;
    boolean trust = false;
    ArrayList<Float> myarray;

    public void CreatCat(String Cat1, double a) {
        cat2 = new ProductCat(this);
        cat2.textViewcat.setText(Cat1);
        cat2.progressBar.setProgress((int) a);
        layoutParamsCat = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ActivvityNagd_Linear.addView(cat2);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nagd);
        Bundle bundle = getIntent().getExtras();
        Id = bundle.getString("Id");
        InitilizeView();
        SetFont();
        CheckInternet();
        icontoolbar.setOnClickListener(this);
        Submit.setOnClickListener(this);
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


    @Override
    public void onBackPressed() {
        // your code.
    }

    private void SetFont() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        Text1.setTypeface(tf);
        Text2.setTypeface(tf);
        Text3.setTypeface(tf);
        Text4.setTypeface(tf);
        Text5.setTypeface(tf);
        EdtTopicNagd.setTypeface(tf);
        EdtStrengths.setTypeface(tf);
        EdtWeakpoint.setTypeface(tf);
        EdtDescriptionNagd.setTypeface(tf);
        Submit.setTypeface(tf);

    }

    private void InitilizeView() {
        ActivvityNagd_Linear = findViewById(R.id.ActivityNagd_Linear);
        new ArrayList<>();

//        ToolBar
        Text1 = findViewById(R.id.toolbarNagd_Text);
        icontoolbar = findViewById(R.id.toolbarNagd_Exit);
//        Activity
        Text2 = findViewById(R.id.ActivityNagd_Topic);
        Text3 = findViewById(R.id.ActivityNagd_TopicStrengths);
        Text4 = findViewById(R.id.ActivityNagd_TopicWeakPoints);
        Text5 = findViewById(R.id.ActivityNagd_TopicDescription);

        EdtTopicNagd = findViewById(R.id.ActivityNagd_EditTextTopic);
        EdtStrengths = findViewById(R.id.ActivityNagd_EditTextTopicStrengths);
        EdtWeakpoint = findViewById(R.id.ActivityNagd_EditTextTopicWeakPoints);
        EdtDescriptionNagd = findViewById(R.id.ActivityNagd_EditTextTopicDescription);

        Submit = findViewById(R.id.ActivityNagd_ButtonSubmit);

    }

    private void InitRate() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur33, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                        CreatCat("نوآوری", Innovation * 20);

                    }
                    if (BuildQuality >= 0) {
                        CreatCat("کیفیت ساخت", BuildQuality * 20);
                    }
                    if (Parameter >= 0) {
                        CreatCat("پارامتر های تست", Parameter * 20);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Id", Id);
                return params;
            }
        };
        MySingleton.getInstance(Activity_Review.this).addtoRequestQueue(stringrequest);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.toolbarNagd_Exit: {
                finish();
                break;
            }
            case R.id.ActivityNagd_ButtonSubmit: {
                if (trust()) {
                    StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur3, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.toString().equals("Insert")) {
                                Intent in = new Intent(Activity_Review.this, ActivityCommint.class);
                                in.putExtra("myflag", true);
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
                            params.put("Id", Id);
                            params.put("Text_Topic_Negd", EdtTopicNagd.getText().toString().trim());
                            params.put("Text_Topic_Text", EdtDescriptionNagd.getText().toString().trim());
                            params.put("Text_Strengths", EdtStrengths.getText().toString().trim());
                            params.put("Text_Weak", EdtWeakpoint.getText().toString().trim());
                            return params;
                        }
                    };
                    MySingleton.getInstance(Activity_Review.this).addtoRequestQueue(stringrequest);

                }

                break;
            }

        }
    }

    private boolean trust() {
        trust = true;
        if (EdtTopicNagd.getText().toString().isEmpty()) {
            Drawable warning = (Drawable) getResources().getDrawable(R.drawable.star_yellow_fill);
            EdtTopicNagd.setError("لطفا موضوع را بنویسید", warning);
            trust = false;
        }
        if (EdtStrengths.getText().toString().isEmpty()) {
            Drawable warning = (Drawable) getResources().getDrawable(R.drawable.star_yellow_fill);
            EdtStrengths.setError("لطفا نقاط قوت را بنویسید", warning);
            trust = false;
        }
        if (EdtWeakpoint.getText().toString().isEmpty()) {
            Drawable warning = (Drawable) getResources().getDrawable(R.drawable.star_yellow_fill);
            EdtWeakpoint.setError("لطفا نقاط ضعف را بنویسید", warning);
            trust = false;
        }
        if (EdtDescriptionNagd.getText().toString().isEmpty()) {
            Drawable warning = (Drawable) getResources().getDrawable(R.drawable.star_yellow_fill);
            EdtDescriptionNagd.setError("لطفا توضیحات خود را بنویسید", warning);
            trust = false;
        }
        return true;

    }
}
