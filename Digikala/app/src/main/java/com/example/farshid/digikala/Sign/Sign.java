package com.example.farshid.digikala.Sign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.farshid.digikala.Activity_search;
import com.example.farshid.digikala.MainActivity;
import com.example.farshid.digikala.MySingleton;
import com.example.farshid.digikala.R;
import com.example.myloadingbutton.MyLoadingButton;
import com.github.hujiaweibujidao.wava.Techniques;
import com.github.hujiaweibujidao.wava.YoYo;

import java.util.HashMap;
import java.util.Map;

public class Sign extends AppCompatActivity implements View.OnClickListener {

    CardView activity_sign_linear;
    CheckBox ID_CheckBoxShowPass;
    EditText ID_Pass, ID_Email;
    TextView ID_Register, ID_TextCheckBoxShowPass, Sign_TextSignIn, Sign_TextForget, sign_id_text_enter, Textwellcome;
    LinearLayout ID_SignIn;
    ImageView Sign_Id_search, sign_id_icon_back;
    public static String data = "";
    MyLoadingButton myLoadingButton;
    boolean flage = false;
    String Server_Url = "http://learnshipp.ir/farshid/diji/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        InitializationView();
        set_font();
        ID_CheckBoxShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ID_CheckBoxShowPass.isChecked()) {
                    ID_Pass.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    ID_Pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        Sign_Id_search.setOnClickListener(this);
        ID_Register.setOnClickListener(this);
        myLoadingButton.setMyButtonClickListener(new MyLoadingButton.MyLoadingButtonClick() {
            @Override
            public void onMyLoadingButtonClick() {
                myLoadingButton.showLoadingButton();
                if (Trust()) {
                    StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String response) {

                            if (!response.toString().equals("noLogin")) {
                                myLoadingButton.showDoneButton();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        YoYo.with(Techniques.Bounce).duration(1000).listen(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);

                                                YoYo.with(Techniques.SlideOutRight).duration(2000).playOn(activity_sign_linear);
                                                YoYo.with(Techniques.Tada).duration(2000).listen(new AnimatorListenerAdapter() {
                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {
                                                        super.onAnimationEnd(animation);
                                                        Textwellcome.setVisibility(View.VISIBLE);
                                                        YoYo.with(Techniques.ZoomIn).duration(5000).listen(new AnimatorListenerAdapter() {
                                                            @Override
                                                            public void onAnimationEnd(Animator animation) {
                                                                super.onAnimationEnd(animation);
                                                                Intent in = new Intent(Sign.this, MainActivity.class);
                                                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Sign.this);
                                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                editor.putString("email", ID_Email.getText().toString().trim());
                                                                editor.putString("Sign", "Sign");
                                                                editor.putString("user", response);
                                                                editor.commit();
                                                                in.putExtra("email", ID_Email.getText().toString().trim());
                                                                setResult(RESULT_OK, in);
                                                                finish();
                                                            }
                                                        }).playOn(Textwellcome);

                                                    }
                                                }).playOn(activity_sign_linear);


                                            }
                                        }).playOn(activity_sign_linear);


                                    }
                                }, 2000);




                            } else if (response.toString().equals("noLogin")) {

                                myLoadingButton.showErrorButton();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        YoYo.with(Techniques.Tada).duration(1000).playOn(activity_sign_linear);
                                        ID_Email.getText().clear();
                                        ID_Pass.getText().clear();
                                        ID_Pass.setError("رمز وارد شده اشتباه است!");
                                        ID_Email.setError("ایمیل وارد شده اشتباه است!");

                                    }
                                }, 2000);

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                Toast.makeText(LoginToPage.this, "اینترنت خود را بررسی کنید!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("Email", ID_Email.getText().toString().trim());
                            params.put("Password", ID_Pass.getText().toString().trim());
                            return params;
                        }
                    };
                    MySingleton.getInstance(Sign.this).addtoRequestQueue(stringrequest);

                } else {
                    myLoadingButton.showErrorButton();
                }

            }
        });
        sign_id_icon_back.setOnClickListener(this);

    }

    private void set_font() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        ID_Pass.setTypeface(tf);
        ID_Email.setTypeface(tf);
        ID_TextCheckBoxShowPass.setTypeface(tf);
        ID_Register.setTypeface(tf);
//        Sign_TextSignIn.setTypeface(tf);
        Sign_TextForget.setTypeface(tf);
        sign_id_text_enter.setTypeface(tf);
        Textwellcome.setTypeface(tf);


    }

    private void InitializationView() {
        Sign_TextForget = findViewById(R.id.Sign_TextForget);
        ID_Pass = findViewById(R.id.Sign_ID_Pass);
        ID_Email = findViewById(R.id.Sign_ID_Email);
        ID_CheckBoxShowPass = findViewById(R.id.ID_CheckBoxShowPass);
        ID_TextCheckBoxShowPass = findViewById(R.id.ID_TextCheckBoxShowPass);
        ID_Register = findViewById(R.id.ID_Register);
//        ID_SignIn = findViewById(R.id.ID_SignIn);
//        Sign_TextSignIn = findViewById(R.id.Sign_TextSignIn);
        Sign_Id_search = findViewById(R.id.Sign_Id_search);
        sign_id_text_enter = findViewById(R.id.sign_id_text_enter);
        sign_id_icon_back = findViewById(R.id.sign_id_icon_back);
        activity_sign_linear = findViewById(R.id.activity_sign_linear);
        Textwellcome = findViewById(R.id.Textwellcome);
        myLoadingButton = findViewById(R.id.my_loading_button);
        Textwellcome.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.Sign_Id_search: {
                Intent in = new Intent(Sign.this, Activity_search.class);
                startActivity(in);
                finish();
                System.exit(0);
                break;
            }
            case R.id.ID_Register: {
                Intent in = new Intent(Sign.this, SignUp.class);
                startActivity(in);
                finish();
                System.exit(0);
                break;
            }

            case R.id.sign_id_icon_back: {
                finish();
                System.exit(0);
                break;
            }
        }
    }

    private boolean Trust() {
        flage = true;
        if (ID_Pass.getText().toString().isEmpty()) {
            ID_Pass.getText().clear();
            ID_Pass.setError("رمز عبور را وارد کنید!");
            flage = false;
        }
        if (ID_Email.getText().toString().isEmpty()) {
            ID_Email.getText().clear();
            ID_Email.setError("شماره تلفن را وارد کنید!");
            flage = false;
        }
        if (ID_Email.getText().toString().length() != 11) {
            ID_Email.getText().clear();
            ID_Email.setError("شماره تلفن صحیح نمی باشد!");
            flage = false;
        }
        return flage;

    }
}
