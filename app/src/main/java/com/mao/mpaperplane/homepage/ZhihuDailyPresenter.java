package com.mao.mpaperplane.homepage;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mao.mpaperplane.bean.StringModelImpl;
import com.mao.mpaperplane.bean.ZhihuDailyNews;
import com.mao.mpaperplane.interfaze.OnStringListener;
import com.mao.mpaperplane.util.Api;
import com.mao.mpaperplane.util.DateFormatter;
import com.mao.mpaperplane.util.NetworkState;

import java.util.Calendar;

/**
 * Description:
 * Created by MaoJunFeng on 2017/3/18.
 */

public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {

    private final FragmentActivity context;
    private final ZhihuDailyContract.View view;
    private final StringModelImpl model;

    public ZhihuDailyPresenter(FragmentActivity context, ZhihuDailyContract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
        model = new StringModelImpl(context);

    }

    @Override
    public void loadPosts(long date, boolean clearing) {
        if (clearing) {
            view.showLoading();
        }
        if (NetworkState.networkConnected(context)) {
            model.load(Api.ZHIHU_HISTORY + DateFormatter.zhihuDailyDateFormat(date), new OnStringListener() {
                @Override
                public void onSuccess(String result) {
                    try {
                        Log.e("MMM", "onSuccess: " + result);
                        ZhihuDailyNews news = new Gson().fromJson(result, ZhihuDailyNews.class);
                        view.showResults(news.stories);
                    } catch (JsonSyntaxException e) {
                        view.showError();
                    }

                    view.stopLoading();
                }

                @Override
                public void onError(VolleyError error) {
                    view.stopLoading();
                    view.showError();
                }
            });
        } else {
            // TODO: 2017/4/21/021 database
        }
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore(long date) {

    }

    @Override
    public void startReading(int position) {
        context.startActivity(new Intent());
        // TODO: 2017/4/24/024 detail activityu
    }

    @Override
    public void feelLucky() {

    }

    @Override
    public void start() {
        loadPosts(Calendar.getInstance().getTimeInMillis(), true);
    }
}
