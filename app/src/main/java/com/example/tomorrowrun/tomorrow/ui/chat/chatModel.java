package com.example.tomorrowrun.tomorrow.ui.chat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class chatModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public chatModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Chat with us");
    }

    public LiveData<String> getText() {
        return mText;
    }
}