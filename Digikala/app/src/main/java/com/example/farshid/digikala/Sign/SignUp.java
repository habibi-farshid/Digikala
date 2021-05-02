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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class SignUp extends AppCompatActivity {


    EditText ID_Email, ID_Pass, ID_Pass2;
    LinearLayout Id_LinnearSignUp;
    CardView activity_signup_linear;
    MyLoadingButton SignUp_my_loading_button;
    boolean tru = true;
    TextView Signup_descreption, Id_TextSignUp, sign_up_id_text_register, TextError,Textwellcomesignout;
    String Server_Url = "http://learnshipp.ir/farshid/diji/Register.php";
    SharedPreferences sharedPreferences;

    ImageView ActivityMain_Menu, sign_up_id_icon_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Initialize_View();
        set_font();
        SignUp_my_loading_button.setMyButtonClickListener(new MyLoadingButton.MyLoadingButtonClick() {
            @Override
            public void onMyLoadingButtonClick() {
                TextError.setVisibility(View.INVISIBLE);
                SignUp_my_loading_button.showLoadingButton();
                if (Trust()) {
                    StringRequest stringrequest = new StringRequest(Request.Method.POST, Server_Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final  String response) {

                            if (!response.toString().equals("exist")) {
                                SignUp_my_loading_button.showDoneButton();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {


                                        YoYo.with(Techniques.Bounce).duration(1000).listen(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);


                                                YoYo.with(Techniques.SlideOutRight).duration(2000).playOn(activity_signup_linear);
                                                YoYo.with(Techniques.Tada).duration(2000).listen(new AnimatorListenerAdapter() {
                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {
                                                        super.onAnimationEnd(animation);
                                                        Textwellcomesignout.setVisibility(View.VISIBLE);
                                                        YoYo.with(Techniques.ZoomIn).duration(5000).listen(new AnimatorListenerAdapter() {
                                                            @Override
                                                            public void onAnimationEnd(Animator animation) {
                                                                super.onAnimationEnd(animation);

                                                                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SignUp.this);
                                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                editor.putString("email", ID_Email.getText().toString().trim());
                                                                editor.putString("Sign", "Sign");
                                                                editor.putString("user", response);
                                                                editor.commit();
                                                                Intent in = new Intent(SignUp.this, MainActivity.class);
                                                                startActivity(in);
                                                                finish();
                                                            }
                                                        }).playOn(Textwellcomesignout);

                                                    }
                                                }).playOn(activity_signup_linear);


                                            }
                                        }).playOn(activity_signup_linear);

                                    }
                                }, 2000);

                            }if (response.toString().equals("exist")) {

                                SignUp_my_loading_button.showErrorButton();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextError.setVisibility(View.VISIBLE);
                                        YoYo.with(Techniques.Tada).duration(1000).playOn(activity_signup_linear);
                                        ID_Email.getText().clear();
                                        ID_Pass.getText().clear();
                                        ID_Pass2.getText().clear();
                                        YoYo.with(Techniques.Tada).duration(1000).playOn(TextError);

                                    }
                                }, 2000);

                                //                                ID_Pass.setError("رمز وارد شده اشتباه است!");
//                                ID_Pass2.setError("تکرار رمز وارد شده اشتباه است!");
//                                ID_Email.setError("ایمیل وارد شده اشتباه است!");

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
                    MySingleton.getInstance(SignUp.this).addtoRequestQueue(stringrequest);

                }else {
                    SignUp_my_loading_button.showErrorButton();
                }
            }
        });

        ActivityMain_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        sign_up_id_icon_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SignUp.this, Activity_search.class);
                startActivity(in);
                finish();
                System.exit(0);
            }
        });
    }

    private void set_font() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        Signup_descreption.setTypeface(tf);
        ID_Email.setTypeface(tf);
        ID_Pass.setTypeface(tf);
        ID_Pass2.setTypeface(tf);
//        Id_TextSignUp.setTypeface(tf);
        sign_up_id_text_register.setTypeface(tf);
    }

    private boolean Trust() {

        tru = true;
        if (!ID_Pass2.getText().toString().equals(ID_Pass.getText().toString())) {
            ID_Pass2.getText().clear();
            ID_Pass.getText().clear();
            ID_Pass2.setError("کلمه عبور وارد شده با تکرار کلمه عبور مطابقت ندارد!");
            tru = false;
            return tru;
        }
        if (ID_Pass2.getText().toString().isEmpty() || ID_Email.getText().toString().isEmpty() || ID_Pass.getText().toString().isEmpty()) {
            if (ID_Email.getText().toString().isEmpty()){
                ID_Email.setError("ایمیل را وارد کنید!");
            }
            if (ID_Pass.getText().toString().isEmpty()){
                ID_Pass.setError("رمز عبور را وارد کنید!");
            }
            if (ID_Pass2.getText().toString().isEmpty()){
                ID_Pass2.setError("تکرار رمز عبور را وارد کنید!");
            }
            tru = false;
            return tru;
        }
        if (ID_Email.getText().toString().length()!=11) {
            Toast.makeText(this, "شماره تلفن صحیح نمی باشد!", Toast.LENGTH_SHORT).show();
            tru = false;
            return tru;
        }
        return tru;

    }

    private void Initialize_View() {
        SignUp_my_loading_button=findViewById(R.id.SignUp_my_loading_button);
//        Id_TextSignUp = findViewById(R.id.Id_TextSignUp);
        Signup_descreption = findViewById(R.id.Signup_descreption);
        ID_Email = findViewById(R.id.ID_Email);
        ID_Pass = findViewById(R.id.ID_Pass);
        ID_Pass2 = findViewById(R.id.ID_Pass2);
        sign_up_id_text_register = findViewById(R.id.sign_up_id_text_register);
//        Id_LinnearSignUp = findViewById(R.id.Id_LinnearSignUp);
        ActivityMain_Menu = findViewById(R.id.ActivityMain_Menu);
        sign_up_id_icon_search = findViewById(R.id.sign_up_id_icon_search);
        activity_signup_linear = findViewById(R.id.activity_signup_linear);
        TextError = findViewById(R.id.TextError);
        Textwellcomesignout = findViewById(R.id.Textwellcomesignout);
        TextError.setVisibility(View.INVISIBLE);
        Textwellcomesignout.setVisibility(View.INVISIBLE);


    }
}
