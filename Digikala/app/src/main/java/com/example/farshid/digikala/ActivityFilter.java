package com.example.farshid.digikala;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.farshid.digikala.Adapter.Adapter_Listview_filterproduct;
import com.example.farshid.digikala.Modle.Model_listView_FilterProduct;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Fragment.Fragment_Color;
import Fragment.Fragment_Price;

public class ActivityFilter extends AppCompatActivity {

    ListView Activity_Filter_ListView;
    String Server_Url = "http://learnshipp.ir/farshid/diji/FilterVar.php", Cat = "";
    ArrayList<Model_listView_FilterProduct> TitleArray;
    JSONObject obj;
    JSONArray jsonarray;
    ArrayAdapter<String> arrayAdapter;
    LinearLayout Activity_Filter_linear_layout, ActivityFilter_Filter;
    String item1 = "", title = "";
    TextView ActivityFilter_Toolbar_text,ActivityFilter_textFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Bundle bundle = getIntent().getExtras();
        Cat = bundle.getString("Cat");
        InitilizeView();
        SetFont();


        Init_variableFilter_FromServer();
        ActivityFilter_Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout v = (LinearLayout) Activity_Filter_linear_layout.getChildAt(0);
                View v2 = v.getChildAt(0);

                if (v2 instanceof BetterSpinner) {
                    BetterSpinner spinner = (BetterSpinner) v2;
                    String item = spinner.getText().toString();
//                    Intent
                    switch (title) {
                        case "رنگ": {
                            Intent in = new Intent(ActivityFilter.this, Activity_ShowSelectedlistProduct.class);
                            in.putExtra("Color", item);
                            in.putExtra("Cat", Cat);
                            in.putExtra("flag", true);
                            startActivity(in);
                            System.exit(0);
                            finish();
                            break;
                        }

                        case "قیمت": {
                            switch (item) {
                                case "کمتر از 500 تومان": {
                                    item1 = 500000 + "";
                                    break;

                                }
                                case "کمتر از یک میلیون تومان": {
                                    item1 = 1000000 + "";
                                    break;

                                }
                                case "کمتر از 2 میلیون تومان": {
                                    item1 = 2000000 + "";
                                    break;

                                }
                                case "کمتر از 3 میلیون تومان": {
                                    item1 = 3000000 + "";
                                    break;

                                }
                                case "کمتر از 4 میلیون تومان": {
                                    item1 = 4000000 + "";
                                    break;

                                }
                            }
                            Intent in = new Intent(ActivityFilter.this, Activity_ShowSelectedlistProduct.class);
                            in.putExtra("Cost", item1);
                            in.putExtra("Cat", Cat);
                            in.putExtra("flag", true);
                            startActivity(in);
                            System.exit(0);
                            finish();
                            break;
                        }
                    }
                }


            }
        });
        Activity_Filter_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Model_listView_FilterProduct value = (Model_listView_FilterProduct) adapterView.getItemAtPosition(i);
                title = value.title;
                Activity_Filter_linear_layout.removeAllViews();
                if (title.equals("رنگ")) {
                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment_Color fragment_color = new Fragment_Color();
                    fragmentTransaction.add(R.id.Activity_Filter_linear_layout, fragment_color);
                    fragmentTransaction.commit();

                }
                if (title.equals("قیمت")) {
                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment_Price fragment_color = new Fragment_Price();
                    fragmentTransaction.add(R.id.Activity_Filter_linear_layout, fragment_color);
                    fragmentTransaction.commit();

                }


            }
        });


    }

    private void SetFont() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        ActivityFilter_Toolbar_text.setTypeface(tf);
        ActivityFilter_textFilter.setTypeface(tf);
    }


    private void Init_variableFilter_FromServer() {
        StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int count = 0;
                try {
                    obj = new JSONObject(response);
                    jsonarray = obj.getJSONArray("resp");
                    while (count < jsonarray.length()) {
                        JSONObject jsonObject = jsonarray.getJSONObject(count);
                        String Title = jsonObject.getString("Title");
                        TitleArray.add(new Model_listView_FilterProduct(Title));
                        count++;
                    }
                    Activity_Filter_ListView.setAdapter(arrayAdapter);


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
        MySingleton.getInstance(ActivityFilter.this).addtoRequestQueue(stringrequest);


    }


    private void InitilizeView() {

//        ListView
        Activity_Filter_ListView = findViewById(R.id.Activity_Filter_ListView);

//        ArrayList

        TitleArray = new ArrayList<>();

//        ArrayAdapter
        arrayAdapter = new Adapter_Listview_filterproduct(this, R.layout.simple_listview_filterproduct, TitleArray);

//        LinearLayout

        Activity_Filter_linear_layout = findViewById(R.id.Activity_Filter_linear_layout);
        ActivityFilter_Filter = findViewById(R.id.ActivityFilter_Filter);

//        TextView

        ActivityFilter_Toolbar_text=findViewById(R.id.ActivityFilter_Toolbar_text);
        ActivityFilter_textFilter=findViewById(R.id.ActivityFilter_textFilter);
    }

}
