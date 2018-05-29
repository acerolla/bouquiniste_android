package com.acerolla.bouquiniste.presentation.edit.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

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

    @Inject
    ICategoryInteractor mInteractor;

    private ImageView mIvImage;
    private EditText mTvTitle;
    private EditText mTvAuthor;
    private EditText mTvDescription;
    private EditText mTvPrice;
    private EditText mTvPhone;
    private EditText mTvLocation;
    private Button mTvCategory;
    private Button mBtnEdit;



    //private Toolbar mToolbar;
    private FloatingActionLayout mFab;


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

        //mToolbar = findViewById(R.id.toolbar_actionbar);
        //mToolbar.inflateMenu(R.menu.menu_detail);

        mIvImage = findViewById(R.id.iv_image);
        mTvTitle = findViewById(R.id.tv_title);
        mTvAuthor = findViewById(R.id.tv_author);
        mTvDescription = findViewById(R.id.tv_description);
        mTvPrice = findViewById(R.id.tv_price);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvLocation = findViewById(R.id.tv_location);
        mTvCategory = findViewById(R.id.tv_category);
        mBtnEdit = findViewById(R.id.btn_edit);

        mFab = findViewById(R.id.fab);
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
                    mTvTitle.setText(parent.getTitle());
                    return;
                }

                for (CategoryChildrenData child : parent.getChildren()) {
                    if (child.getId() == data.getCategoryId()) {
                        mTvCategory.setText(parent.getTitle() + " / " + child.getTitle());
                    }
                }
            }
        });

        mCategoryId = data.getId();
    }

    private void loadImage(String url) {
        if (url != null && !url.isEmpty()) {
            Picasso.get()
                    .load(url)
                    .into(mIvImage);
        } else {
            Picasso.get()
                    .load(R.mipmap.ic_camera)
                    .into(mIvImage);
        }
    }

    public void setCategoryButtonClickListener(OnClickListener listener) {
        mTvCategory.setOnClickListener(listener);
    }

    public void setEditClickListener(OnClickListener listener) {
        mBtnEdit.setOnClickListener(listener);
    }

    public void changeCategory(int categoryId, String categoryTitle) {
        mTvCategory.setText(categoryTitle);
        mCategoryId = categoryId;
    }

    public AdvertData collectData() {

        return new AdvertData(
                0,
                mTvTitle.getText().toString(),
                mTvAuthor.getText().toString(),
                mTvDescription.getText().toString(),
                Float.parseFloat(mTvPrice.getText().toString()),
                mTvPhone.getText().toString(),
                "active",
                mCategoryId,
                "default_path",
                false,
                mTvLocation.getText().toString()
        );
    }

    public void setContentVisibility(int visibility) {

    }

    public void setLoaderVisibility(int visibility) {

    }

    public void setErrorVisibility(int visibility) {

    }
}
