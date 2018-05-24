package com.acerolla.bouquiniste.presentation.utils;

import android.content.Context;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class ImageLoader {

    public static void showAvatar(ImageView view, String name) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(name.substring(0, 1).toUpperCase(), ColorGenerator.MATERIAL.getColor(name));
        view.setImageDrawable(drawable);

    }
}
