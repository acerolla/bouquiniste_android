package com.acerolla.bouquiniste.presentation.favorites.view.recycler.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.favorites.view.recycler.AdvertAdapter;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IRecyclerPresenter extends BasePresenter<AdvertAdapter> {

    void onBind(ResultListener<String> listener, int categoryId);
    void handleItemClicked(AdvertData advert);
}
