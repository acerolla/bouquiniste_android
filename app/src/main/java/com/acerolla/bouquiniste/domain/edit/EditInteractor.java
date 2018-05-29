package com.acerolla.bouquiniste.domain.edit;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.data.edit.repository.IEditRepository;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class EditInteractor implements IEditInteractor {

    private IEditRepository mEditRepository;
    private IAdvertsRepository mAdvertsRepository;

    public EditInteractor(IEditRepository repository, IAdvertsRepository advertsRepository) {
        mEditRepository = repository;
        mAdvertsRepository = advertsRepository;
    }


    @Override
    public void closeAdvert(ResultListener<Boolean> listener, int advertId) {
        mEditRepository.closeAdvert(listener, advertId);
    }

    @Override
    public void editAdvert(ResultListener<AdvertData> listener, AdvertData data) {
        mEditRepository.editAdvert(listener, data);
    }

    @Override
    public void loadAdvert(ResultListener<AdvertData> listener, int id) {
        if (id == -1) {
            if (listener != null) {
                listener.onResult(null);
                return;
            }
        }

        mAdvertsRepository.loadAdvert(listener, id);
    }

    @Override
    public void release() {
        mEditRepository = null;
        mAdvertsRepository = null;
    }
}
