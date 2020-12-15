package com.zhangpengblog.jetpackmvvmexample.ui.fragment.first;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirstViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _isHoliday = new MutableLiveData<> ();
    public LiveData<Boolean> isHoliday = _isHoliday;

    public void setIsHoliday(Boolean isHoliday) {
        this._isHoliday.setValue (isHoliday);
    }
}