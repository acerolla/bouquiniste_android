package com.acerolla.bouquiniste.domain.detail;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.profile.ResultListener;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DetailInteractor implements IDetailInteractor {

    private IAdvertsRepository mRepository;

    public DetailInteractor(IAdvertsRepository repository) {
        mRepository = repository;
    }

    @Override
    public void loadAdvert(ResultListener<AdvertData> listener, int advertId) {
        mRepository.loadAdvert(listener, advertId);
    }

    @Override
    public void editAdvert(ResultListener<AdvertData> listener, AdvertData advert) {
        mRepository.editAdvert(listener, advert);
    }

    @Override
    public void release() {
        mRepository = null;
    }


}
