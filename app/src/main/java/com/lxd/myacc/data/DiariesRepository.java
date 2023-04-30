package com.lxd.myacc.data;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import com.lxd.myacc.local.DiariesLocalDataSource;
import com.lxd.myacc.model.Diary;
import com.lxd.myacc.source.DataCallback;
import com.lxd.myacc.source.DataSource;
import com.lxd.myacc.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author lixd
 * 数据仓库
 */
public class DiariesRepository implements DataSource<Diary> {

    private static volatile DiariesRepository mInstance;
    private final DataSource<Diary> mLocalDataSource;
    private Map<String,Diary> mMemoryCache;

    public DiariesRepository(){
        mMemoryCache = new LinkedHashMap<>();
        mLocalDataSource = DiariesLocalDataSource.get();
    }
    public static DiariesRepository getInstance(){
        if(mInstance == null){
            synchronized (DiariesRepository.class){
                if(mInstance == null){
                    mInstance = new DiariesRepository();
                }
            }
        }
        return mInstance;
    }


    @Override
    public void getAll(@NonNull DataCallback<List<Diary>> callback) {

        if(!CollectionUtils.isEmpty(mMemoryCache)){
            callback.onSuccess(new ArrayList<>(mMemoryCache.values()));
            return;
        }
        mLocalDataSource.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onSuccess(List<Diary> data) {
                updateMemoryCache(data);
                callback.onSuccess(new ArrayList<>(mMemoryCache.values()));

            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void get(@NonNull String id, @NonNull DataCallback<Diary> callback) {

        Diary cacheDiary = getDiaryByIdFromMemory(id);
        if(cacheDiary != null){
            callback.onSuccess(cacheDiary);
            return;
        }

        mLocalDataSource.get(id, new DataCallback<Diary>() {
            @Override
            public void onSuccess(Diary data) {
                mMemoryCache.put(data.getId(),data);
                callback.onSuccess(data);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void update(@NonNull Diary diary) {
        mLocalDataSource.update(diary);
        mMemoryCache.put(diary.getId(),diary);
    }

    @Override
    public void clear() {

        mLocalDataSource.clear();
        mMemoryCache.clear();
    }

    @Override
    public void delete(@NonNull String id) {

        mLocalDataSource.delete(id);
        mMemoryCache.remove(id);
    }
    private void updateMemoryCache(List<Diary> diaryList){
        mMemoryCache.clear();
        for(Diary diary :diaryList){
            mMemoryCache.put(diary.getId(),diary);
        }
    }

    private Diary getDiaryByIdFromMemory(String id){
        if(CollectionUtils.isEmpty(mMemoryCache)){
            return null;
        }else{
            return mMemoryCache.get(id);
        }
    }
}
