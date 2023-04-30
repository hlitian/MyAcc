package com.lxd.myacc.list;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lxd.myacc.list.holder.DiaryHolder;
import com.lxd.myacc.model.Diary;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lixd
 */
public class DiariesAdapter extends RecyclerView.Adapter<DiaryHolder>{

    private List<Diary> mDiaries = new ArrayList<>();
    private OnLongClickListener<Diary> mOnLongClickListener;

    public void update(List<Diary> diaries){
        mDiaries = diaries;
        notifyDataSetChanged();
    }

    public void setOnLongClickListener(OnLongClickListener<Diary> onLongClickListener){
        this.mOnLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new DiaryHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryHolder holder, int position) {
            final Diary diary = mDiaries.get(position);
            holder.onBindView(diary);
            holder.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnLongClickListener.onLongClick(v,diary);
                }
            });

    }

    @Override
    public int getItemCount() {
        return mDiaries.size();
    }

    public interface OnLongClickListener<T>{
        boolean onLongClick(View v,T data);
    }

}
