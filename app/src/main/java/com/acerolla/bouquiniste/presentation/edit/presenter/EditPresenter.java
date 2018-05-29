package com.acerolla.bouquiniste.presentation.edit.presenter;

import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.repository.IAdvertsRepository;
import com.acerolla.bouquiniste.domain.edit.IEditInteractor;
import com.acerolla.bouquiniste.presentation.edit.view.IEditView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class EditPresenter implements IEditPresenter {

    private IEditView mView;
    private IEditInteractor mInteractor;

    private AdvertData mAdvert;

    public EditPresenter(IEditInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void bindView(IEditView view) {
        mView = view;
        mInteractor.loadAdvert(result -> {
            if (result != null) {
                if (mView != null) {
                    mView.setContentData(result);
                    mAdvert = result;
                }
            } else {
                if (mView != null) {

                }
            }
        }, mView.getExtraId());
    }

    @Override
    public void handleAdvertClosedClicked() {
        mInteractor.closeAdvert(result -> {
            if (result != null && result) {
                if (mView != null) {
                    mView.notifySuccess("Объявление успешно закрыто!");
                    mView.navigateBack(true);
                }
            } else {
                if (mView != null) {
                    mView.notifyFailure("Не удалось закрыть объявление. Попробуйте позже.");
                    mView.navigateBack(false);
                }
            }
        }, mAdvert.getId());
    }

    @Override
    public void handleAdvertEditClicked(AdvertData data) {
        mInteractor.editAdvert(result -> {
            if (result != null) {
                if (mView != null) {
                    mView.notifySuccess("Объявление успешно отредактировано!");
                    mView.navigateBack(true);
                }
            } else {
                mView.notifySuccess("Не удалось отредактировать объявление. Попробуйте позже.");
                mView.navigateBack(false);
            }
        }, data);
    }

    @Override
    public void handleCategoryClicked() {
        mView.navigateToCategories();
    }

    @Override
    public void handleCategorySelected(int categoryId, String categoryTitle) {
        mView.changeCategory(categoryId, categoryTitle);
    }

    @Override
    public void release() {
        mAdvert = null;
        mView = null;

        if (mInteractor != null) {
            mInteractor.release();
        }
        mInteractor = null;
    }
}
