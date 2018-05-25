package com.acerolla.bouquiniste.data.category.repository;

import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.category.repository.datasource.CategoryDataSourceFactory;
import com.acerolla.bouquiniste.data.category.repository.datasource.ICategoryDataSource;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryRepository implements ICategoryRepository{

    private ICategoryDataSource mCacheSource;

    @Override
    public List<CategoryParentData> getCategories() {
        return getCacheSource().getCategories();
    }

    private ICategoryDataSource getCacheSource() {
        if (mCacheSource == null) {
            mCacheSource = CategoryDataSourceFactory.getMemoryCacheDataSource();
        }

        return mCacheSource;
    }

    @Override
    public void release() {
        if (mCacheSource != null) {
            mCacheSource.release();
        }
        mCacheSource = null;
    }
}
