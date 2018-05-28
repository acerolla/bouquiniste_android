package com.acerolla.bouquiniste.presentation.auth.login.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.widget.RelativeLayout;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class LoginView extends RelativeLayout {

    private AppCompatEditText mEtEmail;
    private AppCompatEditText mEtPassword;
    private AppCompatButton mBtnLogin;
    private AppCompatButton mBtnRegister;

    public LoginView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        setClickable(true);
        setBackgroundColor(Color.WHITE);

    }

    public void setRegisterClickListener(OnClickListener listener) {
        mBtnRegister.setOnClickListener(listener);
    }

    public void setLoginClickListener(OnClickListener listener) {
        mBtnLogin.setOnClickListener(listener);
    }

    public String getEmail() {
        return mEtEmail.getText().toString();
    }

    public String getPassword() {
        return mEtPassword.getText().toString();
    }


}
