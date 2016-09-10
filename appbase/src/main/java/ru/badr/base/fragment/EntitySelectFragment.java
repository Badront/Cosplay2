package ru.badr.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.io.Serializable;
import java.util.List;

import ru.badr.base.BaseSpiceManager;
import ru.badr.base.R;
import ru.badr.base.activity.BaseActivity;
import ru.badr.base.adapter.BaseRecyclerAdapter;
import ru.badr.base.adapter.EntitySelectAdapter;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.base.util.Navigate;
import ru.badr.base.util.retrofit.TaskRequest;

/**
 * Created by ABadretdinov
 * 30.06.2015
 * 16:54
 */
public class EntitySelectFragment<T extends Serializable, VIEW_HOLDER extends BaseViewHolder> extends BaseRecyclerFragment<T, VIEW_HOLDER> implements SearchView.OnQueryTextListener, RequestListener<List> {
    protected SpiceManager mSpiceManager = new BaseSpiceManager(UncachedSpiceService.class);
    Handler mHandler = new Handler();
    private EntityLoader<T> mLoader;
    private String mTitle;
    private String mSearch;
    private Filter mFilter;
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mLoader != null && !mLoader.supportsPreLoading()) {
                onRefresh();
            } else if (mFilter != null) {
                mFilter.filter(mSearch);
                checkAdapterForEmpty();
            }
        }
    };

    @Override
    public void onStart() {
        if (!mSpiceManager.isStarted()) {
            mSpiceManager.start(getActivity());
            if (mLoader != null) {
                if (!mLoader.supportsPreLoading()) {
                    onRefresh();
                } else {
                    showPreloaded();
                }
            }
        }
        super.onStart();
    }

    @Override
    public void onDestroy() {
        if (mSpiceManager.isStarted()) {
            mSpiceManager.shouldStop();
        }
        super.onDestroy();
    }

    public void setLoader(EntityLoader<T> loader) {
        this.mLoader = loader;
    }

    @Override
    protected String getTitle() {
        return mTitle;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint(mTitle);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        initArguments();
    }

    @SuppressWarnings("unchecked")
    protected void initArguments() {
        mTitle = getArguments().getString(Navigate.PARAM_TITLE);
        ActionBar ab = ((BaseActivity) getActivity()).getSupportActionBar();
        if (ab != null) {
            ab.setTitle(mTitle);
        }
        setLoader((EntityLoader<T>) getArguments().getSerializable(Navigate.PARAM_LOADER));
        if (getArguments().containsKey(Navigate.PARAM_EMPTYTEXT)) {
            ((TextView) getEmptyView()).setText(getArguments().getString(Navigate.PARAM_EMPTYTEXT));
        } else {
            ((TextView) getEmptyView()).setText(R.string.no_matches_found);
        }
    }

    @Override
    public void onRecyclerViewItemClick(View v, int position) {
        Intent intent = new Intent();
        T entity = getAdapter().getItem(position);
        intent.putExtra(Navigate.PARAM_ENTITY, entity);
        final Activity activity = getActivity();
        if (activity != null) {
            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        }
    }

    protected BaseRecyclerAdapter getNewAdapter(List<T> data) {
        return new EntitySelectAdapter<T>(data);
    }

    @Override
    public void onRefresh() {
        Context context = getActivity();
        if (context != null) {
            setRefreshing(true);
            mSpiceManager.execute(new LoadRequest(context.getApplicationContext(), mSearch), this);
        }
    }

    protected void showPreloaded() {
        Context context = getActivity();
        if (context != null) {
            mSpiceManager.execute(new PreloadRequest(context.getApplicationContext(), mSearch), this);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String text) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String text) {
        mSearch = text;
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, 1000);
        return true;
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        setRefreshing(false);
        showMessage(spiceException.getCause() != null ? spiceException.getCause().getMessage() : spiceException.getMessage(), getString(R.string.repeat), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onRequestSuccess(List list) {
        BaseRecyclerAdapter adapter = getNewAdapter(list);
        if ((mLoader == null || mLoader.supportsPreLoading()) && (adapter instanceof Filterable)) {
            mFilter = ((Filterable) adapter).getFilter();
            mFilter.filter(mSearch);
            checkAdapterForEmpty();
        }
        setAdapter(adapter);
        setRefreshing(false);
    }

    public class PreloadRequest extends TaskRequest<List> {
        private Context mContext;
        private String mSearch;

        public PreloadRequest(Context context, String search) {
            super(List.class);
            this.mContext = context;
            this.mSearch = search;
        }

        @Override
        public List loadData() throws Exception {
            List list = mLoader.getEntities(mContext, mSearch);
            if (list == null || list.isEmpty()) {
                mLoader.loadEntities(mContext);
                list = mLoader.getEntities(mContext, mSearch);
            }
            return list;
        }
    }

    public class LoadRequest extends TaskRequest<List> {
        private Context mContext;
        private String mSearch;

        public LoadRequest(Context context, String search) {
            super(List.class);
            this.mContext = context;
            this.mSearch = search;
        }

        @Override
        public List loadData() throws Exception {

            if (mLoader.supportsPreLoading()) {
                mLoader.loadEntities(mContext);
                return mLoader.getEntities(mContext, mSearch);
            } else {
                return mLoader.getEntities(mContext, mSearch);
            }
        }
    }
}
