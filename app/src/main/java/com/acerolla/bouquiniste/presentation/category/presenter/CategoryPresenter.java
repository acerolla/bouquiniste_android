package com.acerolla.bouquiniste.presentation.category.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.domain.category.ICategoryInteractor;
import com.acerolla.bouquiniste.presentation.category.view.ICategoryView;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryPresenter implements ICategoryPresenter {

    private ICategoryView mView;
    private ICategoryInteractor mInteractor;

    public CategoryPresenter(ICategoryInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(ICategoryView view) {
        mView = view;
        mInteractor.loadCategories(result -> {
            if (result != null && !result.isEmpty()) {
                mView.setCategoryData(result);
            }
        });
    }

    @Override
    public void handleCategorySelected(int categoryId, String categoryTitle) {
        mView.navigateBack(categoryId, categoryTitle);
    }

    @Override
    public void release() {
        mView = null;

        if (mInteractor != null) {
            mInteractor.release();
        }
        mInteractor = null;
    }
}
