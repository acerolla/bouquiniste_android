package com.acerolla.bouquiniste.domain.category;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.category.repository.ICategoryRepository;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryInteractor implements ICategoryInteractor {

    private ICategoryRepository mRepository;

    public CategoryInteractor(ICategoryRepository repository) {
        mRepository = repository;
    }

    @Override
    public void loadCategories(ResultListener<List<CategoryParentData>> listener) {
        mRepository.loadCategories(listener);
    }

    @Override
    public void loadAdvertsByCategory(ResultListener<List<AdvertData>> listener, int categoryId) {
        mRepository.loadAdvertsByCategory(listener, categoryId);
    }

    @Override
    public void release() {
        mRepository = null;
    }
}
