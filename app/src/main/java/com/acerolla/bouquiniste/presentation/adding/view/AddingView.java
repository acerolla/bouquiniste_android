package com.acerolla.bouquiniste.presentation.adding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

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
    private EditText mEtTitle;
    private EditText mEtAuthor;
    private EditText mEtDescription;
    private EditText mEtPrice;
    private EditText mEtPhone;
    private EditText mEtLocation;
    private Button mBtnCategory;
    private Button mBtnPost;

    private String mImagePath;
    private int mId;

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
        mEtTitle = findViewById(R.id.et_title);
        mEtAuthor = findViewById(R.id.et_author);
        mEtDescription = findViewById(R.id.et_description);
        mEtPrice = findViewById(R.id.et_price);
        mEtPhone = findViewById(R.id.et_phone);
        mEtLocation = findViewById(R.id.et_location);
        mBtnCategory = findViewById(R.id.tv_category);
        mBtnPost = findViewById(R.id.btn_post);

    }

    public void setAddClickListener(OnClickListener listener) {
        mBtnPost.setOnClickListener(listener);
    }

    public void setImageClickListener(OnClickListener listener) {
        mIvImage.setOnClickListener(listener);
    }

    public void setCategoryButtonCLickListener(OnClickListener listener) {
        mBtnCategory.setOnClickListener(listener);
    }

    public AdvertData collectData() {
        AdvertData advert = new AdvertData();
        advert.setmTitle(mEtTitle.getText().toString());
        advert.setmAuthor(mEtAuthor.getText().toString());
        advert.setmDescription(mEtDescription.getText().toString());
        advert.setmPrice(Float.parseFloat(mEtPrice.getText().toString()));
        advert.setmPhone(mEtPhone.getText().toString());
        advert.setmStatus("active");
        return advert;
    }

    public void setContentVisibility(int visibility) {

    }

    public void setLoaderVisibility(int visibility) {

    }

    public void setErrorVisibility(int visibility) {

    }

    public void setCategory(int id, String title) {
        mBtnCategory.setText(title);
        mId = id;
    }
}
