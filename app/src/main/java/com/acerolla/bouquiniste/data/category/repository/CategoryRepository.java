package com.acerolla.bouquiniste.data.category.repository;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryChildrenData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.category.repository.datasource.CategoryDataSourceFactory;
import com.acerolla.bouquiniste.data.category.repository.datasource.ICategoryDataSource;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryRepository implements ICategoryRepository {

    private ICategoryDataSource mCacheSource;

    @Override
    public void loadCategories(ResultListener<List<CategoryParentData>> listener) {
        if (getCacheSource().getCategoriesAsync() != null) {
            if (listener != null) {
                listener.onResult(getCacheSource().getCategoriesAsync());
                return;
            }
        }

        CategoryDataSourceFactory.getLocalDataSource()
                .loadCategories(resultFromLocal -> {
                    if (resultFromLocal != null) {
                        if (listener != null) {
                            getCacheSource().saveCategories(resultFromLocal);
                            listener.onResult(resultFromLocal);
                        }
                    } else {
                        CategoryDataSourceFactory.getCloudDataSource()
                                .loadCategories(resultFromCloud -> {
                                    if (resultFromCloud != null) {
                                        CategoryDataSourceFactory.getLocalDataSource()
                                                .saveCategories(resultFromCloud);
                                        getCacheSource().saveCategories(resultFromCloud);
                                        if (listener != null) {
                                            listener.onResult(resultFromCloud);
                                        }
                                    } else {
                                        if (listener != null) {
                                            listener.onResult(null);
                                        }
                                    }
                                });
                    }
                });
    }

    private ICategoryDataSource getCacheSource() {
        if (mCacheSource == null) {
            mCacheSource = CategoryDataSourceFactory.getMemoryCacheDataSource();
        }

        return mCacheSource;
    }

    @Override
    public void loadAdvertsByCategory(ResultListener<List<AdvertData>> listener, int categoryId) {
        CategoryDataSourceFactory.getCloudDataSource()
                .loadAdvertsByCategory(listener, categoryId);
    }

    @Override
    public void release() {
        if (mCacheSource != null) {
            mCacheSource.release();
        }
        mCacheSource = null;
    }
}
