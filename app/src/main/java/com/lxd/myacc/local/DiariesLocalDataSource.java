package com.lxd.myacc.local;

import androidx.annotation.NonNull;

import com.lxd.myacc.mock.MockDiaries;
import com.lxd.myacc.model.Diary;
import com.lxd.myacc.source.DataCallback;
import com.lxd.myacc.source.DataSource;
import com.lxd.myacc.util.ThreadUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author lixd
 */
public class DiariesLocalDataSource implements DataSource<Diary> {

    private static volatile DiariesLocalDataSource mInstance;//本地数据源
    private DiaryDao mDao;
    private final Executor mIOThread;

    public DiariesLocalDataSource(){

        mDao = DbManager.getInstance().diaryDao();
        mIOThread = Executors.newSingleThreadExecutor();
        mockData();
    }

    private void mockData() {
        Map<String,Diary> diaryMap = MockDiaries.mock();

        for(Diary value: diaryMap.values()){
            add(value);
        }
    }
    //获取日记本地数据源类的单例
    public static DiariesLocalDataSource get(){
        if(mInstance == null){
            synchronized (DiariesLocalDataSource.class){
                if(mInstance == null){
                    mInstance = new DiariesLocalDataSource();
                }
            }
        }
        return mInstance;
    }

    private void add(Diary value) {

        mIOThread.execute(new Runnable() {
            @Override
            public void run() {
                mDao.add(value);
            }
        });
    }



    @Override
    public void getAll(@NonNull DataCallback<List<Diary>> callback) {

        mIOThread.execute(new Runnable() {
            @Override
            public void run() {
                final List<Diary> diaries = mDao.getAll();
                notifyUIAfterGetAll(diaries,callback);
            }
        });
    }

    @Override
    public void get(@NonNull String id, @NonNull DataCallback<Diary> callback) {

        mIOThread.execute(new Runnable() {
            @Override
            public void run() {
                Diary diary = mDao.get(id);
                notifyUIAfterGet(diary,callback);
            }
        });
    }

    private void notifyUIAfterGetAll(List<Diary> diaries,DataCallback<List<Diary>> callback){
        ThreadUtils.runOnUI(new Runnable() {
            @Override
            public void run() {
                if(diaries.isEmpty()){
                    callback.onError();
                }else{
                    callback.onSuccess(diaries);
                }
            }
        });
    }

    private void notifyUIAfterGet(Diary diary, DataCallback<Diary> callback) {
        ThreadUtils.runOnUI(new Runnable() {
            @Override
            public void run() {
                if(diary !=null){
                    callback.onSuccess(diary);
                }else{
                    callback.onError();
                }
            }
        });
    }

    @Override
    public void update(@NonNull Diary diary) {

        mIOThread.execute(new Runnable() {
            @Override
            public void run() {
                mDao.update(diary);
            }
        });
    }

    @Override
    public void clear() {

        mIOThread.execute(new Runnable() {
            @Override
            public void run() {
                mDao.deleteAll();
            }
        });
    }

    @Override
    public void delete(@NonNull String id) {

        mIOThread.execute(new Runnable() {
            @Override
            public void run() {
                mDao.delete(id);
            }
        });
    }
}
