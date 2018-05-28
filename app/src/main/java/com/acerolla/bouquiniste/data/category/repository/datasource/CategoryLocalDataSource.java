package com.acerolla.bouquiniste.data.category.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.utils.BouquinisteRunnable;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryLocalDataSource implements ICategoryDataSource {

    private static final int ZERO = 0;

    @Override
    public void loadCategories(ResultListener<List<CategoryParentData>> listener) {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        return BouquinisteApplication.getInstance()
                                .getDbHelper()
                                .getDao(CategoryParentData.class)
                                .queryForAll();
                    }
                }, result -> {
                    if (result != null && result instanceof List && !((List) result).isEmpty() &&
                            ((List) result).get(ZERO)!= null && ((List) result).get(ZERO) instanceof CategoryParentData) {
                        if (listener != null) {
                            listener.onResult((List<CategoryParentData>) result);
                        }
                    } else {
                        if (listener != null) {
                            listener.onResult(null);
                        }
                    }
                });
    }

    @Override
    public void saveCategories(List<CategoryParentData> categories) {
        BouquinisteApplication.getInstance()
                .getBackgroundThread()
                .execute(new BouquinisteRunnable() {
                    @Override
                    public Object execute() throws Throwable {
                        for (CategoryParentData category : categories) {
                            BouquinisteApplication.getInstance()
                                    .getDbHelper()
                                    .getDao(CategoryParentData.class)
                                    .create(category);
                        }

                        return null;
                    }
                }, null);
    }

    @Override
    public void loadAdvertsByCategory(ResultListener<List<AdvertData>> listener, int categoryId) {
        //ignore
    }

    @Override
    public List<CategoryParentData> getCategoriesAsync() {
        //ignore
        return null;
    }

    @Override
    public void release() {

    }
}
