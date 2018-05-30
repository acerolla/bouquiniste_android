package com.acerolla.bouquiniste.presentation.category.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.category.presenter.ICategoryPresenter;
import com.acerolla.bouquiniste.presentation.category.view.recycler.CategoryViewHolder;
import com.acerolla.bouquiniste.presentation.category.view.recycler.SubcategoryViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryActivity extends AppCompatActivity implements ICategoryView {

    public static final int REQUEST_CATEGORY = 33;
    public static final String EXTRA_CATEGORY_ID = "CategoryActivity.EXTRA_CATEGORY_ID";
    public static final String EXTRA_CATEGORY_TITLE = "CategoryActivity.EXTRA_CATEGORY_TITLE";

    private CategoryView mView;

    @Inject
    ICategoryPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new CategoryView(this);
        setContentView(mView);
        setListeners();
        initToolbar();

        DiManager.getCategoryComponent().inject(this);
        mPresenter.bindView(this);
    }

    private void setListeners() {
        mView.setParentClickListener(v -> {
            if (mView.getViewHolder(v) instanceof CategoryViewHolder) {
                mPresenter.handleCategorySelected(
                        ((CategoryViewHolder) mView.getViewHolder(v)).item.getId(),
                        ((CategoryViewHolder) mView.getViewHolder(v)).item.getTitle());
            }
        });

        mView.setChildClickListener(v -> {
            if (mView.getViewHolder(v) instanceof SubcategoryViewHolder) {
                mPresenter.handleCategorySelected(
                        ((SubcategoryViewHolder) mView.getViewHolder(v)).item.getId(),
                        ((SubcategoryViewHolder) mView.getViewHolder(v)).item.getTitle());
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(mView.getToolbar());

        mView.getToolbar().setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        mView.getToolbar().setTitle("Категории");
        mView.getToolbar().setNavigationOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void setCategoryData(List<CategoryParentData> data) {
        mView.setData(data);
    }

    @Override
    public void navigateBack(int categoryId, String categoryTitle) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        intent.putExtra(EXTRA_CATEGORY_TITLE, categoryTitle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
