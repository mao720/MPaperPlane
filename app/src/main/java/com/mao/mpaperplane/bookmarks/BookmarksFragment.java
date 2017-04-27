package com.mao.mpaperplane.bookmarks;

import android.support.v4.app.Fragment;

/**
 * Created by maojunfeng on 2017/3/24/024.
 * Description:
 */

public class BookmarksFragment extends Fragment {
    private static BookmarksFragment bookmarksFragment;
    public static BookmarksFragment newInstance() {
        if (bookmarksFragment == null) {
            synchronized (BookmarksFragment.class) {
                if (bookmarksFragment == null) {
                    bookmarksFragment = new BookmarksFragment();
                }
            }
        }
        return bookmarksFragment;
    }
}
