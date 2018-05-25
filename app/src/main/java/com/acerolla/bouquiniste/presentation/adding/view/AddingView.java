package com.acerolla.bouquiniste.presentation.adding.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;

import static android.widget.RelativeLayout.BELOW;
import static android.widget.RelativeLayout.CENTER_HORIZONTAL;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingView extends ScrollView {

    public static final int ID_BUTTON_POST = 1;
    public static final int ID_IMAGE = 2;
    public static final int ID_TITLE = 3;
    public static final int ID_AUTHOR = 4;
    public static final int ID_DESCRIPTION = 5;
    public static final int ID_PRICE = 6;
    public static final int ID_PHONE = 7;

    private static final int ZERO = 0;

    private AppCompatImageView mIvImage;
    private AppCompatEditText mTvTitle;
    private AppCompatEditText mTvAuthor;
    private AppCompatEditText mTvDescription;
    private AppCompatEditText mTvPrice;
    private AppCompatEditText mTvPhone;
    private AppCompatButton mBtnPost;

    private String imagePath;

    public AddingView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        setClickable(true);
        setBackgroundColor(Color.WHITE);

        RelativeLayout contentRoot = new RelativeLayout(getContext());
        contentRoot.setBackgroundColor(Color.WHITE);

        LayoutParams rootParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        contentRoot.setLayoutParams(rootParams);
        addView(contentRoot);

        mIvImage = new AppCompatImageView(getContext());
        mIvImage.setId(ID_IMAGE);
        mIvImage.setImageResource(R.mipmap.ic_camera);

        LayoutParams imageParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ValuesConverter.dp2px(ValuesConverter.DP_200));
        mIvImage.setLayoutParams(imageParams);
        contentRoot.addView(mIvImage);

        mTvTitle = new AppCompatEditText(getContext());
        mTvTitle.setId(ID_TITLE);
        mTvTitle.setHint(R.string.adding_hint_title);
        mTvTitle.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        int paddingLeftRight = ValuesConverter.dp2px(ValuesConverter.DP_5);
        mTvTitle.setPadding(paddingLeftRight, ZERO, paddingLeftRight, ZERO);

        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(BELOW, ID_IMAGE);
        int topMargin = ValuesConverter.dp2px(ValuesConverter.DP_10);
        titleParams.topMargin = topMargin;
        mTvTitle.setLayoutParams(titleParams);
        contentRoot.addView(mTvTitle);

        mTvAuthor = new AppCompatEditText(getContext());
        mTvAuthor.setId(ID_AUTHOR);
        mTvAuthor.setHint(R.string.adding_hint_author);
        mTvAuthor.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        mTvAuthor.setPadding(paddingLeftRight, ZERO, paddingLeftRight, ZERO);

        RelativeLayout.LayoutParams authorParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        authorParams.addRule(BELOW, ID_TITLE);
        authorParams.topMargin = topMargin;
        mTvAuthor.setLayoutParams(authorParams);
        contentRoot.addView(mTvAuthor);

        mTvDescription = new AppCompatEditText(getContext());
        mTvDescription.setId(ID_DESCRIPTION);
        mTvDescription.setHint(R.string.adding_hint_description);
        mTvDescription.setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
        mTvDescription.setPadding(paddingLeftRight, ZERO, paddingLeftRight, ZERO);

        RelativeLayout.LayoutParams descriptionParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ValuesConverter.dp2px(ValuesConverter.DP_100));
        descriptionParams.addRule(BELOW, ID_AUTHOR);
        descriptionParams.topMargin = topMargin;
        mTvDescription.setLayoutParams(descriptionParams);
        contentRoot.addView(mTvDescription);

        mTvPrice = new AppCompatEditText(getContext());
        mTvPrice.setId(ID_PRICE);
        mTvPrice.setHint(R.string.adding_hint_price);
        mTvPrice.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        mTvPrice.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        mTvPrice.setPadding(paddingLeftRight, ZERO, paddingLeftRight, ZERO);

        RelativeLayout.LayoutParams priceParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ValuesConverter.dp2px(ValuesConverter.DP_100));
        priceParams.addRule(BELOW, ID_DESCRIPTION);
        priceParams.topMargin = topMargin;
        mTvPrice.setLayoutParams(priceParams);
        contentRoot.addView(mTvPrice);

        mTvPhone = new AppCompatEditText(getContext());
        mTvPhone.setId(ID_PHONE);
        mTvPhone.setHint(R.string.adding_hint_price);
        mTvPhone.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        mTvPhone.setInputType(InputType.TYPE_CLASS_PHONE);
        mTvPhone.setPadding(paddingLeftRight, ZERO, paddingLeftRight, ZERO);

        RelativeLayout.LayoutParams phoneParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ValuesConverter.dp2px(ValuesConverter.DP_100));
        phoneParams.addRule(BELOW, ID_PRICE);
        phoneParams.topMargin = topMargin;
        mTvPhone.setLayoutParams(phoneParams);
        contentRoot.addView(mTvPhone);

        mBtnPost = new AppCompatButton(getContext());
        mBtnPost.setId(ID_BUTTON_POST);
        mBtnPost.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        mBtnPost.setText(R.string.adding_button_add);
        mBtnPost.setTextColor(Color.WHITE);

        RelativeLayout.LayoutParams uploadParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        uploadParams.addRule(BELOW, ID_PHONE);
        uploadParams.addRule(CENTER_HORIZONTAL);
        phoneParams.topMargin = ValuesConverter.dp2px(ValuesConverter.DP_25);
        mBtnPost.setLayoutParams(uploadParams);
        contentRoot.addView(mBtnPost);
    }

    public void setAddClickListener(OnClickListener listener) {
        mBtnPost.setOnClickListener(listener);
    }

    public void setImageClickListener(OnClickListener listener) {
        mIvImage.setOnClickListener(listener);
    }

    public AdvertData collectData() {
        return new AdvertData(
                ZERO,
                mTvTitle.getText().toString(),
                mTvAuthor.getText().toString(),
                mTvDescription.getText().toString(),
                Float.parseFloat(mTvPrice.getText().toString()),
                mTvPhone.getText().toString(),
                "active",
                ZERO,
                ZERO,
                imagePath);
    }
}
