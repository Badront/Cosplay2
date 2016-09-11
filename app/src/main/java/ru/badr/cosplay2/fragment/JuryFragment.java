package ru.badr.cosplay2.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.tonicartos.superslim.LayoutManager;

import org.apache.commons.lang3.StringUtils;

import ru.badr.base.BaseSpiceManager;
import ru.badr.base.adapter.OnItemClickListener;
import ru.badr.base.entity.LineItem;
import ru.badr.base.fragment.BaseFragment;
import ru.badr.base.util.retrofit.LocalSpiceService;
import ru.badr.cosplay2.adapter.JuryAndOrgsAdapter;
import ru.badr.cosplay2.api.JuryEntity;
import ru.badr.cosplay2.api.JurySectionEntity;
import ru.badr.cosplay2.task.JuryLoadRequest;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 18:16
 */
public class JuryFragment extends BaseFragment implements RequestListener<JurySectionEntity.List>, OnItemClickListener {
    protected SpiceManager mSpiceManager = new BaseSpiceManager(LocalSpiceService.class);

    protected View mProgressBarHolder;
    private RecyclerView mRecyclerView;
    private JuryAndOrgsAdapter mAdapter;

    @Override
    protected String getTitle() {
        return getString(R.string.jury);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mSpiceManager.isStarted()) {
            Context context = getActivity();
            mSpiceManager.start(context.getApplicationContext());
            mSpiceManager.execute(new JuryLoadRequest(context), this);
        }
    }

    @Override
    public void onDestroy() {
        if (mSpiceManager.isStarted()) {
            mSpiceManager.shouldStop();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jury_n_orgs_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(itemAnimator);

        mProgressBarHolder = view.findViewById(ru.badr.base.R.id.progressbar);

        return view;
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        mProgressBarHolder.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        showMessage(spiceException.getLocalizedMessage());
    }

    @Override
    public void onRequestSuccess(JurySectionEntity.List jurySectionEntities) {
        mProgressBarHolder.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new JuryAndOrgsAdapter(jurySectionEntities);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        LineItem lineItem = (LineItem) mAdapter.getItem(position);
        JuryEntity entity = (JuryEntity) lineItem.data;
        if (!StringUtils.isEmpty(entity.getVk())) {
            Uri uri = Uri.parse(entity.getVk());
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }
}
