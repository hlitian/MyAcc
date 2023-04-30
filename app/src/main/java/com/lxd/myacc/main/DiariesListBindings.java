package com.lxd.myacc.main;

import androidx.recyclerview.widget.RecyclerView;

import com.lxd.myacc.list.DiariesAdapter;
import com.lxd.myacc.model.Diary;

import java.util.List;

/**
 * @Author lixd
 */
public class DiariesListBindings {

    public static void setData(RecyclerView recyclerView, List<Diary> data){
        DiariesAdapter adapter = (DiariesAdapter) recyclerView.getAdapter();
        if(adapter == null){
            return;
        }
        adapter.update(data);
    }
}
