package com.vcalazas.formasgeomtricas.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;

import com.vcalazas.formasgeomtricas.R;
import com.vcalazas.formasgeomtricas.utils.Constants;

public class NavBar implements NavigationView.OnNavigationItemSelectedListener {

    private Activity activity;
    private Context context;
    private View view;
    private MyFragmentManager myFragmentManager;

    public NavBar(Context c, View v, MyFragmentManager mfm, Activity act) {
        NavBar.this.context = c;
        NavBar.this.view = v;
        NavBar.this.myFragmentManager = mfm;
        NavBar.this.activity = act;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_circle) {
            NavBar.this.goForms(Constants.NameOfForms.CIRCLE);
        } else if (id == R.id.nav_oval) {
            NavBar.this.goForms(Constants.NameOfForms.OVAL);
        } else if (id == R.id.nav_square) {
            NavBar.this.goForms(Constants.NameOfForms.SQUARE);
        } else if (id == R.id.nav_retangule) {
            NavBar.this.goForms(Constants.NameOfForms.RECTANGULE);
        }

//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) NavBar.this.view.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void goForms(int form){
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.NameOfForms.BUNDLE_FORMS, form);
        NavBar.this.myFragmentManager.navigator(Constants.NameOfFragments.FORMS, bundle);
    }

    public boolean onOptionsItemSelected(MenuItem item, View view) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_share){
            listaCheck(activity, view).show();
        }
        return false;
    }

    public static AlertDialog listaCheck(final Activity activity, final View view){
        final String[] items        = {"Facebook",  "Twitter", "Instagram", "Google Plus", "Whatsapp","Messager","SMS"};
        final boolean[] checkedItem = {false,       false,      false,      false,          false,      false,      false};
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.TemaDialogoRadioCheck);

        builder.setTitle(R.string.text2);

        builder.setMultiChoiceItems(items, checkedItem, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            }
        });

        builder.setPositiveButton(R.string.compartilhar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selection = "";
                for (int i=0; i<checkedItem.length; i++){
                    if (checkedItem[i]) {
                        selection += items[i]+",";
                    }
                }
                dialog.dismiss();
                confirm(activity, view, selection).show();
//                Snackbar.make(view, "Seleção: "+selection, Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }

    public static AlertDialog confirm(final Activity activity, final View view, final String selection){
        final String[] items        = {"Facebook",  "Twitter", "Instagram", "Google Plus", "Whatsapp","Messager","SMS"};
        final boolean[] checkedItem = {false,       false,      false,      false,          false,      false,      false};
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.TemaDialogoRadioCheck);

        builder.setTitle(R.string.text2);
        builder.setMessage(R.string.text3);

        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar.make(view, activity.getString(R.string.text4, selection), Snackbar.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}
