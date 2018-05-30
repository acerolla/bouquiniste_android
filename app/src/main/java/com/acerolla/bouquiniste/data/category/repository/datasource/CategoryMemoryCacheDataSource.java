package com.acerolla.bouquiniste.data.category.repository.datasource;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryMemoryCacheDataSource implements ICategoryDataSource {

    private List<CategoryParentData> mCategories;

    @Override
    public void loadCategories(ResultListener<List<CategoryParentData>> listener) {
        //ignore
    }

    @Override
    public void saveCategories(List<CategoryParentData> categories) {
        mCategories = categories;
    }

    @Override
    public void loadAdvertsByCategory(ResultListener<List<AdvertData>> listener, int categoryId) {
        //ignore
    }

    @Override
    public List<CategoryParentData> getCategoriesAsync() {
        return mCategories;
    }

    @Override
    public void clear() {
        mCategories = null;
    }

    @Override
    public void release() {
        mCategories = null;
    }
}
