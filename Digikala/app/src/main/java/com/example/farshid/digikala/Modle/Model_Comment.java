package com.example.farshid.digikala.Modle;

import android.content.Context;

import java.util.ArrayList;

public class Model_Comment {

    private   ArrayList<String> bu;
    private ArrayList<Integer> in;
    private     int Count;
    Context context;


    public Model_Comment(Context co, int count, ArrayList<String> bu, ArrayList<Integer> in) {
        Count = count;
        this.bu = bu;
        this.in = in;
        this.context=co;
    }
    public Model_Comment() {

    }



    public void setCount(int count) {
        Count = count;
    }

    public void setBu(ArrayList<String> bu) {
        this.bu = bu;
    }

    public void setIn(ArrayList<Integer> in) {
        this.in = in;
    }



    public int getCount() {
        return Count;
    }

    public ArrayList<String> getBu() {
        return bu;
    }

    public ArrayList<Integer> getIn() {
        return in;
    }



}
