package com.brayanbedritchuk.zerotohero.view.adapter.view_holder;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
