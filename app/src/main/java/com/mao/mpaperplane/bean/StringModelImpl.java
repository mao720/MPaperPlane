package com.mao.mpaperplane.bean;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mao.mpaperplane.app.VolleySingleton;
import com.mao.mpaperplane.interfaze.OnStringListener;

/**
 * Description:
 * Created by MaoJunFeng on 2017/3/18.
 */

public class StringModelImpl {
    private Context context;

    public StringModelImpl(Context context) {
        this.context = context;
    }

    public void load(String url, final OnStringListener listener) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getVolleySingleton(context).addToRequsetQueue(request);
    }
}
