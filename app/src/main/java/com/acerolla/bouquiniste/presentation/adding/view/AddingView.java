package com.acerolla.bouquiniste.presentation.adding.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingView extends RelativeLayout {

    public static final int ID_BUTTON_UPLOAD = 1;

    private AppCompatButton mBtnUpload;

    public AddingView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        setClickable(true);
        setBackgroundColor(Color.WHITE);

        mBtnUpload = new AppCompatButton(getContext());
        mBtnUpload.setId(ID_BUTTON_UPLOAD);
        mBtnUpload.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        mBtnUpload.setText("upload");
        mBtnUpload.setTextColor(Color.WHITE);

        LayoutParams uploadParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        uploadParams.addRule(CENTER_IN_PARENT);
        mBtnUpload.setLayoutParams(uploadParams);
        addView(mBtnUpload);

    }

    public void setUploadClickListener(OnClickListener listener) {
        mBtnUpload.setOnClickListener(listener);
    }
}
