package com.acerolla.bouquiniste.presentation.adverts.view.recycler.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryChildrenData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.domain.category.ICategoryInteractor;
import com.acerolla.bouquiniste.presentation.adverts.view.recycler.AdvertAdapter;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class RecyclerPresenter implements IRecyclerPresenter {

    private AdvertAdapter mAdapter;
    private ICategoryInteractor mInteractor;

    public RecyclerPresenter(ICategoryInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(AdvertAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onBind(ResultListener<String> listener, int categoryId) {
        mInteractor.loadCategories(result -> {
            if (result == null) {
                return;
            }

            for (CategoryParentData parent : result) {
                if (parent.getId() == categoryId) {
                    listener.onResult(parent.getTitle());
                    return;
                }

                for (CategoryChildrenData child : parent.getChildren()) {
                    if (child.getId() == categoryId) {
                        listener.onResult(parent.getTitle() + " / " + child.getTitle());
                    }
                }
            }
        });
    }

    @Override
    public void handleItemClicked(AdvertData advert) {

    }

    @Override
    public void release() {
        mAdapter = null;

        if (mInteractor != null) {
            mInteractor.release();
        }
        mInteractor = null;
    }
}
