package com.vcalazas.formasgeomtricas.utils;

import android.content.Context;

import com.vcalazas.formasgeomtricas.R;

public class Constants {

    public static class NameOfFragments{
        public static final int HOME = 0;
        public static final int FORMS = 1;
    }

    public static class NameOfForms {
        public static final String BUNDLE_FORMS   = "bundleForms";

        public static final int CIRCLE      = 0;
        public static final int OVAL        = 1;
        public static final int SQUARE      = 2;
        public static final int RECTANGULE  = 3;

        public static String getName(Context context, int form){
            String name = "";
            switch (form){
                case Constants.NameOfForms.CIRCLE:
                    name = context.getString(R.string.circulo);
                    break;
                case Constants.NameOfForms.OVAL:
                    name = context.getString(R.string.oval);
                    break;
                case Constants.NameOfForms.SQUARE:
                    name = context.getString(R.string.quadrado);
                    break;
                case Constants.NameOfForms.RECTANGULE:
                    name = context.getString(R.string.retangulo);
                    break;
            }
            return name;
        };
    }

    public static class Verbose{
        public static final String ERROR = "Error";
    }
}
