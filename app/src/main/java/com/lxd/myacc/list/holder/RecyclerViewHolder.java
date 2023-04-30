package com.lxd.myacc.list.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author lixd
 */
public class RecyclerViewHolder<D> extends RecyclerView.ViewHolder {

    private D mData;
    public RecyclerViewHolder(ViewGroup pratent, int Resource) {
        super(LayoutInflater.from(pratent.getContext()).inflate(Resource,pratent,false));
    }

    public void onBindView(D data){
        mData = data;
    }

    public D getDate(){
        return mData;
    }
}
