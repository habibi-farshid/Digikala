package com.example.farshid.digikala;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.ArrayList;
import java.util.Locale;

public class Activity_search extends AppCompatActivity implements View.OnClickListener {


    EditText toolbar_edit_text;
    ImageView toolbar_Image_microphone, toolbar_ImageBack, toolbar_search_clean;
    Button buttonSearch;
    String string = "";

    private TextView voiceInput;
    private TextView speakButton;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        InitializationView();
        set_font();
        toolbar_search_clean.setOnClickListener(this);
        toolbar_ImageBack.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);

        toolbar_Image_microphone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                askSpeechInput();

            }
        });
        toolbar_edit_text.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() > 2) {
                    toolbar_Image_microphone.setVisibility(View.GONE);
                    toolbar_search_clean.setVisibility(View.VISIBLE);
                } else {
                    toolbar_Image_microphone.setVisibility(View.VISIBLE);
                    toolbar_search_clean.setVisibility(View.GONE);
                }


            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 2) {
                    toolbar_Image_microphone.setVisibility(View.GONE);
                    toolbar_search_clean.setVisibility(View.VISIBLE);
                }

            }
        });


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



    private void set_font() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        toolbar_edit_text.setTypeface(tf);
        buttonSearch.setTypeface(tf);

    }

    private void InitializationView() {
        toolbar_edit_text = findViewById(R.id.toolbar_search_edittext);
        toolbar_ImageBack = findViewById(R.id.toolbar_search_back);
        toolbar_Image_microphone = findViewById(R.id.toolbar_search_microphone);
        buttonSearch = findViewById(R.id.toolbar_search_buttonsearch);
        toolbar_search_clean = findViewById(R.id.toolbar_search_clean);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.toolbar_search_clean: {
                toolbar_edit_text.getText().clear();
                string = "";
                toolbar_Image_microphone.setVisibility(View.VISIBLE);
                toolbar_search_clean.setVisibility(View.GONE);
                break;
            }
            case R.id.toolbar_search_back: {
                finish();
                System.exit(0);
                break;
            }
            case R.id.toolbar_search_buttonsearch: {

                if (isInternetOn()) {
                    if (toolbar_edit_text.getText().length() != 0) {
                        Intent in = new Intent(Activity_search.this, Activity_ShowSelectedlistProduct.class);
                        in.putExtra("search", toolbar_edit_text.getText().toString().trim());
                        in.putExtra("flag2", true);
                        startActivity(in);
                        finish();
                        System.exit(0);

                    }
                } else {
                    onload();
                }


                break;
            }
        }
    }

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    sea.setText(result.get(0));

                    Intent in = new Intent(Activity_search.this, Activity_ShowSelectedlistProduct.class);
                    in.putExtra("search", result.get(0).toString());
                    in.putExtra("flag2", true);
                    startActivity(in);
                    finish();
                    System.exit(0);

                }
                break;
            }
        }
    }
}
