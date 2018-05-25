package com.acerolla.bouquiniste.data.auth.entity.login;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
public class LoginData {

    private String mEmail;
    private String mPassword;

    public LoginData(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }
}
