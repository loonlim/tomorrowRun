package com.example.tomorrow.ui.product_progress;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class ProductProgressModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ProductProgressModel() {

        mText = new MutableLiveData<>();
        mText.setValue("In Progressing...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}