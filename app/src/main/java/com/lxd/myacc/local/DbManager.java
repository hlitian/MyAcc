package com.lxd.myacc.local;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lxd.myacc.EnApplication;
import com.lxd.myacc.model.Diary;

/**
 * @Author lixd
 */
@Database(entities = {Diary.class}, version = 1, exportSchema = false)
public abstract class DbManager extends RoomDatabase {

    private static volatile DbManager mInstance;

    abstract DiaryDao diaryDao();

    static DbManager getInstance() {
        if (mInstance == null) {
            synchronized (DbManager.class) {
                if (mInstance == null) {
                    mInstance = getDatabase();
                }
            }
        }
        return mInstance;
    }

    @NonNull
    private static DbManager getDatabase() {
        return Room.databaseBuilder(
                EnApplication.get(),
                DbManager.class,
                "enDiary.db"
        ).build();
    }
}
