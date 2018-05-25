package com.acerolla.bouquiniste.data.category.repository.datasource;

import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ICategoryDataSource {

    List<CategoryParentData> getCategories();
    void release();
}
