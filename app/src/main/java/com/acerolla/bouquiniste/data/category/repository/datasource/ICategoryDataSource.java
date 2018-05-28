package com.acerolla.bouquiniste.data.category.repository.datasource;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface ICategoryDataSource {

    void loadCategories(ResultListener<List<CategoryParentData>> listener);
    void saveCategories(List<CategoryParentData> categories);
    void loadAdvertsByCategory(ResultListener<List<AdvertData>> listener, int categoryId);
    void release();
}
