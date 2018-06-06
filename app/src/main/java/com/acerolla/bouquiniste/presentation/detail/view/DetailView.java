package com.acerolla.bouquiniste.presentation.detail.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
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
import com.acerolla.bouquiniste.presentation.adverts.view.recycler.AdvertItemView;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;
import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionLayout;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DetailView extends RelativeLayout {

    private static final String DEFAULT_CATEGORY = "UNKNOWN!";
    private static final String API_URL = "http://85.119.144.206/";

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
    private Toolbar mToolbar;

    private RelativeLayout mRlRoot;
    private ProgressBar mProgress;
    private TextView mTvError;
    private Button mBtnRefresh;

    private FloatingActionLayout mFab;

    private AdvertData mAdvert;

    public DetailView(Context context) {
        super(context);
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
        DiManager.getCategoryComponent().inject(this);

        mToolbar = findViewById(R.id.toolbar_actionbar);

        mIvImage = findViewById(R.id.iv_image);
        mTvTitle = findViewById(R.id.tv_title);
        mTvAuthor = findViewById(R.id.tv_author);
        mTvDescription = findViewById(R.id.tv_description);
        mTvPrice = findViewById(R.id.tv_price);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvLocation = findViewById(R.id.tv_location);
        mTvCategory = findViewById(R.id.tv_category);

        mRlRoot = findViewById(R.id.content_root);
        mProgress = findViewById(R.id.progress);
        mTvError = findViewById(R.id.tv_error);
        mBtnRefresh = findViewById(R.id.btn_refresh);
        mFab = findViewById(R.id.fab);
    }

    public void setPhoneClickListener(OnClickListener listener) {
        mFab.setOnClickListener(listener);
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

        mAdvert = data;
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

    public String getPhoneNumber() {
        return mTvPhone.getText().toString();
    }

    public void setContentVisibility(int visibility) {
        mRlRoot.setVisibility(visibility);
    }

    public void setLoaderVisibility(int visibility) {
        mProgress.setVisibility(visibility);
    }

    public void setErrorVisibility(int visibility) {
        mTvError.setVisibility(visibility);
        mBtnRefresh.setVisibility(visibility);
    }

    public void setRefreshClickListener(OnClickListener listener){
        mBtnRefresh.setOnClickListener(listener);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public AdvertData getData() {
        return mAdvert;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

          mIvImage = null;
          mTvTitle = null;
          mTvAuthor = null;
          mTvDescription = null;
          mTvPrice = null;
          mTvPhone = null;
          mTvLocation = null;
          mTvCategory = null;
          mToolbar = null;

          if (mInteractor != null) {
              mInteractor.release();
          }
          mInteractor = null;
    }
}
