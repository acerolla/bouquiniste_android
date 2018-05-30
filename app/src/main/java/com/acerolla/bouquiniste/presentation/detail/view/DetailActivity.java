package com.acerolla.bouquiniste.presentation.detail.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.detail.presenter.IDetailPresenter;
import com.acerolla.bouquiniste.presentation.edit.view.EditActivity;

import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class DetailActivity extends AppCompatActivity implements IDetailView {

    public static final int REQUEST_CODE_DETAIL = 1;
    public static final String EXTRA_IS_CHANGED = "DetailActivity.EXTRA_IS_CHANGED";
    public static final String EXTRA_ID = "DetailActivity.EXTRA_ID";

    private DetailView mView;

    @Inject
    IDetailPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_detail);
        mView = findViewById(R.id.root_detail);
        mView.initViews();


        DiManager.getDetailComponent().inject(this);
        mPresenter.bindView(this);

        initToolbar();
        setListeners();
    }

    @SuppressLint("RestrictedApi")
    private void initToolbar() {
        setSupportActionBar(mView.getToolbar());

        mView.getToolbar().setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        mView.getToolbar().setTitle("Объявление");
        mView.getToolbar().setNavigationOnClickListener(v -> {
            finish();
        });
    }

    private void setListeners() {
        mView.setFavoriteClickListener(v -> mPresenter.handleFavoriteClick());
    }

    public int getExtraId() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(EXTRA_ID)) {
                return getIntent().getExtras().getInt(EXTRA_ID);
            }
        }

        return -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_favorite) {
            mPresenter.handleFavoriteClick();
        } else if (item.getItemId() == R.id.menu_item_share) {
            mPresenter.handleShareClick();
        } else if (item.getItemId() == R.id.menu_item_on_map) {
            mPresenter.handleOnMapClick();
        }

        return true;
    }

    @Override
    public void setContentData(AdvertData data) {
        mView.setContentData(data);
    }

    @Override
    public void setToolbarTitleParams(int advertId) {
        mView.getToolbar().setTitle(String.format(
                Locale.getDefault(),
                "Объявление №%d",
                advertId));
    }

    @Override
    public void setContentVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setContentVisibility(View.VISIBLE);
        } else {
            mView.setContentVisibility(View.GONE);
        }
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setLoaderVisibility(View.VISIBLE);
        } else {
            mView.setLoaderVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorVisibility(boolean isVisible) {
        if (isVisible) {
            mView.setErrorVisibility(View.VISIBLE);
        } else {
            mView.setErrorVisibility(View.GONE);
        }
    }

    @Override
    public void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, generateText(mView.getData()));
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private String generateText(AdvertData data) {
        String body = "";
        body += "\"Bouquiniste\" хочет продать.\r\n";
        body += data.getAuthor() + "  —  " + data.getTitle() + "\r\n";
        body += String.format(Locale.getDefault(), "За %.0f %s \r\n", data.getPrice(), "\u20BD");
        body += data.getPhone() + " (" + data.getLocation() + ")";

        return body;
    }

    @Override
    public void navigateToMap() {
        Toast.makeText(this, "Не отвечает Google Map API", Toast.LENGTH_SHORT).show();  //TODO:intent
    }

    @Override
    public void changeFavoriteStatus(boolean isFavorite) {
        Menu menu = mView.getToolbar().getMenu();
        if (menu != null && menu.size() > 0 && menu.getItem(0) != null) {
            if (isFavorite) {
                mView.getToolbar().getMenu().getItem(0).setIcon(R.drawable.ic_favorite_white_24dp);
            } else {
                mView.getToolbar().getMenu().getItem(0).setIcon(R.drawable.ic_unfavorite_white_24dp);
            }
        }
    }

    @Override
    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mView = null;

        if (mPresenter != null) {
            mPresenter.release();
        }
        mPresenter = null;
    }
}
