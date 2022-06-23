package com.example.tomorrowrun.tomorrow.ui.product_check;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductCheckViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ProductCheckViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Product Infomation");
    }

    public LiveData<String> getText() {
        return mText;
    }
}