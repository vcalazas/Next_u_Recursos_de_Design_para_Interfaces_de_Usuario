package com.vcalazas.formasgeomtricas.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.TransitionManager;
import com.vcalazas.formasgeomtricas.R;
import com.vcalazas.formasgeomtricas.fragments.FormsFragment;
import com.vcalazas.formasgeomtricas.fragments.HomeFragment;
import com.vcalazas.formasgeomtricas.interfaces.OnListener;
import com.vcalazas.formasgeomtricas.utils.Constants;
import com.vcalazas.formasgeomtricas.utils.Logger;

public class MyFragmentManager {

    private AppCompatActivity appCompatActivity;
    private Context context;
    private OnListener onListener;
    private LinearLayout mainActMainLayout;

    public MyFragmentManager(AppCompatActivity appCompatActivity, Context context, LinearLayout main) {
        this.appCompatActivity = appCompatActivity;
        this.context = context;
        this.mainActMainLayout = main;
    }

    private void startListener() {
        MyFragmentManager.this.onListener = new OnListener() {

            @Override
            public <T> void saveSession(Context context, String field, T data) {

            }

            @Override
            public void onCall(int frameLayout, int option,Bundle arguments) {
                try {
                    MyFragmentManager.this.navigator(frameLayout ,option, arguments);
                } catch (Exception e) {
                    Logger.write("MyFragmentManager.startListener // new OnListener().onCall - ", Constants.Verbose.ERROR);
                }
            }
        };
    }

    public void navigator( int way, Bundle arguments){
        MyFragmentManager.this.navigator(R.id.fragment_content, way, arguments);
    }

    private void navigator(int frameLayout , int way, Bundle arguments){
        try {

            switch (way) {
                case Constants.NameOfFragments.HOME:
                    MyFragmentManager.this.goHome(frameLayout, arguments);
                    break;

                case Constants.NameOfFragments.FORMS:
                    MyFragmentManager.this.goForms(frameLayout, arguments);
                    break;
            }
        } catch (Exception e){

        }
    }

    private void navigationFragment(int frameLayout, Fragment fragment, String title) {
        try {
            MyFragmentManager.this.appCompatActivity.setTitle(title);
            android.support.v4.app.FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(frameLayout, fragment);
            fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Logger.write("MyFragmentManager.navigationFragment - ", Constants.Verbose.ERROR);
        }
    }

    private void goHome(int frameLayout, Bundle arguments){
        try {
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setListener(MyFragmentManager.this.onListener);
            if( arguments != null ){
                homeFragment.setArguments(arguments);
            }
            MyFragmentManager.this.navigationFragment(frameLayout , homeFragment, context.getString(R.string.app_name));
            TransitionManager.beginDelayedTransition(mainActMainLayout, new Rotate());
        } catch (Exception e){

        }
    }

    private void goForms(int frameLayout, Bundle arguments){
        try {
            FormsFragment formsFragment = new FormsFragment();
            formsFragment.setListener(MyFragmentManager.this.onListener);
            if( arguments != null ){
                formsFragment.setArguments(arguments);
                MyFragmentManager.this.navigationFragment(frameLayout , formsFragment, Constants.NameOfForms.getName(context, arguments.getInt(Constants.NameOfForms.BUNDLE_FORMS)));
                Toast.makeText(appCompatActivity, appCompatActivity.getString(R.string.text5,
                        Constants.NameOfForms.getName(context, arguments.getInt(Constants.NameOfForms.BUNDLE_FORMS)) ), Toast.LENGTH_SHORT).show();
            } else {
                MyFragmentManager.this.navigationFragment(frameLayout , formsFragment, context.getString(R.string.app_name));
            }TransitionManager.beginDelayedTransition(mainActMainLayout, new Rotate());
        } catch (Exception e){

        }
    }
}
