package com.acerolla.bouquiniste.domain.adding;

import com.acerolla.bouquiniste.data.adding.repository.IAddingRepository;

/**
 * Created by Evgeniy Solovev
 * Date: 24.05.2018
 * Email: solevur@gmail.com
 */
public class AddingInteractor implements IAddingInteractor {

    private IAddingRepository mRepository;

    public AddingInteractor(IAddingRepository repository) {
        mRepository = repository;
    }


    @Override
    public void release() {
        mRepository = null;
    }
}
