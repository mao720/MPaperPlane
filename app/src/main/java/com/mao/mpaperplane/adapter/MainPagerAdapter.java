package com.mao.mpaperplane.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.mao.mpaperplane.R;
import com.mao.mpaperplane.homepage.DoubanMomentFragment;
import com.mao.mpaperplane.homepage.GuokrFragment;
import com.mao.mpaperplane.homepage.ZhihuDailyFragment;

/**
 * Created by maojunfeng on 2017/3/27/027.
 * Description:
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private final ZhihuDailyFragment zhihuDailyFragment;
    private final GuokrFragment guokrFragment;
    private final DoubanMomentFragment doubanMomentFragment;
    private Context context;
    private String[] title;

    public MainPagerAdapter(FragmentManager childFragmentManager,
                            FragmentActivity context,
                            ZhihuDailyFragment zhihuDailyFragment,
                            GuokrFragment guokrFragment,
                            DoubanMomentFragment doubanMomentFragment) {
        super(childFragmentManager);
        this.context = context;
        title = new String[]{context.getResources().getString(R.string.zhihu_daily),
                context.getResources().getString(R.string.guokr_handpick),
                context.getResources().getString(R.string.douban_moment)
        };
        this.zhihuDailyFragment = zhihuDailyFragment;
        this.guokrFragment = guokrFragment;
        this.doubanMomentFragment = doubanMomentFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return guokrFragment;
            case 2:
                return doubanMomentFragment;
            default:
                return zhihuDailyFragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
