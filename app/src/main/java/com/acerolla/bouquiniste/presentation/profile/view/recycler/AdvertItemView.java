package com.acerolla.bouquiniste.presentation.profile.view.recycler;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;
import com.squareup.picasso.Picasso;

import java.util.Locale;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertItemView extends RelativeLayout {

    private AppCompatImageView mIvImage;
    private AppCompatTextView mTvTitle;
    private AppCompatTextView mTvAuthor;
    private AppCompatTextView mTvCategory;
    private AppCompatTextView mTvPrice;
    private AppCompatTextView mTvLocation;

    public AdvertItemView(Context context) {
        super(context);
    }

    public AdvertItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdvertItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AdvertItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initViews() {
        mIvImage = findViewById(R.id.iv_advert_image);
        mTvTitle = findViewById(R.id.tv_title);
        mTvAuthor = findViewById(R.id.tv_author);
        mTvCategory = findViewById(R.id.tv_category);
        mTvPrice = findViewById(R.id.tv_price);
        mTvLocation = findViewById(R.id.tv_location);
    }

    public void loadImage(String url) {
        if (url != null && !url.isEmpty()) {
            Picasso.get()
                    .load(url)
                    .resize(ValuesConverter.dp2px(ValuesConverter.DP_150), ValuesConverter.dp2px(ValuesConverter.DP_150))
                    .centerCrop()
                    .into(mIvImage);
        } else {
            Picasso.get()
                    .load(R.mipmap.ic_camera)
                    .resize(ValuesConverter.dp2px(ValuesConverter.DP_150), ValuesConverter.dp2px(ValuesConverter.DP_150))
                    .centerCrop()
                    .into(mIvImage);
        }
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setAuthor(String author) {
        mTvAuthor. setText(author);
    }

    public void setCategory(String categoryName) {
        mTvCategory.setText(categoryName);
    }

    public void setPrice(float price) {
        mTvPrice.setText(String.format(Locale.getDefault(), "%.2f \u20BD", price));
    }

    public void setLocation(String location) {
        mTvLocation.setText(location);
    }

}
