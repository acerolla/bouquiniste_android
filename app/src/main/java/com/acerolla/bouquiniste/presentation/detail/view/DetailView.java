package com.acerolla.bouquiniste.presentation.detail.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.squareup.picasso.Picasso;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DetailView extends RelativeLayout {

    private static final int ID_TOOLBAR = 1;

    private Toolbar mToolbar;
    private AppCompatImageView mIvFavorite;

    public DetailView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        setBackgroundColor(Color.WHITE);

        AppBarLayout appBarLayout = new AppBarLayout(getContext());
        appBarLayout.setId(ID_TOOLBAR);
        appBarLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        addView(appBarLayout);

        mToolbar = new Toolbar(getContext());
        mToolbar.setId(android.R.id.toggle);
        mToolbar.setBackgroundColor(Color.BLACK);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setSubtitleTextColor(Color.WHITE);
        mToolbar.setTitle(R.string.app_name);

        AppBarLayout.LayoutParams toolbarParams = new AppBarLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mToolbar.setLayoutParams(toolbarParams);
        appBarLayout.addView(mToolbar);

        mIvFavorite = new AppCompatImageView(getContext());
        mIvFavorite.setImageResource(R.drawable.ic_filter_list_white_24dp);
        mIvFavorite.setVisibility(GONE);

        Toolbar.LayoutParams categoryParams = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        categoryParams.gravity = Gravity.END;
        mIvFavorite.setLayoutParams(categoryParams);
        mToolbar.addView(mIvFavorite);
    }

    public void setFavoriteClickListener(OnClickListener listener) {
        mIvFavorite.setOnClickListener(listener);
    }

    public void setContentData(AdvertData data) {

    }

    public void setContentVisibility(int visibility) {

    }

    public void setLoaderVisibility(int visibility) {

    }

    public void setErrorVisibility(int visibility) {

    }

    public void setFavorite(boolean isFavorite) {
        if (isFavorite) {
            Picasso.get()
                    .load(R.drawable.ic_favorite_white_24dp)
                    .into(mIvFavorite);
        } else {
            Picasso.get()
                    .load(R.drawable.ic_unfavorite_white_24dp)
                    .into(mIvFavorite);
        }
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
