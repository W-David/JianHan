package com.example.jianhan.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jianhan.util.L;

public class LoadMoreRecyclerView extends RecyclerView {

    private static final String TAG = "LoadMoreRecyclerView";
    private boolean loadingMore = false;
    private boolean isUpSliding  = false;
    public void setUpSliding(boolean state){
        this.isUpSliding = state;
    }
    private onLoadMoreScrollListener mListener;
    public interface onLoadMoreScrollListener{
        void onLoadMore();
    }
    private static class onLoadMoreListener extends OnScrollListener {

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView,dx,dy);
            ((LoadMoreRecyclerView) recyclerView).setUpSliding(dy > 0);
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LoadMoreRecyclerView view = (LoadMoreRecyclerView) recyclerView;
            onLoadMoreScrollListener listener = view.getListener();
            GridLayoutManager layoutManager =  (GridLayoutManager) view.getLayoutManager();
            int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
            int itemCount = layoutManager.getItemCount();
            if(lastVisibleItem >= itemCount - 1 && view.isUpSliding && !view.isLoadingMore()){
                listener.onLoadMore();
                L.i(TAG,"load more: lastVisibleItem = " + lastVisibleItem + ", " + "itemCount = " + itemCount);
            }
        }
    }

    public boolean isLoadingMore() {
        return loadingMore;
    }

    public void setLoadingMore(boolean loadingMore) {
        this.loadingMore = loadingMore;
    }

    public onLoadMoreScrollListener getListener() {
        return mListener;
    }

    public void setLoadMoreListener(onLoadMoreScrollListener listener) {
        this.mListener = listener;
        this.setOnScrollListener(new onLoadMoreListener());
    }

    public LoadMoreRecyclerView(@NonNull Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
