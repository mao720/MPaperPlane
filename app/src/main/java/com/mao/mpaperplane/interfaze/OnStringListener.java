package com.mao.mpaperplane.interfaze;

import com.android.volley.VolleyError;

/**
 * Description:
 * Created by MaoJunFeng on 2017/3/18.
 */

public interface OnStringListener {
    /**
     * 请求成功时回调
     * @param result
     */
    void onSuccess(String result);

    /**
     * 请求失败时回调
     * @param error
     */
    void onError(VolleyError error);
}
