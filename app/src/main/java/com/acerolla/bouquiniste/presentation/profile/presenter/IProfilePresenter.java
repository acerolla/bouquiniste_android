package com.acerolla.bouquiniste.presentation.profile.presenter;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.profile.view.IProfileView;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface IProfilePresenter extends BasePresenter<IProfileView> {

    void handleNameClicked();
    void handleNameChanged(String oldUserName, String newUserName);
    void handleItemClicked(AdvertData data);

    void handleItemEdited();
}
