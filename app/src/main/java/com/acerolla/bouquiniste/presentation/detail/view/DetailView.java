package com.acerolla.bouquiniste.presentation.detail.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DetailView extends ScrollView {

    private ImageView mIvImage;
    private TextView mTvTitle;
    private TextView mTvAuthor;
    private TextView mTvDescription;
    private TextView mTvPrice;
    private TextView mTvPhone;
    private TextView mTvLocation;
    private TextView mTvCategory;
    private Toolbar mToolbar;

    public DetailView(Context context) {
        super(context);
        initViews();
    }

    public DetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DetailView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @SuppressLint("RestrictedApi")
    public void initViews() {
        mToolbar = findViewById(R.id.toolbar_actionbar);
        mToolbar.inflateMenu(R.menu.menu_detail);

        mIvImage = findViewById(R.id.iv_image);
        mTvTitle = findViewById(R.id.tv_title);
        mTvAuthor = findViewById(R.id.tv_author);
        mTvDescription = findViewById(R.id.tv_description);
        mTvPrice = findViewById(R.id.tv_price);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvLocation = findViewById(R.id.tv_location);
        mTvCategory = findViewById(R.id.tv_category);
    }

    public void setFavoriteClickListener(OnClickListener listener) {

    }

    public void setContentData(AdvertData data) {

    }

    public void setContentVisibility(int visibility) {

    }

    public void setLoaderVisibility(int visibility) {

    }

    public void setErrorVisibility(int visibility) {

    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
