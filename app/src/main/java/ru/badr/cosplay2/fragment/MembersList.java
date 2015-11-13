package ru.badr.cosplay2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.tonicartos.superslim.LayoutManager;

import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.base.entity.LineItem;
import ru.badr.base.fragment.BaseRecyclerFragment;
import ru.badr.base.util.Navigate;
import ru.badr.cosplay2.adapter.MembersAdapter;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.cards.Topic;
import ru.badr.cosplay2.task.SectionedCardsLoadRequest;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 15:47
 */
public class MembersList extends BaseRecyclerFragment<Object, BaseViewHolder> implements RequestListener<Topic.List> {
    private SpiceManager mSpiceManager = new SpiceManager(UncachedSpiceService.class);


    @Override
    public void onStart() {
        if (!mSpiceManager.isStarted()) {
            mSpiceManager.start(getActivity().getApplicationContext());
            onRefresh();
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

    @Override
    protected RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new LayoutManager(context);
    }

    @Override
    public boolean isNeedDivider() {
        return false;
    }


    @Override
    protected String getTitle() {
        return getString(R.string.members);
    }

    @Override
    public void onRefresh() {
        setRefreshing(true);
        mSpiceManager.execute(new SectionedCardsLoadRequest(getActivity().getApplicationContext()), this);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        setRefreshing(false);
        setAdapter(new MembersAdapter(null));

        showMessage(spiceException.getCause().getMessage(), getString(R.string.repeat), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    public void onRequestSuccess(Topic.List topics) {
        setRefreshing(false);
        setAdapter(new MembersAdapter(topics));
    }

    @Override
    public void onRecyclerViewItemClick(View v, int position) {
        LineItem lineItem = (LineItem) getAdapter().getItem(position);
        Card card = (Card) lineItem.data;
        Bundle bundle = new Bundle();
        bundle.putString(FestCardInfoFragment.TITLE, card.getTopicName());
        bundle.putSerializable(FestCardInfoFragment.CARD, card);
        Navigate.to(getActivity(), FestCardInfoFragment.class, bundle, false);
    }
}
