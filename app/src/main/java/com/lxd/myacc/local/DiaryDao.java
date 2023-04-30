package com.lxd.myacc.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.lxd.myacc.model.Diary;

import java.util.List;

/**
 * @Author lixd
 */
@Dao
public interface DiaryDao {

    @Query("SELECT*FROM Diary")
    List<Diary> getAll();

    @Query("SELECT*FROM Diary WHERE diaryId=:id")
    Diary get(String id);

    // 更新某个数据
    @Update
    int update(Diary diary);

    // 清空所有数据
    @Query("DELETE FROM Diary")
    void deleteAll();

    // 删除某个数据
    @Query("DELETE FROM Diary WHERE diaryId = :id")
    int delete(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Diary diary);
}
