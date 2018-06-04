package com.acerolla.bouquiniste.presentation.auth.register.view;

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
public class RegisterView extends RelativeLayout {

    private EditText mEtEmail;
    private Button mBtnRegister;
    private TextView mBtnLogin;

    public RegisterView(Context context) {
        super(context);
    }

    public RegisterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RegisterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RegisterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initViews() {
        setClickable(true);

        mEtEmail = findViewById(R.id.et_email);
        mBtnRegister = findViewById(R.id.btn_register);
        mBtnLogin = findViewById(R.id.tv_label);
    }

    public void setRegisterClickListener(OnClickListener listener) {
        mBtnRegister.setOnClickListener(listener);
    }

    public void setLoginClickListener(OnClickListener listener) {
        mBtnLogin.setOnClickListener(listener);
    }

    public String collectData() {
        return mEtEmail.getText().toString();
    }
}
