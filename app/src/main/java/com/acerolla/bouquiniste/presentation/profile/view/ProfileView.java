package com.acerolla.bouquiniste.presentation.profile.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.presentation.utils.ImageLoader;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class ProfileView extends RelativeLayout {

    private static final int ID_AVATAR = 1;
    private static final int ID_USER = 2;
    private static final int ID_EMAIL = 3;
    private static final int ID_ADVERT_IMAGE = 4;

    private static final int TEXT_SIZE_LABEL_ADVERT = 16;

    private AppCompatImageView mIvAvatar;
    private AppCompatTextView mTvUser;
    private AppCompatTextView mTvEmail;

    public ProfileView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        setClickable(true);
        setBackgroundColor(Color.WHITE);

        mIvAvatar = new AppCompatImageView(getContext());
        mIvAvatar.setId(ID_AVATAR);

        LayoutParams avatarParams = new LayoutParams(
                ValuesConverter.dp2px(ValuesConverter.DP_100),
                ValuesConverter.dp2px(ValuesConverter.DP_100));
        mIvAvatar.setLayoutParams(avatarParams);
        addView(mIvAvatar);

        mTvUser = new AppCompatTextView(getContext());
        mTvUser.setTextColor(ContextCompat.getColor(getContext(), R.color.black));

        LayoutParams userParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        userParams.addRule(RIGHT_OF, ID_AVATAR);
        userParams.topMargin = ValuesConverter.dp2px(ValuesConverter.DP_25);
        int marginLeft = ValuesConverter.dp2px(ValuesConverter.DP_10);
        userParams.leftMargin = marginLeft;
        mTvUser.setLayoutParams(userParams);
        addView(mTvUser);

        mTvEmail = new AppCompatTextView(getContext());
        mTvEmail.setTextColor(ContextCompat.getColor(getContext(), R.color.darker_gray));

        LayoutParams emailParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        emailParams.addRule(RIGHT_OF, ID_AVATAR);
        emailParams.topMargin = ValuesConverter.dp2px(ValuesConverter.DP_45);
        emailParams.leftMargin = marginLeft;
        mTvEmail.setLayoutParams(emailParams);
        addView(mTvEmail);

        AppCompatImageView advertImage = new AppCompatImageView(getContext());
        advertImage.setId(ID_ADVERT_IMAGE);
        advertImage.setImageResource(R.mipmap.ic_user_adverts);

        int imageSize = ValuesConverter.dp2px(ValuesConverter.DP_30);
        LayoutParams advertImageParams = new LayoutParams(
                imageSize,
                imageSize);
        advertImageParams.addRule(BELOW, ID_AVATAR);
        advertImageParams.topMargin = ValuesConverter.dp2px(ValuesConverter.DP_10);
        advertImage.setLayoutParams(advertImageParams);
        addView(advertImage);

        AppCompatTextView advertLabel = new AppCompatTextView(getContext());
        advertLabel.setGravity(Gravity.CENTER_VERTICAL);
        advertLabel.setTextSize(TEXT_SIZE_LABEL_ADVERT);
        advertLabel.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        advertLabel.setText(R.string.profile_label_adverts);

        LayoutParams advertLabelParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                imageSize);
        advertLabelParams.addRule(RIGHT_OF, ID_ADVERT_IMAGE);
        advertLabelParams.addRule(BELOW, ID_AVATAR);
        advertLabelParams.topMargin = ValuesConverter.dp2px(ValuesConverter.DP_10);
        advertLabelParams.leftMargin = marginLeft;
        advertLabel.setLayoutParams(advertLabelParams);
        addView(advertLabel);
    }

    public void setAvatar(String userName) {
        ImageLoader.showAvatar(mIvAvatar, userName);
    }

    public void setUserName(CharSequence userName) {
        mTvUser.setText(userName);
    }

    public void setEmail(CharSequence email) {
        mTvEmail.setText(email);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mIvAvatar = null;
        mTvUser = null;
        mTvEmail = null;
    }
}
