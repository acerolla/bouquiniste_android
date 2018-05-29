package com.acerolla.bouquiniste.presentation.profile.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.presentation.utils.ImageLoader;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;

import java.util.List;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class ProfileView extends RelativeLayout {

    private static final int ID_AVATAR = 1;
    private static final int ID_USER = 2;
    private static final int ID_EMAIL = 3;
    private static final int ID_ADVERT_IMAGE = 4;

    private static final int TEXT_SIZE_LABEL_ADVERT = 16;

    private ImageView mIvAvatar;
    private TextView mTvUser;
    private TextView mTvEmail;
    private Button mBtnLogin;

    public ProfileView(Context context) {
        super(context);
    }

    public ProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initViews() {
        setClickable(true);

        mIvAvatar = findViewById(R.id.iv_avatar);
        mTvUser = findViewById(R.id.tv_username);
        mTvEmail = findViewById(R.id.tv_email);
        mBtnLogin = findViewById(R.id.btn_login);
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

    public void setContentAdverts(List<AdvertData> data) {

    }

    public void setProfileVisibility(int visibility) {

    }

    public void setAdvertsVisibility(int visibility) {

    }

    public void setLoaderVisibility(int visibility) {

    }

    public void setErrorVisibility(int visibility) {

    }

    public void setEmptyMessageVisibility(int visibility) {

    }

    public void setNameClickListener(OnClickListener listener) {
        mTvUser.setOnClickListener(listener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mIvAvatar = null;
        mTvUser = null;
        mTvEmail = null;
    }
}
