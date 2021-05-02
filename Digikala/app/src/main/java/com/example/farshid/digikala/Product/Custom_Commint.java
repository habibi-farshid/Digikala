package com.example.farshid.digikala.Product;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.farshid.digikala.MySingleton;
import com.example.farshid.digikala.R;
import com.example.farshid.digikala.Sign.Sign;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.HashMap;
import java.util.Map;

public class Custom_Commint extends LinearLayout {

    public TextView TextUser, TextNumberLike, TextNumberDislike, TextNamCommint, TextMatnCommint, TextRateCommint, CustomComment_NegText,
            CustomComment_IDEdame, CustomComment_IDExit, CustomComment_pos_neg_Text;
    public ProgressBar CustomComment_ProgressBar;
    public String in = "", bu = "", pa = "";
    public LayoutParams layoutParams;
    public int idcommint = 0;
    public int iduser = 0, count = 0, count1 = 0;
    public ImageView Id_iconLike, Id_icondisLike;
    public CircularProgressView progress_wheel, progress_wheel2;
    public LinearLayout linearLayout, CustomComment_linearPos_and_neg, CustomComment_linear_Neg, CustomComment_LinearProgress, linear_like_id, id_linear_dislike;
    public ImageView CustomComment_IDLine, CustomComment_pos_neg_ImageView;
    String Server_Ur31 = "http://learnshipp.ir/farshid/diji/like.php";

    public Custom_Commint(Context context) {
        super(context);
        init(context);
    }

    public Custom_Commint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public Custom_Commint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.coustom_commint, this, true);
        TextUser = view.findViewById(R.id.Commint_TextNameUser);
        Id_iconLike = view.findViewById(R.id.Id_iconLike);
        Id_icondisLike = view.findViewById(R.id.Id_icondisLike);
        linear_like_id = view.findViewById(R.id.linear_like_id);
        id_linear_dislike = view.findViewById(R.id.id_linear_dislike);
        progress_wheel = view.findViewById(R.id.progress_wheel1);
        progress_wheel2 = view.findViewById(R.id.progress_wheel2);
        TextNumberLike = view.findViewById(R.id.Commint_TextLike);
        TextNumberDislike = view.findViewById(R.id.Commint_TextDisLike);
        TextNamCommint = view.findViewById(R.id.Commint_TextNameCommint);
        TextMatnCommint = view.findViewById(R.id.Commint_TextMatnCommint);
        linearLayout = view.findViewById(R.id.Custom_Comment_linnear_progress);
        CustomComment_LinearProgress = findViewById(R.id.CustomComment_LinearProgress);
        CustomComment_IDEdame = view.findViewById(R.id.CustomComment_IDEdame);
        CustomComment_IDExit = view.findViewById(R.id.CustomComment_IDExit);
        CustomComment_IDLine = view.findViewById(R.id.CustomComment_IDLine);
        CustomComment_pos_neg_Text = view.findViewById(R.id.CustomComment_pos_neg_Text);
        CustomComment_pos_neg_ImageView = view.findViewById(R.id.CustomComment_pos_neg_ImageView);
        CustomComment_linearPos_and_neg = view.findViewById(R.id.CustomComment_linearPos_and_neg);
        CustomComment_NegText = view.findViewById(R.id.CustomComment_NegText);
        CustomComment_linear_Neg = view.findViewById(R.id.CustomComment_linear_Neg);
        CustomComment_IDExit.setVisibility(GONE);
        CustomComment_IDLine.setVisibility(GONE);
        CustomComment_linearPos_and_neg.setVisibility(GONE);
        CustomComment_linear_Neg.setVisibility(GONE);
        CustomComment_LinearProgress.setVisibility(GONE);


//        ست کردن فونت
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Font/by.ttf");
        TextUser.setTypeface(tf);
        TextNumberLike.setTypeface(tf);
        TextNumberDislike.setTypeface(tf);
        TextNamCommint.setTypeface(tf);
        TextMatnCommint.setTypeface(tf);
        CustomComment_IDExit.setTypeface(tf);
        CustomComment_pos_neg_Text.setTypeface(tf);
        CustomComment_IDEdame.setTypeface(tf);
        CustomComment_NegText.setTypeface(tf);

        CustomComment_IDEdame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomComment_IDLine.setVisibility(VISIBLE);
                CustomComment_IDExit.setVisibility(VISIBLE);
                CustomComment_IDEdame.setVisibility(GONE);
                CustomComment_linearPos_and_neg.setVisibility(VISIBLE);
                CustomComment_linear_Neg.setVisibility(VISIBLE);
                CustomComment_LinearProgress.setVisibility(VISIBLE);


            }
        });
        linear_like_id.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                String email = sharedPreferences.getString("Sign", "");
                if (!email.equals("SignOut")) {
                    progress_wheel.setVisibility(VISIBLE);
                    progress_wheel.startAnimation();
                    Id_iconLike.setVisibility(GONE);

                    StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur31, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (count == 0) {
                                int i = Integer.parseInt(TextNumberLike.getText().toString()) + 1;
                                TextNumberLike.setText(i + "");
                                count = 1;
                                progress_wheel.setVisibility(GONE);
                                Id_iconLike.setVisibility(VISIBLE);

                            } else if (count == 1) {
                                int i = Integer.parseInt(TextNumberLike.getText().toString()) - 1;
                                if (i < 0) {
                                    TextNumberLike.setText("0");
                                } else {
                                    TextNumberLike.setText(i + "");
                                }
                                count = 0;
                                progress_wheel.setVisibility(GONE);
                                Id_iconLike.setVisibility(VISIBLE);


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
                            params.put("idcommint", String.valueOf(idcommint));
                            params.put("iduser", String.valueOf(iduser));
                            params.put("kind", "like");
                            return params;
                        }
                    };
                    MySingleton.getInstance(context).addtoRequestQueue(stringrequest);


//}


                } else {
                    Intent in = new Intent(context, Sign.class);
                    context.startActivity(in);
                    System.exit(0);
                }

            }
            });
        id_linear_dislike.setOnClickListener(new

            OnClickListener() {
                @Override
                public void onClick (View view){
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String email = sharedPreferences.getString("Sign", "");
                    if (!email.equals("SignOut")) {

                    progress_wheel2.setVisibility(VISIBLE);
                        progress_wheel.startAnimation();
                        Id_icondisLike.setVisibility(GONE);

                    StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Ur31, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (count1 == 0) {
                                int i = Integer.parseInt(TextNumberDislike.getText().toString()) - 1;
                                TextNumberDislike.setText(i + "");
                                count1 = 1;
                                progress_wheel2.setVisibility(GONE);
                                Id_icondisLike.setVisibility(VISIBLE);

                            } else if (count1 == 1) {
                                int i = Integer.parseInt(TextNumberDislike.getText().toString()) + 1;
                                TextNumberDislike.setText(i + "");
                                count1 = 0;
                                progress_wheel2.setVisibility(GONE);
                                Id_icondisLike.setVisibility(VISIBLE);
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
                            params.put("idcommint", String.valueOf(idcommint));
                            params.put("iduser", String.valueOf(iduser));
                            params.put("kind", "dislike");
                            return params;
                        }
                    };
                    MySingleton.getInstance(context).addtoRequestQueue(stringrequest);


//

                }else {
                        Intent in = new Intent(context, Sign.class);
                        context.startActivity(in);
                        System.exit(0);
                    }

                }
            });
        CustomComment_IDExit.setOnClickListener(new

            OnClickListener() {
                @Override
                public void onClick (View view){
                    CustomComment_IDLine.setVisibility(GONE);
                    CustomComment_IDExit.setVisibility(GONE);
                    CustomComment_IDEdame.setVisibility(VISIBLE);
                    CustomComment_linearPos_and_neg.setVisibility(GONE);
                    CustomComment_linear_Neg.setVisibility(GONE);
                    CustomComment_LinearProgress.setVisibility(GONE);


                }
            });
        }


    }
