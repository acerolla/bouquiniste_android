package com.acerolla.bouquiniste.presentation.category.view;

import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ICategoryView {

    void setCategoryData(List<CategoryParentData> data);
    void navigateBack(int categoryId, String categoryTitle);
}
