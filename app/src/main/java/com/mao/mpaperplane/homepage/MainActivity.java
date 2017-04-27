package com.mao.mpaperplane.homepage;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.mao.mpaperplane.R;
import com.mao.mpaperplane.bookmarks.BookmarksFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private MainFragment mainFragment;
    private BookmarksFragment bookmarksFragment;
    public static final String ACTION_BOOKMARKS = "com.marktony.zhihudaily.bookmarks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        if (savedInstanceState != null) {
            mainFragment = (MainFragment) getSupportFragmentManager().getFragment(savedInstanceState, "MainFragment");
            bookmarksFragment = (BookmarksFragment) getSupportFragmentManager().getFragment(savedInstanceState, "BookmarksFragment");
        } else {
            mainFragment = MainFragment.newInstance();
            bookmarksFragment = BookmarksFragment.newInstance();
        }

        if (!mainFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_fragment, mainFragment, "MainFragment")
                    .commit();
        }
        if (!bookmarksFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_fragment, bookmarksFragment, "BookmarksFragment")
                    .commit();
        }

        String action = getIntent().getAction();

        if (action.equals(ACTION_BOOKMARKS)) {
            showBookmarksFragment();
            navigationView.setCheckedItem(R.id.nav_bookmarks);
        } else {
            showMainFragment();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(Gravity.START);
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                showMainFragment();
                break;
            case R.id.nav_bookmarks:
                showBookmarksFragment();
                break;
            case R.id.nav_change_theme:
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_about:
                break;
        }
        return true;
    }

    private void showBookmarksFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(bookmarksFragment);
        transaction.hide(mainFragment);
        transaction.commit();

        toolbar.setTitle(R.string.nav_bookmarks);

    }

    private void showMainFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(mainFragment);
        transaction.hide(bookmarksFragment);
        transaction.commit();

        toolbar.setTitle(R.string.app_name);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mainFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "MainFragment", mainFragment);
        }
        if (bookmarksFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "BookmarksFragment", bookmarksFragment);
        }
    }
}
