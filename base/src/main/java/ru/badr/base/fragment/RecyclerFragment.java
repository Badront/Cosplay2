package ru.badr.base.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.badr.base.R;
import ru.badr.base.adapter.BaseRecyclerAdapter;
import ru.badr.base.adapter.OnItemClickListener;
import ru.badr.base.adapter.OnItemLongClickListener;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.base.view.DividerItemDecoration;

/**
 * Created by ABadretdinov
 * 23.09.2015
 * 16:59
 */
public abstract class RecyclerFragment<T, VIEW_HOLDER extends BaseViewHolder> extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    final private OnItemClickListener mOnClickListener
            = new OnItemClickListener() {
        public void onItemClick(View v, int position) {
            onRecyclerViewItemClick(v, position);
        }
    };
    final private OnItemLongClickListener mOnLongClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(View view, int position) {
            return onRecyclerViewItemLongClick(view, position);
        }
    };
    protected View mProgressBarHolder;
    protected SwipeRefreshLayout mListContainer;
    private RecyclerView mRecyclerView;
    private View mEmptyView;
    private BaseRecyclerAdapter<T, VIEW_HOLDER> mAdapter;


    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    public void showMessage(String message, String actionMessage, View.OnClickListener action) {
        String resultMessage;
        if ("500 Internal Server Error".equals(message)) {
            resultMessage = getString(R.string.no_data_available_yet);
        } else {
            resultMessage = message;
        }
        Snackbar.make(getView(), resultMessage, Snackbar.LENGTH_LONG).setAction(actionMessage, action).show();
    }

    protected int getLayoutId() {
        return R.layout.recycler_fragment;
    }

    private void ensureList() {
        View root = getView();
        if (root == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (root instanceof RecyclerView) {
            mRecyclerView = (RecyclerView) root;
        } else {
            mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        }
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = getLayoutManager(root.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(itemAnimator);

        if (isNeedDivider()) {
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(root.getContext(), LinearLayoutManager.VERTICAL);
            mRecyclerView.addItemDecoration(itemDecoration);
        }
    }

    public boolean isNeedDivider() {
        return true;
    }

    public BaseRecyclerAdapter<T, VIEW_HOLDER> getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseRecyclerAdapter<T, VIEW_HOLDER> adapter) {
        mAdapter = adapter;
        if (mRecyclerView != null) {
            adapter.setOnItemClickListener(mOnClickListener);
            adapter.setOnItemLongClickListener(mOnLongClickListener);
            mRecyclerView.setAdapter(adapter);
        }
        checkAdapterForEmpty();
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public abstract void onRecyclerViewItemClick(View v, int position);

    public boolean onRecyclerViewItemLongClick(View view, int position) {
        return false;
    }

    public void setRefreshing(boolean refreshing) {
        if (!refreshing) {
            mProgressBarHolder.setVisibility(View.GONE);
            checkAdapterForEmpty();
        }
        mListContainer.setRefreshing(refreshing);
    }

    protected void checkAdapterForEmpty() {
        if (mEmptyView != null) {
            if (mAdapter == null || mAdapter.getItemCount() == 0) {
                mEmptyView.setVisibility(View.VISIBLE);
            } else {
                mEmptyView.setVisibility(View.GONE);
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        mListContainer = (SwipeRefreshLayout) view.findViewById(R.id.listContainer);
        mListContainer.setOnRefreshListener(this);
        TypedValue typedValue = new TypedValue();
        TypedArray a = view.getContext().obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorAccent});
        int colorAccent = a.getColor(0, 0);
        a.recycle();

        mListContainer.setColorSchemeColors(colorAccent);

        mProgressBarHolder = view.findViewById(R.id.progressbar);

        mEmptyView = view.findViewById(R.id.internalEmpty);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ensureList();
        if (mAdapter != null) {
            BaseRecyclerAdapter<T, VIEW_HOLDER> adapter = mAdapter;
            mAdapter = null;
            setAdapter(adapter);
        }
    }

    protected RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
