package com.acerolla.bouquiniste.presentation.utils.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.presentation.adding.view.AddingFragment;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;

/**
 * Created by Evgeniy Solovev
 * Date: 04.06.2018
 * Email: solevur@gmail.com
 */
public class DialogEditTitle extends DialogFragment {

    public static final String CALLING_FRAGMENT = "DialogEditTitle.CALLING_FRAGMENT";

    private Listener mListener;

    private Button mBtnConfirm;
    private EditText mEtTitle;
    private EditText mEtAuthor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_title, container, false);

        mBtnConfirm = v.findViewById(R.id.btn_confirm);
        mEtTitle = v.findViewById(R.id.et_title);
        mEtAuthor = v.findViewById(R.id.et_author);

        return v;
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * 0.90), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnConfirm.setOnClickListener(v -> {
            if (!mEtAuthor.getText().toString().isEmpty() && !mEtTitle.getText().toString().isEmpty()) {
                mListener.onClick(mEtTitle.getText().toString(), mEtAuthor.getText().toString());
                dismiss();
            }
        });
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public interface Listener {
        void onClick(String title, String author);
    }
}
