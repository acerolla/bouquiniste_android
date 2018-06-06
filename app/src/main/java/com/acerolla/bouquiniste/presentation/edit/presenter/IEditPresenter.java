package com.acerolla.bouquiniste.presentation.edit.presenter;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.edit.view.IEditView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IEditPresenter extends BasePresenter<IEditView> {

    void handleAdvertClosedClicked();
    void handleAdvertEditClicked(AdvertData data);
    void handleCategoryClicked();
    void handleCategorySelected(int categoryId, String categoryTitle);
    void handleShareClick();
    void handleRefreshClick();
}
