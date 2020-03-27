package com.example.jianhan.ui.adapter;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jianhan.util.L;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V>{

    private static final String TAG = "BaseAdapter";

    protected final List<T> temp; //perv data
    protected final List<T> dataSet; //now data

    public BaseAdapter(List<T> dataSet){
        this.dataSet = dataSet;
        temp = new ArrayList<>(dataSet);
    }

    protected abstract boolean areItemsTheSame(T oldItem,T newItem);

    protected abstract boolean areContentsTheSame(T oldItem,T newItem);

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    void notifyDiff(){
        DiffUtil.DiffResult  diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                L.i(TAG,"old Size: " + temp.size());
                return temp.size();
            }

            @Override
            public int getNewListSize() {
                L.i(TAG,"new Size: " + temp.size());
                return dataSet.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return BaseAdapter.this.areItemsTheSame(temp.get(oldItemPosition),dataSet.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return BaseAdapter.this.areContentsTheSame(temp.get(oldItemPosition),dataSet.get(newItemPosition));
            }
        });

        diffResult.dispatchUpdatesTo(this);
        temp.clear();
        temp.addAll(dataSet);
    }
}

