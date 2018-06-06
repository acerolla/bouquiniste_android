package com.acerolla.bouquiniste.presentation.edit.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryChildrenData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.domain.category.ICategoryInteractor;
import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionLayout;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class EditView extends ScrollView {

    private static final String DEFAULT_CATEGORY = "UNKNOWN!";
    private static final String API_URL = "http://85.119.144.206/";

    private int mCategoryId;
    private int mAdvertId;

    @Inject
    ICategoryInteractor mInteractor;

    private ImageView mIvImage;
    private TextView mTvTitle;
    private TextView mTvAuthor;
    private TextView mTvDescription;
    private TextView mTvPrice;
    private TextView mTvPhone;
    private TextView mTvLocation;
    private TextView mTvCategory;
    private Button mBtnEdit;


    private LinearLayout mLlDescription;
    private LinearLayout mLlCategory;
    private LinearLayout mLlPhone;
    private LinearLayout mLlLocation;

    private RelativeLayout mRlError;
    private RelativeLayout mRlRoot;
    private ProgressBar mProgress;
    private Button mBtnRefresh;


    public EditView(Context context) {
        super(context);
    }

    public EditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EditView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @SuppressLint("RestrictedApi")
    public void initViews() {
        DiManager.getCategoryComponent().inject(this);

        mIvImage = findViewById(R.id.iv_image);
        mTvTitle = findViewById(R.id.tv_title);
        mTvAuthor = findViewById(R.id.tv_author);
        mTvDescription = findViewById(R.id.tv_description);
        mTvPrice = findViewById(R.id.tv_price);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvLocation = findViewById(R.id.tv_location);
        mTvCategory = findViewById(R.id.tv_category);
        mBtnEdit = findViewById(R.id.btn_edit);

        mLlDescription = findViewById(R.id.ll_description);
        mLlCategory = findViewById(R.id.ll_category);
        mLlPhone = findViewById(R.id.ll_phone);
        mLlLocation = findViewById(R.id.ll_location);


        mRlRoot = findViewById(R.id.content_root);
        mProgress = findViewById(R.id.progress);
        mRlError = findViewById(R.id.rl_error);
        mBtnRefresh = findViewById(R.id.btn_refresh);
    }

    public void setContentData(AdvertData data) {
        String url = "";
        if (data.getImage() != null && !data.getImage().isEmpty()) {
            url = API_URL + data.getImage();
        }
        loadImage(url);
        mTvTitle.setText(data.getTitle());
        mTvAuthor.setText(data.getAuthor());
        mTvPrice.setText(String.format(Locale.getDefault(), "%.2f \u20BD", data.getPrice()));
        mTvLocation.setText(data.getLocation());
        mTvCategory.setText(DEFAULT_CATEGORY);
        mTvPhone.setText(data.getPhone());
        mTvDescription.setText(data.getDescription());

        mInteractor.loadCategories(result -> {
            for (CategoryParentData parent : result) {
                if (parent.getId() == data.getCategoryId()) {
                    mTvCategory.setText(parent.getTitle());
                    return;
                }

                for (CategoryChildrenData child : parent.getChildren()) {
                    if (child.getId() == data.getCategoryId()) {
                        mTvCategory.setText(parent.getTitle() + " / " + child.getTitle());
                    }
                }
            }
        });

        mAdvertId = data.getId();
        mCategoryId = data.getCategoryId();
    }

    private void loadImage(String url) {
        if (url != null && !url.isEmpty()) {
            Picasso.get()
                    .load(url)
                    .fit()
                    .centerCrop()
                    .into(mIvImage);
        } else {
            Picasso.get()
                    .load(R.mipmap.ic_camera)
                    .into(mIvImage);
        }
    }

    public void setItemClickListener(OnClickListener listener) {
        mLlCategory.setOnClickListener(listener);
        mLlDescription.setOnClickListener(listener);
        mLlPhone.setOnClickListener(listener);
        mLlLocation.setOnClickListener(listener);

        mTvPrice.setOnClickListener(listener);
        mTvTitle.setOnClickListener(listener);
        mTvAuthor.setOnClickListener(listener);
    }

    public void setEditClickListener(OnClickListener listener) {
        mBtnEdit.setOnClickListener(listener);
    }

    public void setRefreshListener(OnClickListener listener) {
        mBtnRefresh.setOnClickListener(listener);
    }

    public void changeCategory(int categoryId, String categoryTitle) {
        mTvCategory.setText(categoryTitle);
        mCategoryId = categoryId;
    }

    public AdvertData collectData() {

        return new AdvertData(
                mAdvertId,
                mTvTitle.getText().toString(),
                mTvAuthor.getText().toString(),
                mTvDescription.getText().toString(),
                getPrice(mTvPrice.getText().toString()),
                mTvPhone.getText().toString(),
                "active",
                mCategoryId,
                "default_path",
                false,
                mTvLocation.getText().toString()
        );
    }

    private float getPrice(String price) {
        if (price.indexOf('.') > -1) {
            price = price.substring(0, price.indexOf('.'));
        } else if (price.indexOf(',') > -1) {
            price = price.substring(0, price.indexOf(','));
        } else if (price.indexOf(' ') > -1){
            price = price.substring(0, price.indexOf(' '));
        }

        return Float.parseFloat(price);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setAuthor(String author) {
        mTvAuthor.setText(author);
    }

    public void setPrice(String price) {
        mTvPrice.setText(String.format(Locale.getDefault(), "%s,00 \u20BD", price));
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

    public void setContentVisibility(int visibility) {
        mRlRoot.setVisibility(visibility);
    }

    public void setLoaderVisibility(int visibility) {
        mProgress.setVisibility(visibility);
    }

    public void setErrorVisibility(int visibility) {
        mRlError.setVisibility(visibility);
    }
}
