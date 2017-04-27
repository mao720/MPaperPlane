package com.mao.mpaperplane.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by maojunfeng on 2017/3/25/025.
 * Description:
 */

public class DoubanMomentFragment extends Fragment {

    public static DoubanMomentFragment newInstance() {
        return new DoubanMomentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new View(getActivity());
    }

    public void feelLucky() {

    }
}
