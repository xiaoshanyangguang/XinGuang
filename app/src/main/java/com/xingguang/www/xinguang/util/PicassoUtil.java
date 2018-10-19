package com.xingguang.www.xinguang.util;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * @创建者 pengbo
 * @创建时间 2018/10/17 11:43
 * @描述 TODO
 */
public class PicassoUtil {
    public static final String TAG = "PicassoUtil";

    public static Picasso getPicasso() {
        Picasso.get().setLoggingEnabled(true);
//        Picasso.with(BaseAppliCation.getInstance()).setLoggingEnabled(true);
        return Picasso.get();
//        return  Picasso.with(BaseAppliCation.getInstance());
    }

    public static void showFilePicture(String filePath, int drawableIdIError, ImageView view) {
        getPicasso().load("file://" + filePath).fit().error(drawableIdIError).into(view);
    }

    public static void showDrawablePicture(int drawableId, int drawableIdIError, ImageView view) {
        getPicasso().load(drawableId).error(drawableIdIError).into(view);
//        view.setImageResource(drawableId);
    }

}
