package com.training.testapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Util {

    private static final int IMAGE_HEIGHT = 300;
    private static final int IMAGE_WIDTH = 300;

    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Picasso.with(context).load(imageUrl).resize(IMAGE_WIDTH, IMAGE_HEIGHT).into(imageView);
    }

    public static void loadImageWithCustomResize(Context context, String imageUrl, ImageView imageView, int width, int height) {
        Picasso.with(context).load(imageUrl).resize(width, height).into(imageView);
    }
}
