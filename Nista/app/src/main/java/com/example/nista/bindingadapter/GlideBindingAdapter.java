package com.example.nista.bindingadapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class GlideBindingAdapter {

    private static final String imageBaseUrl = "https://image.tmdb.org/t/p/w185";

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(imageBaseUrl+url).into(view);
    }
}
