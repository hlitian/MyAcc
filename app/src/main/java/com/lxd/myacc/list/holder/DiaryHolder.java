package com.lxd.myacc.list.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxd.myacc.R;
import com.lxd.myacc.model.Diary;

/**
 * @Author lixd
 */
public class DiaryHolder extends RecyclerViewHolder<Diary> {

    private View.OnLongClickListener mOnLongClickListener;
    public DiaryHolder(ViewGroup parent){
        super(parent, R.layout.list_diary_item);
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener){
        this.mOnLongClickListener = onLongClickListener;
    }

    public void onBindView(Diary diary){
        super.onBindView(diary);
        TextView title = itemView.findViewById(R.id.title);
        title.setText(diary.getTitle());
        TextView desc = itemView.findViewById(R.id.desc);
        desc.setText(diary.getDesc());

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v);
            }
        });

    }

}
