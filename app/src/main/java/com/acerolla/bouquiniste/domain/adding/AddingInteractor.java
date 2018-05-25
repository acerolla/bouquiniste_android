package com.acerolla.bouquiniste.domain.adding;

import com.acerolla.bouquiniste.data.adding.repository.IAddingRepository;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.profile.ResultListener;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingInteractor implements IAddingInteractor {

    private IAddingRepository mRepository;

    public AddingInteractor(IAddingRepository repository) {
        mRepository = repository;
    }

    @Override
    public void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData) {
        mRepository.postAdvert(listener, advertData);
    }

    @Override
    public void release() {
        mRepository = null;
    }
}
