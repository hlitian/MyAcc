package com.lxd.myacc;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * @Author lixd
 */
public class ToastInfo extends MutableLiveData<String> {

    public void observe(LifecycleOwner owner,final ToastObserver observer){
        super.observe(owner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s == null){
                    return;
                }
                observer.onNewMessage(s);
            }
        });

    }

    public interface ToastObserver{
        void onNewMessage(String toastInfo);
    }

}
