package com.example.rajnish.mygmail;

import android.support.v7.widget.RecyclerView;

public interface RecycleViewItemTochHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder , int direction  , int position);
}
