package com.acerolla.bouquiniste.domain.adverts;

import com.acerolla.bouquiniste.data.adverts.repository.IAdvertsRepository;

/**
 * Created by Evgeniy Solovev
 * Date: 24.05.2018
 * Email: solevur@gmail.com
 */
public class AdvertsInteractor implements IAdvertsInteractor {

    IAdvertsRepository mRepository;

    public AdvertsInteractor(IAdvertsRepository repository) {
        mRepository = repository;
    }

    @Override
    public void release() {
        mRepository = null;
    }
}
