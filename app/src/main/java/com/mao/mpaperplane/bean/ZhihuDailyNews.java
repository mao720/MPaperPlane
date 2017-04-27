package com.mao.mpaperplane.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by MaoJunFeng on 2017/3/18.
 */

public class ZhihuDailyNews {

    /**
     * date : 20170121
     * stories : [{"images":["http://pic1.zhimg.com/ffcca2b2853f2af791310e6a6d694e80.jpg"],"type":0,"id":9165434,"ga_prefix":"012121","title":"谁说普通人的生活就不能精彩有趣呢？"}]
     */

    public String date;
    public ArrayList<Story> stories;

    public static class Story {
        /**
         * images : ["http://pic1.zhimg.com/ffcca2b2853f2af791310e6a6d694e80.jpg"]
         * type : 0
         * id : 9165434
         * ga_prefix : 012121
         * title : 谁说普通人的生活就不能精彩有趣呢？
         */

        public int type;
        public int id;
        public String ga_prefix;
        public String title;
        public List<String> images;
    }
}
