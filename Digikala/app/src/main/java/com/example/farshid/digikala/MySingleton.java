package com.example.farshid.digikala;

/**
 * Created by farshid on 10/30/2018.
 */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class MySingleton {
    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private static Context Mycontext;

    private MySingleton(Context context)
    {
        this.Mycontext=context;
        this.requestQueue=getRequestQueue();

    }
    public static synchronized MySingleton getInstance(Context context)
    {
        if(mySingleton==null)
        {
            mySingleton=new MySingleton(context);
        }
        return  mySingleton;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null)
        {
            requestQueue= Volley.newRequestQueue(Mycontext.getApplicationContext());
        }
        return  requestQueue;
    }

    public <T> void addtoRequestQueue (Request<T> request)
    {

        requestQueue.add(request);
    }


}
