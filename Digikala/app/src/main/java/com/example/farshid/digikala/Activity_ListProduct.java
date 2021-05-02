package com.example.farshid.digikala;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.farshid.digikala.Adapter.Adapter_listProduct;
import com.example.farshid.digikala.Modle.Model_listProduct;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_ListProduct extends AppCompatActivity {


    RecyclerView ListProduct_RecyclerView;
    ArrayList<Model_listProduct> modellist;
    Adapter_listProduct homeAdd;
    JSONObject obj;
    JSONArray jsonarray;
    TextView textToolBar;
    ImageView toolbarImageview;
    String Server_Ur22 = "http://learnshipp.ir/farshid/diji/GiveList.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__list_product);
        ListProduct_RecyclerView = findViewById(R.id.ListProduct_RecyclerView);
        textToolBar = findViewById(R.id.ActivityListProduct_Toolbar_text);
        toolbarImageview = findViewById(R.id.ActivityListProduct_Toolbar_Exit);
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        textToolBar.setTypeface(tf);
        toolbarImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        CheckInternet();

    }

    private void CheckInternet() {
        if (isInternetOn()) {
            listProduct();
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


    private void listProduct() {
        modellist = new ArrayList<>();
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
                        String Image = jsonObject.getString("Url");
                        String Cat = jsonObject.getString("Cat");
                        modellist.add(new Model_listProduct(Name, Image, Cat));
                        final LinearLayoutManager layoutmanager = new LinearLayoutManager(Activity_ListProduct.this);
                        RecyclerView.LayoutManager rcLayoutManager = layoutmanager;
                        ListProduct_RecyclerView.setLayoutManager(rcLayoutManager);
                        homeAdd = new Adapter_listProduct(Activity_ListProduct.this, modellist);
                        ListProduct_RecyclerView.setAdapter(homeAdd);
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
        MySingleton.getInstance(Activity_ListProduct.this).addtoRequestQueue(stringrequest);
    }
}
