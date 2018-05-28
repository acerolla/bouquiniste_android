package com.acerolla.bouquiniste.data;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public interface ResultListener<T> {
    void onResult(T result);
}
