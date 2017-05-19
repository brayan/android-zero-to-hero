package br.com.sailboat.zerotohero.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import br.com.sailboat.canoe.helper.BitmapHelper;
import br.com.sailboat.zerotohero.R;

public class SwipeExercise extends ItemTouchHelper.Callback {

    private ItemTouchHelperAdapter adapter;

    private Paint paint;
    private Bitmap bitmapDeleteWhite;

    public SwipeExercise(Context ctx, ItemTouchHelperAdapter adapter) {
        this.adapter = adapter;

        paint = new Paint();
        paint.setColor(ContextCompat.getColor(ctx, R.color.md_blue_grey_200));
        bitmapDeleteWhite = BitmapHelper.getBitmapFromVectorRes(ctx, R.drawable.ic_delete_white_24dp);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return super.getSwipeEscapeVelocity(defaultValue * 3);
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        return super.getSwipeVelocityThreshold(defaultValue * 2);
    }

}
