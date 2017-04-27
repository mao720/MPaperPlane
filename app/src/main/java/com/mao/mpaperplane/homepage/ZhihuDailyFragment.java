package com.mao.mpaperplane.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mao.mpaperplane.R;
import com.mao.mpaperplane.adapter.ZhihuDailyNewsAdapter;
import com.mao.mpaperplane.bean.ZhihuDailyNews;
import com.mao.mpaperplane.interfaze.onRecyclerViewItemClickListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Description:
 * Created by MaoJunFeng on 2017/3/18.
 */

public class ZhihuDailyFragment extends Fragment implements ZhihuDailyContract.View {

    private ZhihuDailyContract.Presenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refresh;
    private FloatingActionButton fab;
    private TabLayout tabLayout;
    private int mYear = Calendar.getInstance().get(Calendar.YEAR);
    private int mMonth = Calendar.getInstance().get(Calendar.MONTH);
    private int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    private ZhihuDailyNewsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initViews(view);
        presenter.start();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //RecyclerView.SCROLL_STATE_SETTLING
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    Calendar now = Calendar.getInstance();
                    now.set(mYear, mMonth, mDay);
                    //showError();
                    //Toast.makeText(getContext(), mYear + "-" + mMonth + "-" + mDay, Toast.LENGTH_SHORT).show();
                    DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                            mYear = year;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;
                            Calendar temp = Calendar.getInstance();
                            temp.set(year, monthOfYear, dayOfMonth);
                            presenter.loadPosts(temp.getTimeInMillis(), true);
                        }
                    }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                    dialog.setMaxDate(Calendar.getInstance());
                    Calendar date = Calendar.getInstance();
                    date.set(2013, 4, 20);
                    dialog.setMinDate(date);
                    dialog.vibrate(true);
                    dialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
                } else if (tabLayout.getSelectedTabPosition() == 2) {

                }
            }
        });
        return view;
    }

    @Override
    public void showError() {
        Snackbar.make(fab, R.string.loaded_failed, BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.refresh();
                    }
                }).show();
    }

    @Override
    public void showLoading() {
        refresh.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        refresh.setRefreshing(false);
    }

    @Override
    public void showResults(ArrayList<ZhihuDailyNews.Story> list) {
        if (adapter == null) {
            adapter = new ZhihuDailyNewsAdapter(getContext(), list);
            adapter.setRecyclerViewItemClickListener(new onRecyclerViewItemClickListener(){
                @Override
                public void onItemClick(View v, int layoutPosition) {
                    presenter.startReading(layoutPosition);
                }
            });
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showPickDialog() {

    }

    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refresh.setColorSchemeResources(R.color.colorPrimary);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setRippleColor(ContextCompat.getColor(getActivity(),R.color.colorPrimaryDark));
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
    }

    public static ZhihuDailyFragment newInstance() {
        return new ZhihuDailyFragment();
    }
}
