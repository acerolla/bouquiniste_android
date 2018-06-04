package com.acerolla.bouquiniste.domain.adding;

import android.net.Uri;

import com.acerolla.bouquiniste.data.adding.repository.IAddingRepository;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.domain.adverts.IAdvertsInteractor;
import com.acerolla.bouquiniste.domain.category.ICategoryInteractor;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingInteractor implements IAddingInteractor {

    private IAddingRepository mRepository;
    private ICategoryInteractor mCategoryInteractor;
    private IAdvertsInteractor mAdvertsInteractor;


    public AddingInteractor(IAddingRepository repository, ICategoryInteractor categoryInteractor, IAdvertsInteractor advertsInteractor) {
        mRepository = repository;
        mCategoryInteractor = categoryInteractor;
        mAdvertsInteractor = advertsInteractor;
    }

    @Override
    public void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData) {
        mRepository.postAdvert(result -> {
            if (result != null) {
                if (listener != null) {
                    listener.onResult(result);
                }
                mAdvertsInteractor.saveAdvertToCache(result);
            } else {
                if (listener != null) {
                    listener.onResult(null);
                }
            }
        }, advertData);
    }

    @Override
    public void loadCategories(ResultListener<List<CategoryParentData>> listener) {
        mCategoryInteractor.loadCategories(listener);
    }

    @Override
    public void saveImagePath(Uri uri) {
        mRepository.saveUri(uri);
    }

    @Override
    public void release() {

        mRepository = null;

        if (mCategoryInteractor != null) {
            mCategoryInteractor.release();
        }
        mCategoryInteractor = null;

        if (mAdvertsInteractor != null) {
            mAdvertsInteractor.release();
        }
        mAdvertsInteractor = null;
    }
}
