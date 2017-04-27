package com.mao.mpaperplane;

/**
 * Description:
 * Created by MaoJunFeng on 2017/3/18.
 */

public interface BasePresenter {
    //获取数据并改变界面显示，在todo-mvp中的调用时机为Fragment的OnResume（）方法中
    void start();
}
