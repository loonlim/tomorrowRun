package com.example.tomorrow.ui.customerinfo;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomerInfomationViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;


    public CustomerInfomationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Customer Information");


    }

    public LiveData<String> getText() {
        return mText;
    }
}
