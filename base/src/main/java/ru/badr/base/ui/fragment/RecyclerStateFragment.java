package ru.badr.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.badr.base.R;
import ru.badr.base.ui.adapter.BaseDelegationAdapter;

/**
 * Created by abadretdinov
 * on 28.06.2018
 */
public class RecyclerStateFragment extends Fragment {
    private View retryView;
    private View loadingView;
    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private BaseDelegationAdapter adapter;


    public void showLoadingState(boolean show) {
        loadingView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showList(boolean show) {
        recyclerView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showEmptyState(boolean show) {
        emptyTextView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void showRetry(boolean show) {
        retryView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public BaseDelegationAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseDelegationAdapter adapter) {
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public TextView getEmptyTextView() {
        return emptyTextView;
    }

    @LayoutRes
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.retryView = view.findViewById(R.id.retry);
        this.loadingView = view.findViewById(R.id.progress_bar);
        this.recyclerView = view.findViewById(R.id.recycler);
        this.emptyTextView = view.findViewById(R.id.empty_text);
    }

    public void setOnRetryClick(View.OnClickListener onRetryClick) {
        this.retryView.setOnClickListener(onRetryClick);
    }
}
