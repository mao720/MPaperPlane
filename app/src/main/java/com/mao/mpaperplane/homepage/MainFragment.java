package com.mao.mpaperplane.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mao.mpaperplane.R;
import com.mao.mpaperplane.adapter.MainPagerAdapter;

import java.util.Random;

/**
 * Created by maojunfeng on 2017/3/24/024.
 * Description:
 */

public class MainFragment extends android.support.v4.app.Fragment {
    private static MainFragment mainFragment;
    private FragmentActivity context;
    private ZhihuDailyFragment zhihuDailyFragment;
    private GuokrFragment guokrFragment;
    private DoubanMomentFragment doubanMomentFragment;
    private TabLayout tabLayout;
    private MainPagerAdapter adapter;
    private ZhihuDailyPresenter zhihuDailyPresenter;

    public static MainFragment newInstance() {
        if (mainFragment == null) {
            synchronized (MainFragment.class) {
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                }
            }
        }
        return mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        if (savedInstanceState != null) {
            FragmentManager manager = getChildFragmentManager();
            zhihuDailyFragment = (ZhihuDailyFragment) manager.getFragment(savedInstanceState, "zhihu");
            guokrFragment = (GuokrFragment) manager.getFragment(savedInstanceState, "guokr");
            doubanMomentFragment = (DoubanMomentFragment) manager.getFragment(savedInstanceState, "douban");
        } else {
            zhihuDailyFragment = ZhihuDailyFragment.newInstance();
            guokrFragment = GuokrFragment.newInstance();
            doubanMomentFragment = DoubanMomentFragment.newInstance();
        }
        zhihuDailyPresenter = new ZhihuDailyPresenter(context, zhihuDailyFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view);
        setHasOptionsMenu(true);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FloatingActionButton floatingActionButton = (FloatingActionButton) context.findViewById(R.id.fab);
                if (tab.getPosition() == 1) {
                    floatingActionButton.hide();
                } else {
                    floatingActionButton.show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private void initViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        adapter = new MainPagerAdapter(getChildFragmentManager(),
                context,
                zhihuDailyFragment,
                guokrFragment,
                doubanMomentFragment);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.action_feel_lucky) {
            feelLucky();
        }
        return true;
    }

    private void feelLucky() {
        int type = new Random().nextInt(3);
        switch (type) {
            case 0:
                zhihuDailyPresenter.feelLucky();
                break;
            case 1:
                //guokrFragment.feelLucky();
                break;
            default:
                //doubanMomentFragment.feelLucky();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getChildFragmentManager();
        manager.putFragment(outState,"zhihu",zhihuDailyFragment);
        manager.putFragment(outState,"guokr",guokrFragment);
        manager.putFragment(outState,"douban",doubanMomentFragment);
    }
}
