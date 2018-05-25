package com.acerolla.bouquiniste.domain.adverts;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.profile.ResultListener;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertsInteractor implements IAdvertsInteractor {

    private IAdvertsRepository mRepository;

    public AdvertsInteractor(IAdvertsRepository repository) {
        mRepository = repository;
    }

    @Override
    public void loadAdvertList(ResultListener<List<AdvertData>> listener) {
        mRepository.loadAdvertList(listener);
    }

    @Override
    public void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId) {
        mRepository.getUserAdverts(listener, userId);
    }

    @Override
    public void release() {
        mRepository = null;
    }
}
