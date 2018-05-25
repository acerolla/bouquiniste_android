package com.acerolla.bouquiniste.presentation.adding.view;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAddingView {

    void showChooseFileActivity();
    AdvertData collectData();
    void navigateToDetail(int advertId);
}
