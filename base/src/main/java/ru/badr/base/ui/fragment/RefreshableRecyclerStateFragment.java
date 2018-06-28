package ru.badr.base.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import ru.badr.base.R;
import ru.badr.base.ui.utils.ColorUtils;

/**
 * @author abadretdinov
 * on 20.02.2018.
 */

public class RefreshableRecyclerStateFragment extends RecyclerStateFragment {
    private SwipeRefreshLayout swipeRefreshLayout;

    public void showRefreshing(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_refreshable;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.swipeRefreshLayout = view.findViewById(R.id.list_container);
        this.swipeRefreshLayout.setColorSchemeColors(getSwipeToRefreshColor(view.getContext()));
    }

    @ColorInt
    protected int[] getSwipeToRefreshColor(Context context) {
        return new int[]{ColorUtils.getAccentColor(context)};
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        this.swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }
}
