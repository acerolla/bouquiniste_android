package com.acerolla.bouquiniste.presentation.adding.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.adding.presenter.IAddingPresenter;
import com.acerolla.bouquiniste.presentation.category.view.CategoryActivity;
import com.acerolla.bouquiniste.presentation.detail.view.DetailActivity;
import com.acerolla.bouquiniste.presentation.main.view.IMainView;
import com.acerolla.bouquiniste.presentation.main.view.MainActivity;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditDescription;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditLocation;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditPhone;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditPrice;
import com.acerolla.bouquiniste.presentation.utils.dialogs.DialogEditTitle;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingFragment extends Fragment implements IAddingView {

    private static final int READ_EXTERNAL_STORAGE_PERMISSION = 346;


    private static final int REQUEST_FILE_CHOOSER = 42;

    private boolean mTitle = false;
    private boolean mPrice = false;
    private boolean mDescription = false;
    private boolean mCategory = false;
    private boolean mPhone = false;
    private boolean mLocation = false;

    private AddingView mView;

    @Inject
    IAddingPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mView = (AddingView) inflater.inflate(R.layout.fragment_adding, container, false);
        mView.initViews();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DiManager.getAddingComponent().inject(this);
        mPresenter.bindView(this);

        ((MainActivity) getActivity()).setTitle("Новое объявление");
        setListeners();
    }

    private void setListeners() {
        mView.setAddClickListener(v -> {
            if (mTitle && mPrice && mDescription && mCategory && mPhone && mLocation) {
                mPresenter.handleAddClick();
            } else {
                Toast.makeText(getContext(), "Не все поля заполнены!", Toast.LENGTH_LONG).show();
            }
        });
        mView.setImageClickListener(v -> mPresenter.handleUploadClick());
        mView.setFieldsClickListener(v -> {
            switch (v.getId()) {
                case R.id.tv_title:
                case R.id.tv_author:
                    DialogEditTitle dialog1 = new DialogEditTitle();
                    dialog1.setListener((title, author) -> {
                        mView.setTitle(title);
                        mView.setAuthor(author);
                        mTitle = true;
                    });
                    dialog1.show(getActivity().getFragmentManager(), "");
                    break;
                case R.id.tv_price:
                    DialogEditPrice dialog2 = new DialogEditPrice();
                    dialog2.setListener(price -> {mView.setPrice(price); mPrice = true;});
                    dialog2.show(getActivity().getFragmentManager(), "");
                    break;
                case R.id.ll_description:
                    DialogEditDescription dialog3 = new DialogEditDescription();
                    dialog3.setListener(price -> {mView.setDescription(price); mDescription = true;});
                    dialog3.show(getActivity().getFragmentManager(), "");
                    break;
                case R.id.ll_category:
                    mPresenter.handleCategoryButtonClicked();
                    mCategory = true;
                    break;
                case R.id.ll_phone:
                    DialogEditPhone dialog4 = new DialogEditPhone();
                    dialog4.setListener(price -> {mView.setPhone(price); mPhone = true;});
                    dialog4.show(getActivity().getFragmentManager(), "");
                    break;
                case R.id.ll_location:
                    DialogEditLocation dialog5 = new DialogEditLocation();
                    dialog5.setListener(price -> {mView.setLocation(price); mLocation = true;});
                    dialog5.show(getActivity().getFragmentManager(), "");
                    break;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FILE_CHOOSER && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                mPresenter.handleFileChoosed(data.getData());
            }
        } else if (requestCode == DetailActivity.REQUEST_CODE_DETAIL) {
            mPresenter.handleDetailFinished();
        } else if (requestCode == CategoryActivity.REQUEST_CATEGORY && resultCode == Activity.RESULT_OK) {
            mPresenter.handleCategorySelected(
                    data.getExtras().getInt(CategoryActivity.EXTRA_CATEGORY_ID),
                    data.getExtras().getString(CategoryActivity.EXTRA_CATEGORY_TITLE));
        }
    }

    @Override
    public void showChooseFileActivity() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        READ_EXTERNAL_STORAGE_PERMISSION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_FILE_CHOOSER);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showChooseFileActivity();
                }
            }

        }
    }

    @Override
    public AdvertData collectData() {
        return mView.collectData();
    }

    @Override
    public void navigateToDetail(int advertId) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_ID, advertId);
        startActivityForResult(intent, DetailActivity.REQUEST_CODE_DETAIL);
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
    public void changeFragment() {
        if (getActivity() != null) {
            ((IMainView) getActivity()).showAdverts();
        }
    }

    @Override
    public void navigateToCategories() {
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        startActivityForResult(intent, CategoryActivity.REQUEST_CATEGORY);
    }

    @Override
    public void showCategory(int id, String title) {
        mView.setCategory(id, title);
    }

    @Override
    public void setImage(Uri uri) {
        mView.setImage(uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView = null;

        if (mPresenter != null) {
            mPresenter.release();
        }
        mPresenter = null;
    }
}
