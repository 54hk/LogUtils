package com.page.android.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.page.android.R;

/**
 * Created by 54hk on 2019/3/5.
 * //加载图片的工具类
 */

public class GlideUtils {
    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);

    }
}
