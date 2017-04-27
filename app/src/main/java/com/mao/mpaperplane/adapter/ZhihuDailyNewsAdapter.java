package com.mao.mpaperplane.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mao.mpaperplane.R;
import com.mao.mpaperplane.bean.ZhihuDailyNews;
import com.mao.mpaperplane.interfaze.onRecyclerViewItemClickListener;
import com.mao.mpaperplane.util.DensityUtils;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by maojunfeng on 2017/3/28/028.
 * Description:
 */

public class ZhihuDailyNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private LayoutInflater inflater;
    private final ArrayList<ZhihuDailyNews.Story> list;
    private onRecyclerViewItemClickListener clickListener;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = 1;


    public ZhihuDailyNewsAdapter(Context context, ArrayList<ZhihuDailyNews.Story> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new NormalViewHolder(inflater.inflate(R.layout.item_list_news, parent, false), clickListener);
            case 1:
                return new FooterViewHolder(inflater.inflate(R.layout.item_list_footer, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) return;
        final NormalViewHolder viewHolder = (NormalViewHolder) holder;
        viewHolder.tvTitle.setText(list.get(position).title);
        //使用Glide获取网络图片的bitmap
        if (list.get(position).images.get(0) == null) {
            //设置无网络时的默认图片
            viewHolder.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    ContextCompat.getDrawable(context, R.drawable.ic_search_circle), null);
            return;
        }
        Glide.with(context)
                .load(list.get(position).images.get(0))
                .asBitmap()
                .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_search_circle))
                .error(ContextCompat.getDrawable(context, R.drawable.ic_search_circle))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>(DensityUtils.dip2px(context, 80), DensityUtils.dip2px(context, 80)) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable drawable = new BitmapDrawable(context.getResources(), resource);
                        Log.e("MMM", "onResourceReady: get bitmap");
                        viewHolder.tvTitle.setCompoundDrawables(null, null, drawable, null);
//                viewHolder.tvTitle.setBackgroundDrawable(new BitmapDrawable(resource));
                        //viewHolder.ivPicture.setImageBitmap(resource);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == list.size() ? TYPE_FOOTER : TYPE_NORMAL;
    }

    public void setRecyclerViewItemClickListener(onRecyclerViewItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvTitle;
        private final onRecyclerViewItemClickListener clickListener;

        public NormalViewHolder(View itemView, onRecyclerViewItemClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getLayoutPosition());
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
