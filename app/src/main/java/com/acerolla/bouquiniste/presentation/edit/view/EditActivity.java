package com.acerolla.bouquiniste.presentation.edit.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.ResultListener;
import com.robertlevonyan.views.customfloatingactionbutton.FloatingActionLayout;

import java.util.Locale;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class EditActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FloatingActionLayout mFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);

        initViews();

        EditFragment fragment = new EditFragment();
        Bundle b = new Bundle();
        b.putInt(EditFragment.EXTRA_ID, getExtraId());
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    private void initViews() {
        mFab = findViewById(R.id.fab);

        mToolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        mToolbar.setTitle(String.format(Locale.getDefault(), "Объявление №%s", getExtraId()));
        mToolbar.setNavigationOnClickListener(v -> {
            EditActivity.this.setResult(RESULT_CANCELED);
            EditActivity.this.finish();
        });
    }

    private int getExtraId() {
        return getIntent().getExtras().getInt(EditFragment.EXTRA_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_share) {
            if (mShareListener != null) {
                mShareListener.onShare();
            }
        }

        return true;
    }

    public void setCloseClickListener(View.OnClickListener listener) {
        mFab.setOnClickListener(listener);
    }

    public void setShareClickListener(ShareClickListener listener) {
        mShareListener = listener;
    }

    public void setFabVisibility(int visibility) {
        mFab.setVisibility(visibility);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShareListener = null;
        mToolbar = null;
        mFab = null;
    }

    private ShareClickListener mShareListener;

    interface ShareClickListener {
        void onShare();
    }
}
