package com.acerolla.bouquiniste.data.category.repository;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.category.repository.datasource.CategoryDataSourceFactory;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryRepository implements ICategoryRepository{


    @Override
    public void loadCategories(ResultListener<List<CategoryParentData>> listener) {
        CategoryDataSourceFactory.getLocalDataSource()
                .loadCategories(resultFromLocal -> {
                    if (resultFromLocal != null) {
                        if (listener != null) {
                            listener.onResult(resultFromLocal);
                        }
                    } else {
                        CategoryDataSourceFactory.getCloudDataSource()
                                .loadCategories(result -> {
                                    if (result != null) {
                                        CategoryDataSourceFactory.getLocalDataSource()
                                                .saveCategories(result);
                                        if (listener != null) {
                                            listener.onResult(result);
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

    @Override
    public void loadAdvertsByCategory(ResultListener<List<AdvertData>> listener, int categoryId) {
        CategoryDataSourceFactory.getCloudDataSource()
                .loadAdvertsByCategory(listener, categoryId);
    }

    @Override
    public void release() {

    }
}
