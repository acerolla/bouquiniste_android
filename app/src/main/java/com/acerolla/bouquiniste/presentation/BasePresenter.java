package com.acerolla.bouquiniste.presentation;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.05.2018.
 */
public interface BasePresenter<T> {

    void bindView(T view);
    void release();
}
