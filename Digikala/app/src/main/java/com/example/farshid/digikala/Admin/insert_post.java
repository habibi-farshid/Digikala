package com.example.farshid.digikala.Admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farshid.digikala.Activity_show;
import com.example.farshid.digikala.Admin.upload.UploadImageInterface;
import com.example.farshid.digikala.Admin.upload.UploadObject;
import com.example.farshid.digikala.Admin.upload.upload;
import com.example.farshid.digikala.R;
import com.example.myloadingbutton.MyLoadingButton;
import com.github.hujiaweibujidao.wava.Techniques;
import com.github.hujiaweibujidao.wava.YoYo;
import com.tfb.fbtoast.FBToast;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.farshid.digikala.R;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class insert_post extends AppCompatActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {


    //   TextView
    @BindView(R.id.pageAdmin_InsertPost_Toolbar_text)
    TextView toolbar_text;
    @BindView(R.id.pageAdmin_InsertPost_Text_Insert)
    TextView TextInsert;
    @BindView(R.id.Activity_insert_imagetext)
    TextView Activity_insert_imagetext;
    @BindView(R.id.pageAdmin_InsertPost_Text_Gallery)
    TextView pageAdmin_InsertPost_Text_Gallery;

    // EditText
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_NameProduct)
    EditText NameProduct;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_EnglishNameProduct)
    EditText NameEnglishProduct;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_Price)
    EditText Price;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_PrePrice)
    EditText PrePrice;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_OS)
    EditText OS;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_Dimensions)
    EditText Dimensions;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_Sd)
    EditText Sd;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_battery)
    EditText Battery;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_Camera)
    EditText Camera;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_wight)
    EditText Wight;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_Processor)
    EditText Processor;
    @BindView(R.id.pageAdmin_InsertPost_Edit_Text_Description)
    EditText Description;

//       Spinner

    @BindView(R.id.pageAdmin_InsertPost_Spinner_kindProduct)
    BetterSpinner kindProduct;
    @BindView(R.id.pageAdmin_InsertPost_Spinner_Color)
    BetterSpinner Color;
    @BindView(R.id.pageAdmin_InsertPost_Spinner_guarantee)
    BetterSpinner Guarantee;
    @BindView(R.id.pageAdmin_InsertPost_Spinner_SimCard)
    BetterSpinner SimCard;

//ImageView

    @BindView(R.id.pageAdmin_InsertPost_Camera)
    ImageView insertImage;
    @BindView(R.id.pageAdmin_InsertPost_Image_Gallery)
    ImageView pageAdmin_InsertPost_Image_Gallery;

//    Button

    //    @BindView(R.id.pageAdmin_Button_InsertPost)
//    Button button_Insert_Post;

    @BindView(R.id.pageAdmin_Button_InsertPost)
    MyLoadingButton myLoadingButton;
    @BindView(R.id.pageAdmin_Button_InsertPost2)
    MyLoadingButton myLoadingButton2;
    @BindView(R.id.pageAdmin_Button_InsertPost_gallery)
    Button pageAdmin_Button_InsertPost_gallery;


    //    LinearLayout
    @BindView(R.id.pageAdmin_linear)
    LinearLayout pageAdmin_linear;
    @BindView(R.id.ActivityinsertPost_linearMain)
    LinearLayout ActivityinsertPost_linearMain;
    @BindView(R.id.linear_Gallery)
    LinearLayout linear_Gallery;


    Integer REQUEST_CAMERA = 1;
    Integer SELECT_FILE = 0;

    MultipartBody.Part fileToUpload;
    RequestBody filename;
    String Cat = "", Id = "";
    boolean flag = false, flag2 = false, flag_gallery = false;
    int count = 1, count2 = 1;
    private static final String TAG = upload.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://farshidhabibi.ir/farshid/diji/";
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_post);
        ButterKnife.bind(this);
//      مقدار دهی سپینر ها
        SetAdapter();
//        فونت
        set_font();
        TextInsert.setOnClickListener(this);
//        button_Insert_Post.setOnClickListener(this);
        myLoadingButton.setMyButtonClickListener(new MyLoadingButton.MyLoadingButtonClick() {
            @Override
            public void onMyLoadingButtonClick() {
                button_submit_info();
            }
        });
        myLoadingButton2.setMyButtonClickListener(new MyLoadingButton.MyLoadingButtonClick() {
            @Override
            public void onMyLoadingButtonClick() {
                button_submit_Image();
            }
        });
        kindProduct.setOnClickListener(this);
        insertImage.setOnClickListener(this);
        pageAdmin_InsertPost_Image_Gallery.setOnClickListener(this);
        pageAdmin_Button_InsertPost_gallery.setOnClickListener(this);


    }

    private void button_submit_Image() {
        myLoadingButton2.showLoadingButton();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);
        Call<UploadObject> fileUpload = uploadImage.uploadFile(fileToUpload, filename);
        fileUpload.enqueue(new Callback<UploadObject>() {
            @Override
            public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
//                        Toast.makeText(insert_post.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    if (flag_gallery) {

                        UploadPath2(Id, response.body().getSuccess());


                    } else {

                        UploadPath(Id, response.body().getSuccess());

                        myLoadingButton2.showDoneButton();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        YoYo.with(Techniques.SlideInDown).duration(2000).playOn(Activity_insert_imagetext);

                                    }
                                }, 2000);
                                Handler hand = new Handler();
                                hand.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        YoYo.with(Techniques.SlideInDown).duration(2000).playOn(insertImage);

                                    }
                                }, 2000);
                                Handler han = new Handler();
                                han.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        YoYo.with(Techniques.SlideInDown).duration(2000).playOn(myLoadingButton2);

                                    }
                                }, 2000);


                            }
                        }, 6000);
                        Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Activity_insert_imagetext.setVisibility(View.GONE);
                                insertImage.setVisibility(View.GONE);
                                myLoadingButton2.setVisibility(View.GONE);
                                linear_Gallery.setVisibility(View.VISIBLE);

                            }
                        }, 2000);

                    }

                }


            }

            @Override
            public void onFailure(Call<UploadObject> call, Throwable t) {
                myLoadingButton2.showErrorButton();
            }
        });

    }

    private void button_submit_Image2() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);
        Call<UploadObject> fileUpload = uploadImage.uploadFile2(fileToUpload, filename);
        fileUpload.enqueue(new Callback<UploadObject>() {
            @Override
            public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
//                        Toast.makeText(insert_post.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {

                    UploadPath2(Id, response.body().getSuccess());

                }


            }

            @Override
            public void onFailure(Call<UploadObject> call, Throwable t) {
            }
        });

    }

    private void button_submit_info() {
        myLoadingButton.showLoadingButton();
        ApiService apiService = RetroClass.getApiService();
        Call<String> call = apiService.givedata(NameProduct.getText().toString().trim(),
                NameEnglishProduct.getText().toString().trim(),
                Price.getText().toString(), PrePrice.getText().toString(), Color.getText().toString(), Guarantee.getText().toString(), OS.getText().toString()
                , Processor.getText().toString(), Dimensions.getText().toString(), Sd.getText().toString(), Battery.getText().toString()
                , Camera.getText().toString(), Wight.getText().toString(), SimCard.getText().toString(), Cat, "", Description.getText().toString()
        );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, final Response<String> response) {

                if (response.isSuccessful()) {
                    myLoadingButton.showDoneButton();
                    Id = response.body().toString();
                    YoYo.with(Techniques.SlideOutDown).duration(5000).playOn(ActivityinsertPost_linearMain);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Activity_insert_imagetext.setVisibility(View.VISIBLE);
                            insertImage.setVisibility(View.VISIBLE);
                            myLoadingButton2.setVisibility(View.VISIBLE);
                            YoYo.with(Techniques.Wobble).duration(2000).playOn(Activity_insert_imagetext);
                            YoYo.with(Techniques.Wave).duration(2000).playOn(insertImage);
                            YoYo.with(Techniques.Bounce).duration(2000).playOn(myLoadingButton2);
                            ActivityinsertPost_linearMain.setVisibility(View.GONE);
                        }
                    }, 5000);

                } else {
                    myLoadingButton.showErrorToNormalButton();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                myLoadingButton.showErrorButton();
            }
        });
//                }else {
//                    FBToast.nativeToast(insert_post.this,"لطفا تمامی فیلد ها را پرکنید!", FBToast.LENGTH_LONG);
//                }
    }

    private void set_font() {
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "Font/irsans.ttf");
        toolbar_text.setTypeface(tf2);
        TextInsert.setTypeface(tf2);
        NameProduct.setTypeface(tf2);
        NameEnglishProduct.setTypeface(tf2);
        PrePrice.setTypeface(tf2);
        Price.setTypeface(tf2);
        OS.setTypeface(tf2);
        Dimensions.setTypeface(tf2);
        Sd.setTypeface(tf2);
        Battery.setTypeface(tf2);
        Camera.setTypeface(tf2);
        Wight.setTypeface(tf2);
//        button_Insert_Post.setTypeface(tf2);
        Activity_insert_imagetext.setTypeface(tf2);
        Description.setTypeface(tf2);
    }

    private void SetAdapter() {

        String[] Item_KindClass = new String[]{"کامپیوتر ولپتاب", "گوشی موبایل"
                , "لوازم جانبی"
        };
        ArrayAdapter<String> adapter_Document_Student = new ArrayAdapter<String>(insert_post.this,
                android.R.layout.simple_dropdown_item_1line, Item_KindClass);
        kindProduct.setAdapter(adapter_Document_Student);


        String[] Item_Color = new String[]{"آبی", "قرمز"
                , "سبز", "بنفش", "نقره ای", "مشکی", "نفتی", "زرد"
        };
        ArrayAdapter<String> adapter_Color = new ArrayAdapter<String>(insert_post.this,
                android.R.layout.simple_dropdown_item_1line, Item_Color);
        Color.setAdapter(adapter_Color);


        String[] Item_guarantee = new String[]{"یک سال گارانتی", "دو سال گارانتی"
                , "سه سال گارانتی", "چهار سال گارانتی", "ندارد"
        };
        ArrayAdapter<String> adapter_guarantee = new ArrayAdapter<String>(insert_post.this,
                android.R.layout.simple_dropdown_item_1line, Item_guarantee);
        Guarantee.setAdapter(adapter_guarantee);


        String[] Item_SimCard = new String[]{"یک سیم کارت", "دوسیم کارت"
        };
        ArrayAdapter<String> adapter_SimCard = new ArrayAdapter<String>(insert_post.this,
                android.R.layout.simple_dropdown_item_1line, Item_SimCard);
        SimCard.setAdapter(adapter_SimCard);


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.pageAdmin_InsertPost_Text_Insert: {

                if (!flag) {
                    pageAdmin_linear.setVisibility(View.VISIBLE);
                    flag = true;
                } else {
                    pageAdmin_linear.setVisibility(View.GONE);
                    flag = false;
                }

                break;
            }
            case R.id.pageAdmin_InsertPost_Spinner_kindProduct: {

                if (count2 == 1) {
                    Handler handl = new Handler();
                    handl.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            YoYo.with(Techniques.SlideOutUp).duration(4000).playOn(NameProduct);
                            YoYo.with(Techniques.SlideOutLeft).duration(4000).playOn(NameEnglishProduct);
                            YoYo.with(Techniques.SlideOutRight).duration(4000).playOn(PrePrice);
                            YoYo.with(Techniques.SlideOutDown).duration(4000).playOn(Price);
                            YoYo.with(Techniques.FlipInX).duration(4000).playOn(ActivityinsertPost_linearMain);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    NameProduct.setVisibility(View.GONE);
                                    NameEnglishProduct.setVisibility(View.GONE);
                                    PrePrice.setVisibility(View.GONE);
                                    Price.setVisibility(View.GONE);
                                }
                            }, 2000);

                        }
                    }, 2000);

                    count2 = 3;
                }

                if (kindProduct.getText().toString().equals("کامپیوتر ولپتاب")) {
                    Cat = "7";
                    pageAdmin_linear.setVisibility(View.VISIBLE);
                    OS.setVisibility(View.GONE);
                    SimCard.setVisibility(View.GONE);
                    Sd.setVisibility(View.GONE);
                    Camera.setVisibility(View.GONE);
                    Processor.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInRight).duration(2000).playOn(pageAdmin_linear);
                }
                if (kindProduct.getText().toString().equals("گوشی موبایل")) {
                    Cat = "8";
                    OS.setVisibility(View.VISIBLE);
                    Processor.setVisibility(View.GONE);
                    SimCard.setVisibility(View.VISIBLE);
                    Sd.setVisibility(View.VISIBLE);
                    Camera.setVisibility(View.VISIBLE);
                    pageAdmin_linear.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInLeft).duration(2000).playOn(pageAdmin_linear);


                }
                if (kindProduct.getText().toString().equals("لوازم جانبی")) {
                    Cat = "9";
                    YoYo.with(Techniques.SlideOutUp).duration(2000).playOn(pageAdmin_linear);
                    pageAdmin_linear.setVisibility(View.GONE);
                    YoYo.with(Techniques.SlideOutUp).duration(2000).playOn(NameProduct);
                }

                break;
            }
            case R.id.pageAdmin_InsertPost_Image_Gallery: {
                flag_gallery = true;
                select_image();

                break;

            }
            case R.id.pageAdmin_InsertPost_Camera: {


                select_image();
                break;
            }
            case R.id.pageAdmin_Button_InsertPost_gallery: {
                if (count <= 3) {
                    pageAdmin_Button_InsertPost_gallery.setText("ثبت عکس " + count);
                    button_submit_Image2();
                } else if (count > 3) {
                    pageAdmin_Button_InsertPost_gallery.setText("با موفقیت ثبت شد!");

                }
                break;
            }


        }


    }


    private boolean Trust() {
        flag2 = true;
//
//        if (NameProduct.getText().toString().isEmpty() || NameEnglishProduct.getText().toString().isEmpty() || Price.getText().toString().isEmpty() ||
//               PrePrice.getText().toString().isEmpty() || OS.getText().toString().isEmpty() || Dimensions.getText().toString().isEmpty() ||
//                Sd.getText().toString().isEmpty() || Battery.getText().toString().isEmpty() || Camera.getText().toString().isEmpty() || Wight.getText().toString().isEmpty() ||
//                Processor.getText().toString().isEmpty() || Description.getText().toString().isEmpty() || kindProduct.getText().toString().equals("نوع محصول")
//                || Color.getText().toString().equals("رنگ") || Guarantee.getText().toString().equals("گارانتی")
//                || SimCard.getText().toString().equals("سیمکارت")) {
//            flag2 = false;
//
//
//        }
        return flag2;

    }

    private void UploadPath(String id, String s) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);
//        Toast.makeText(this, "Upload" + id + "Path" + s, Toast.LENGTH_SHORT).show();
        Call<String> fileUpload = uploadImage.Update(id, s);
        fileUpload.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.body().toString().equals("Ok")) {


//                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void UploadPath2(String id, String s) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);
//        Toast.makeText(this, "Upload" + id + "Path" + s, Toast.LENGTH_SHORT).show();
        Call<String> fileUpload = uploadImage.Update2(id, s);
        fileUpload.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(insert_post.this, "با موفقیت ثبت شد!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void select_image() {
        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setType("image/*");
        startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, insert_post.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, insert_post.this);
                File file = new File(filePath);

                Log.d(TAG, "Filename " + file.getName());
                //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                filename = RequestBody.create(MediaType.parse("text/plain"), file.getName() + "," + Id);
                if (file.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    insertImage.setImageBitmap(myBitmap);
                    if (flag_gallery) {
                        flag_gallery = false;
                        count++;
                        pageAdmin_InsertPost_Image_Gallery.setImageBitmap(myBitmap);

                    }
                }
            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (uri != null) {
            String filePath = getRealPathFromURIPath(uri, insert_post.this);
            File file = new File(filePath);
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }
}

