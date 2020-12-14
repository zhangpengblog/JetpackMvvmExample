package com.zhangpengblog.account;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel {
    private MutableLiveData<String> _action = new  MutableLiveData ("");
    private MutableLiveData<String> _title = new  MutableLiveData("");;
    public LiveData<String> action = _action;
    public LiveData<String> title = _title;

    public MutableLiveData<String> get_action() {
        return _action;
    }

    public void set_action(String _action) {
        this._action.setValue (_action);
    }

    public MutableLiveData<String> get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title .setValue (_title);
    }
}
