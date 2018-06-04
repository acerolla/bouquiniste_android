package com.acerolla.bouquiniste.presentation.auth.login.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acerolla.bouquiniste.R;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class LoginView extends RelativeLayout {

    private EditText mEtEmail;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private TextView mBtnRegister;

    public LoginView(Context context) {
        super(context);
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initViews() {
        setClickable(true);

        mEtEmail = findViewById(R.id.et_email);
        mEtPassword = findViewById(R.id.et_password);
        mBtnLogin = findViewById(R.id.btn_login);

        mBtnRegister = findViewById(R.id.tv_label);
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
