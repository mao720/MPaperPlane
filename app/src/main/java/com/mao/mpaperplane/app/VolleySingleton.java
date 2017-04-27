package com.mao.mpaperplane.app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Description:
 * Created by MaoJunFeng on 2017/3/18.
 */

public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    public static VolleySingleton getVolleySingleton(Context context) {
        if (volleySingleton == null) {
            synchronized (VolleySingleton.class) {
                if (volleySingleton == null) {
                    volleySingleton = new VolleySingleton(context);
                }
            }
        }
        return volleySingleton;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequsetQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
