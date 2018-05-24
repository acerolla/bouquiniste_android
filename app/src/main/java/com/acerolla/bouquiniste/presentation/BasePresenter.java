package com.acerolla.bouquiniste.presentation;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public interface BasePresenter<T> {

    void bindView(T view);
    void release();
}
