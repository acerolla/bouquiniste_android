package com.acerolla.bouquiniste.presentation.adding.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.squareup.picasso.Picasso;

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

    private ImageView mIvImage;
    private TextView mTvTitle;
    private TextView mTvAuthor;
    private TextView mTvDescription;
    private TextView mTvPrice;
    private TextView mTvPhone;
    private TextView mTvLocation;
    private TextView mTvCategory;
    private Button mBtnPost;

    private LinearLayout mLlDescription;
    private LinearLayout mLlCategory;
    private LinearLayout mLlPhone;
    private LinearLayout mLlLocation;

    private String mImagePath;
    private int mCategoryId;

    public AddingView(Context context) {
        super(context);
    }

    public AddingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AddingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AddingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initViews() {
        setClickable(true);

        mIvImage = findViewById(R.id.iv_image);
        mTvTitle = findViewById(R.id.tv_title);
        mTvAuthor = findViewById(R.id.tv_author);
        mTvDescription = findViewById(R.id.tv_description);
        mTvPrice = findViewById(R.id.tv_price);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvLocation = findViewById(R.id.tv_location);
        mTvCategory = findViewById(R.id.tv_category);
        mBtnPost = findViewById(R.id.btn_post);

        mLlDescription = findViewById(R.id.ll_description);
        mLlCategory = findViewById(R.id.ll_category);
        mLlPhone = findViewById(R.id.ll_phone);
        mLlLocation = findViewById(R.id.ll_location);

    }

    public void setAddClickListener(OnClickListener listener) {
        mBtnPost.setOnClickListener(listener);
    }

    public void setImageClickListener(OnClickListener listener) {
        mIvImage.setOnClickListener(listener);
    }

    public void setFieldsClickListener(OnClickListener listener) {
        mLlCategory.setOnClickListener(listener);
        mLlDescription.setOnClickListener(listener);
        mLlPhone.setOnClickListener(listener);
        mLlLocation.setOnClickListener(listener);

        mTvPrice.setOnClickListener(listener);
        mTvTitle.setOnClickListener(listener);
        mTvAuthor.setOnClickListener(listener);
    }

    public AdvertData collectData() {
        AdvertData advert = new AdvertData();
        advert.setmTitle(mTvTitle.getText().toString());
        advert.setmAuthor(mTvAuthor.getText().toString());
        advert.setmDescription(mTvDescription.getText().toString());
        advert.setmPrice(Float.parseFloat(mTvPrice.getText().toString()));
        advert.setmPhone(mTvPhone.getText().toString());
        advert.setmStatus("active");
        advert.setmLocation(mTvLocation.getText().toString());
        advert.setmCategoryId(mCategoryId);
        return advert;
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setAuthor(String author) {
        mTvAuthor.setText(author);
    }

    public void setPrice(String price) {
        mTvPrice.setText(price);
    }

    public void setDescription(String description) {
        mTvDescription.setText(description);
    }

    public void setPhone(String phone) {
        mTvPhone.setText(phone);
    }

    public void setLocation(String location) {
        mTvLocation.setText(location);
    }



    public void setImage(Uri uri) {
        Picasso.get()
                .load(uri)
                .fit()
                .centerCrop()
                .into(mIvImage);
    }

    public void setCategory(int id, String title) {
        mTvCategory.setText(title);
        mCategoryId = id;
    }
}
