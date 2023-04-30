package com.lxd.myacc;

import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;

import com.lxd.myacc.data.DiariesRepository;
import com.lxd.myacc.list.DiariesAdapter;
import com.lxd.myacc.model.Diary;
import com.lxd.myacc.source.DataCallback;

import java.util.List;

/**
 * @Author lixd
 */
public class DiariesViewModel extends ViewModel {


    public final ObservableList<Diary> data = new ObservableArrayList<Diary>();
    public final ObservableField<DiariesAdapter> listAdapter = new ObservableField<>();

    private final ToastInfo mToastInfo = new ToastInfo();
    private final DiariesRepository mDiariesRepository;

    public DiariesViewModel(){
        mDiariesRepository = DiariesRepository.getInstance();
    }

    public void start(){
        initAdapter();
        loadDiaries();
    }

    private void initAdapter() {

        DiariesAdapter diariesAdapter = new DiariesAdapter();
        diariesAdapter.setOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(View v, Diary data) {
                updateDiary(data);
                return false;

            }
        });
        listAdapter.set(diariesAdapter);

    }

    public ToastInfo getToastInfo(){
        return mToastInfo;
    }

    public void loadDiaries(){
        mDiariesRepository.getAll(new DataCallback<List<Diary>>(){

            @Override
            public void onSuccess(List<Diary> data) {
                updateDiaries(data);
            }

            @Override
            public void onError() {
                mToastInfo.setValue(EnApplication.get().getString(R.string.error));
            }
        });


    }

    private void updateDiaries(List<Diary> data) {
        mToastInfo.setValue(EnApplication.get().getString(R.string.app_name));
    }

    public void updateDiary(Diary diary){
        mToastInfo.setValue(EnApplication.get().getString(R.string.app_name));
    }



}
