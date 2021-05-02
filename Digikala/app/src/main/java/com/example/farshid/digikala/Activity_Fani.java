package com.example.farshid.digikala;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.farshid.digikala.Product.ProductCat;
import com.example.farshid.digikala.Product.fani;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_Fani extends AppCompatActivity {
    private static final String TAG = "S";

    ArrayList<String> title;
    ArrayList<String> MyVal;
    LinearLayout LinnearFani;
    ImageView Fani_Exit;
    fani fani;
    TextView tname;
    String Id = "";
    LinearLayout.LayoutParams layoutParams;
    String Server_Ur31 = "http://learnshipp.ir/farshid/diji/Spicification.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__fani);
        Bundle bundle = getIntent().getExtras();
        Id = bundle.getString("Id");
        title = new ArrayList<>();
        MyVal = new ArrayList<>();
        tname = findViewById(R.id.tname);
        Fani_Exit = findViewById(R.id.Fani_Exit);

        LinnearFani = findViewById(R.id.LinnearFani);
        Fani_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
                finish();
            }
        });
        CheckInternet();

    }

    private void CheckInternet() {
        if (isInternetOn()) {
            InitializeInf();


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


    private void InitializeInf() {
        Cache cache = MySingleton.getInstance(Activity_Fani.this).getRequestQueue().getCache();
        Cache.Entry entry = cache.get(Server_Ur31);
        if (entry != null) {
            int count = 0;
            try {
                String data = new String(entry.data, "UTF-8");
                JSONArray jsonarray = new JSONArray(data);
                title.clear();
                MyVal.clear();
                while (count < jsonarray.length()) {
                    JSONObject jsonObject = jsonarray.getJSONObject(count);

                    String Title = jsonObject.getString("Name");
                    tname.setText(Title);

                    JSONArray pr1 = jsonObject.getJSONArray("MyVal");
                    for (int jp = 0; jp < pr1.length(); jp++) {
                        String pr21 = (String) pr1.get(jp);
                        Log.i(TAG, "MyVal" + pr21);
                        MyVal.add(pr21);
                    }
                    JSONArray protitle = jsonObject.getJSONArray("Title");
                    for (int j = 0; j < protitle.length(); j++) {
                        String protitle2 = (String) protitle.get(j);
                        Log.i(TAG, "Mytitle" + protitle2);
                        title.add(protitle2);
                    }

                    Log.i(TAG, "MyValSize" + MyVal.size());
                    for (int j1 = 0; j1 < title.size(); j1++) {
                        fani = new fani(Activity_Fani.this);
                        fani.Fani_Name1.setText(title.get(j1));
                        fani.Fani_Name2.setText(MyVal.get(j1));
                        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        LinnearFani.addView(fani);
                    }
                    count++;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur31, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int count = 0;
                    try {
                        JSONArray jsonarray = new JSONArray(response);
                        title.clear();
                        MyVal.clear();
                        while (count < jsonarray.length()) {
                            JSONObject jsonObject = jsonarray.getJSONObject(count);

                            String Title = jsonObject.getString("Name");
                            tname.setText(Title);

                            JSONArray pr1 = jsonObject.getJSONArray("MyVal");
                            for (int jp = 0; jp < pr1.length(); jp++) {
                                String pr21 = (String) pr1.get(jp);
                                Log.i(TAG, "MyVal" + pr21);
                                MyVal.add(pr21);
                            }
                            JSONArray protitle = jsonObject.getJSONArray("Title");
                            for (int j = 0; j < protitle.length(); j++) {
                                String protitle2 = (String) protitle.get(j);
                                Log.i(TAG, "Mytitle" + protitle2);
                                title.add(protitle2);
                            }

                            Log.i(TAG, "MyValSize" + MyVal.size());
                            for (int j1 = 0; j1 < title.size(); j1++) {
                                fani = new fani(Activity_Fani.this);
                                fani.Fani_Name1.setText(title.get(j1));
                                fani.Fani_Name2.setText(MyVal.get(j1));
                                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                LinnearFani.addView(fani);
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
                    params.put("Id", Id);
                    return params;
                }
            };
            MySingleton.getInstance(Activity_Fani.this).addtoRequestQueue(stringrequest);

        }
    }
}
