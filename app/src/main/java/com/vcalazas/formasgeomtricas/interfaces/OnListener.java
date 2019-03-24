package com.vcalazas.formasgeomtricas.interfaces;

import android.content.Context;
import android.os.Bundle;

public interface OnListener {

    <T> void saveSession(Context context, String field, T data);
    void onCall(int frameLayout, int option, Bundle arguments);

}
