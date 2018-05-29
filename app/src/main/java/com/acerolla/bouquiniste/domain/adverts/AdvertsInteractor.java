package com.acerolla.bouquiniste.domain.adverts;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.domain.category.ICategoryInteractor;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertsInteractor implements IAdvertsInteractor {

    private IAdvertsRepository mRepository;
    private ICategoryInteractor mCategoryInteractor;

    public AdvertsInteractor(IAdvertsRepository repository, ICategoryInteractor categoryInteractor) {
        mRepository = repository;
        mCategoryInteractor = categoryInteractor;
    }

    @Override
    public void loadAdvertList(ResultListener<List<AdvertData>> listener) {
        mRepository.loadAdvertList(listener);
    }

    @Override
    public void loadUserAdverts(ResultListener<List<AdvertData>> listener, int userId) {
        mRepository.loadUserAdverts(listener, userId);
    }

    @Override
    public void saveAdvertToCache(AdvertData advertData) {
        mRepository.saveAdvertToCache(advertData);
    }

    @Override
    public void loadCategories(ResultListener<List<CategoryParentData>> listener) {
        mCategoryInteractor.loadCategories(listener);
    }

    @Override
    public void loadAdvertsByCategories(ResultListener<List<AdvertData>> listener, int categoryId) {
        mCategoryInteractor.loadAdvertsByCategory(listener, categoryId);
    }

    @Override
    public void release() {
        mRepository = null;

        if (mCategoryInteractor != null) {
            mCategoryInteractor.release();
        }
        mCategoryInteractor = null;
    }
}
