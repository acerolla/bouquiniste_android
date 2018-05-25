package com.acerolla.bouquiniste.presentation.adding.presenter;

import android.net.Uri;

import com.acerolla.bouquiniste.presentation.BasePresenter;
import com.acerolla.bouquiniste.presentation.adding.view.IAddingView;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAddingPresenter extends BasePresenter<IAddingView> {

    void handleUploadClick();
    void handleFileChoosed(Uri uri);
    void handleAddClick();
}
