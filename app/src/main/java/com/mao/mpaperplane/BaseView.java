package com.mao.mpaperplane;

import android.view.View;

/**
 * Description:所有View的基类
 * Created by MaoJunFeng on 2017/3/17.
 */

public interface BaseView<T> {
    //为View设置Presenter
    void setPresenter(T presenter);

    //初始化界面控件
    void initViews(View view);
}
