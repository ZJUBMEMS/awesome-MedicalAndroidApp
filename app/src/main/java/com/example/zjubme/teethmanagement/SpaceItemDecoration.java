package com.example.zjubme.teethmanagement;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int verticalSpace;
    private int horizontalSpace;

    public SpaceItemDecoration(int verticalSpace, int horizontalSpace){
        this.horizontalSpace = horizontalSpace;
        this.verticalSpace = verticalSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = horizontalSpace;
        outRect.right = horizontalSpace;

        //item distance except topmost
        if(parent.getChildAdapterPosition(view) != 0){
            outRect.top = verticalSpace;
        }
    }
}
