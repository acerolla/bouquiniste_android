package com.acerolla.bouquiniste.data.utils;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public abstract class BouquinisteRunnable implements Runnable {

    @Override
    public void run() {
        // ignore
    }

    abstract public Object execute() throws Throwable;

}
