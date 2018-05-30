package com.acerolla.bouquiniste.presentation.detail.presenter;

import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.detail.view.IDetailView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IDetailPresenter extends BasePresenter<IDetailView> {

    void handleFavoriteClick();
    void handlePhoneClick(String phoneNumber);
    void handleShareClick();
    void handleOnMapClick();
}
