package com.example.rajnish.mygmail;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public class RecycleViewItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private MainActivity listener;

    public RecycleViewItemTouchHelper(int dragDirs, int swipeDirs, MainActivity listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener != null) {
            listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
        }


    }




    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        View forGroundView = ((GmailAdapter.Viewholder) viewHolder).foreground;
        super.clearView(recyclerView, viewHolder);
        getDefaultUIUtil().clearView(forGroundView);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            View forGroundView = ((GmailAdapter.Viewholder) viewHolder).foreground;

            getDefaultUIUtil().onSelected(forGroundView);
        }

    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View forGroundView = ((GmailAdapter.Viewholder) viewHolder).foreground;
        getDefaultUIUtil().onDraw(c, recyclerView, forGroundView, dX, dY, actionState, isCurrentlyActive);
        // super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View forGroundView = ((GmailAdapter.Viewholder) viewHolder).foreground;
        getDefaultUIUtil().onDrawOver(c, recyclerView, forGroundView, dX, dY, actionState, isCurrentlyActive);
        // super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
