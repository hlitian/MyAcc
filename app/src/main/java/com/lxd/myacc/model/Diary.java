package com.lxd.myacc.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

/**
 * @Author lixd
 */
@Entity(tableName="diary")
public class Diary {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="diaryId")
    private String id;
    private String title;
    @ColumnInfo(name = "desc")
    private String desc;

    @Ignore
    public Diary(@Nullable String title, @Nullable String desc){
       this(title,desc, UUID.randomUUID().toString());

    }
    public Diary(@Nullable String title,@Nullable String desc,@NonNull  String id){
        this.desc = desc;
        this.id = id;
        this.title = title;

    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
