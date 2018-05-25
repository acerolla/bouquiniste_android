package com.acerolla.bouquiniste.data.auth.entity.register;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
public class RegisterData {

    private String mEmail;

    public RegisterData(String email) {
        mEmail = email;
    }

    public String getEmail() {
        return mEmail;
    }
}
