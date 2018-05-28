package com.acerolla.bouquiniste.presentation.category.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.category.view.ICategoryView;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ICategoryPresenter extends BasePresenter<ICategoryView> {

    void handleCategorySelected(int categoryId, String categoryTitle);
}
