package com.acerolla.bouquiniste.presentation.profile.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.presentation.profile.view.recycler.AdvertAdapter;
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

    private RecyclerView mRvAdverts;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdvertAdapter mAdapter;

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

        mRvAdverts = findViewById(R.id.rv_adverts);
        mRvAdverts.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRvAdverts.setLayoutManager(mLayoutManager);

        mAdapter = new AdvertAdapter();
        mRvAdverts.setAdapter(mAdapter);
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
        mAdapter.setData(data);
    }

    public void setItemClickListener(OnClickListener listener) {
        mAdapter.setItemClickListener(listener);
    }

    public AdvertData getDataByView(View v) {
        return ((AdvertAdapter.ViewHolder)mRvAdverts.getChildViewHolder(v)).item;
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

    public void setLoginButtonVisibility(int visibility) {
        mBtnLogin.setVisibility(visibility);
    }

    public void setNameClickListener(OnClickListener listener) {
        mTvUser.setOnClickListener(listener);
    }

    public String getUserName() {
        return mTvUser.getText().toString();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mIvAvatar = null;
        mTvUser = null;
        mTvEmail = null;
    }
}
