package com.example.qual_o_mat.ui.mytests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyTestsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyTestsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mytest fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}