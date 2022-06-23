package com.example.tomorrow.ui.contect;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class contectModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public contectModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Contect of Us");
    }

    public LiveData<String> getText() {
        return mText;
    }
}