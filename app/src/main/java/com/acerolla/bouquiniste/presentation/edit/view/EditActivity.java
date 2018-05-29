package com.acerolla.bouquiniste.presentation.edit.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.acerolla.bouquiniste.R;
import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionLayout;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class EditActivity extends AppCompatActivity {

    private FloatingActionLayout mFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);
        mFab = findViewById(R.id.fab);

        EditFragment fragment = new EditFragment();
        Bundle b = new Bundle();
        b.putInt(EditFragment.EXTRA_ID, getExtraId());
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    private int getExtraId() {
        return getIntent().getExtras().getInt(EditFragment.EXTRA_ID);
    }

    public void setCloseClickListener(View.OnClickListener listener) {
        mFab.setOnClickListener(listener);
    }
}
