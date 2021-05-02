package com.example.farshid.digikala;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.farshid.digikala.Product.Custom_interest_Show_list_Product;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class interest extends AppCompatActivity implements View.OnClickListener {


    //    LinearLayout
    LinearLayout.LayoutParams layoutParams;
    //    TextView
    TextView textToolbar;

    //    ImageView
    ImageView imageView;

    //   LinearLayout
    LinearLayout Activity_interest_Linear_layout;

    //String
    String Server_Ur31 = "http://learnshipp.ir/farshid/diji/Select_interest.php";
    boolean flag = false;

//    Class

    Custom_interest_Show_list_Product custom_show_select_list_product;

    //    Set Custom View
    public void Custom_list(String name, String cost, String image, String id, String Cat) {
        custom_show_select_list_product = new Custom_interest_Show_list_Product(this);
        custom_show_select_list_product.TextTitleBold.setText(name);
        custom_show_select_list_product.textTitleNormal.setText(name);
        custom_show_select_list_product.TextCost.setText(persian.PerisanNumber(cost));
        custom_show_select_list_product.Id = id;
        custom_show_select_list_product.Cat = Cat;
        Picasso.with(this).load(image).placeholder(R.drawable.image_default).into(custom_show_select_list_product.Image);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Activity_interest_Linear_layout.addView(custom_show_select_list_product);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        InitializeView();
        set_font();
        imageView.setOnClickListener(this);
        CheckInternet();


    }

    private void CheckInternet() {
        if (isInternetOn()) {
            givePost();

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

    private void givePost() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur31, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("Name"));
                    JSONArray jsonArray2 = new JSONArray(jsonObject.getString("Price"));
                    JSONArray jsonArray3 = new JSONArray(jsonObject.getString("PrePrice"));
                    JSONArray jsonArray4 = new JSONArray(jsonObject.getString("Image"));
                    JSONArray jsonArray5 = new JSONArray(jsonObject.getString("Id"));
                    JSONArray jsonArray6 = new JSONArray(jsonObject.getString("Cat"));
                    for (int j1 = 0; j1 < jsonArray.length(); j1++) {
                        flag = true;
                        String sName = (String) jsonArray.get(j1);
                        String sPrice = (String) jsonArray2.get(j1);
                        String sPrePrice = (String) jsonArray3.get(j1);
                        String sImage = (String) jsonArray4.get(j1);
                        String sId = (String) jsonArray5.get(j1);
                        String sCat = (String) jsonArray6.get(j1);
                        Custom_list(sName, sPrice, sImage, sId, sCat);


                    }
                    if (flag == false) {
                        Activity_interest_Linear_layout.setBackgroundResource(R.drawable.no_product3);
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
        MySingleton.getInstance(interest.this).addtoRequestQueue(stringrequest);


    }

    private void set_font() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        textToolbar.setTypeface(tf);

    }

    private void InitializeView() {
//        TextView
        textToolbar = findViewById(R.id.Activity_interest_toolbar_text);
//        ImageView
        imageView = findViewById(R.id.Activity_interest_toolbar_icon_back);
//        LinearLayout
        Activity_interest_Linear_layout = findViewById(R.id.Activity_interest_Linear_layout);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.Activity_interest_toolbar_icon_back: {
                finish();
//                new AwesomeSuccessDialog(this)
//                        .setTitle(R.string.app_name)
//                        .setMessage(R.string.app_name)
//                        .setColoredCircle(R.color.dialogSuccessBackgroundColor)
//                        .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
//                        .setCancelable(true)
//                        .setPositiveButtonText(getString(R.string.dialog_yes_button))
//                        .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
//                        .setPositiveButtonTextColor(R.color.white)
//                        .setNegativeButtonText(getString(R.string.dialog_no_button))
//                        .setNegativeButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
//                        .setNegativeButtonTextColor(R.color.white)
//                        .setPositiveButtonClick(new Closure() {
//                            @Override
//                            public void exec() {
//                                //click
//                            }
//                        })
//                        .setNegativeButtonClick(new Closure() {
//                            @Override
//                            public void exec() {
//                                //click
//                            }
//                        })
//                        .show();

                break;
            }

        }
    }
}
