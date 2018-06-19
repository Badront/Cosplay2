package ru.badr.cosplay2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.tonicartos.superslim.LayoutManager;

import ru.badr.base.BaseSpiceManager;
import ru.badr.base.adapter.viewholder.BaseViewHolder;
import ru.badr.base.entity.LineItem;
import ru.badr.base.fragment.BaseRecyclerFragment;
import ru.badr.base.util.Navigate;
import ru.badr.cosplay2.adapter.MembersAdapter;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.cards.Topic;
import ru.badr.cosplay2.task.SectionedCardsLoadRequest;
import ru.badr.opencon.R;
import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator;
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;

/**
 * Created by ABadretdinov
 * 16.10.2015
 * 15:47
 */
public class MembersList extends BaseRecyclerFragment<Object, BaseViewHolder> implements RequestListener<Topic.List> {
    VerticalRecyclerViewFastScroller mFastScroller;
    SectionTitleIndicator mSectionTitleIndicator;
    private SpiceManager mSpiceManager = new BaseSpiceManager(UncachedSpiceService.class);

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
    protected int getLayoutId() {
        return R.layout.members_list;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFastScroller = (VerticalRecyclerViewFastScroller) view.findViewById(R.id.fast_scroller);

        mSectionTitleIndicator = (SectionTitleIndicator) view.findViewById(R.id.fast_scroller_section_title_indicator);


        RecyclerView recyclerView = getRecyclerView();
        mFastScroller.setRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(mFastScroller.getOnScrollListener());

        mFastScroller.setSectionIndicator(mSectionTitleIndicator);

        setRecyclerViewLayoutManager(recyclerView);
    }


    @Override
    public void onRefresh() {
        mSectionTitleIndicator.setVisibility(View.GONE);
        mFastScroller.setVisibility(View.GONE);
        setRefreshing(true);
        mSpiceManager.execute(new SectionedCardsLoadRequest(getActivity()), this);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        setRefreshing(false);
        setAdapter(new MembersAdapter(null));

        showMessage(spiceException.getCause() != null ? spiceException.getCause().getMessage() : spiceException.getMessage(), getString(R.string.repeat), new View.OnClickListener() {
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
        setRecyclerViewLayoutManager(getRecyclerView());
        if (getAdapter().getItemCount() == 0) {
            mSectionTitleIndicator.setVisibility(View.GONE);
            mFastScroller.setVisibility(View.GONE);
        } else {
            mSectionTitleIndicator.setVisibility(View.VISIBLE);
            mFastScroller.setVisibility(View.VISIBLE);
        }
    }
    //todo fast scroll from RecyclerView itself https://android.jlelse.eu/fast-scrolling-with-recyclerview-2b89d4574688

    @Override
    public void onRecyclerViewItemClick(View v, int position) {
        LineItem lineItem = (LineItem) getAdapter().getItem(position);
        Card card = (Card) lineItem.data;
        Bundle bundle = new Bundle();
        bundle.putString(FestCardInfoFragment.TITLE, card.getTopicName());
        bundle.putSerializable(FestCardInfoFragment.CARD, card);
        Navigate.to(getActivity(), FestCardInfoFragment.class, bundle, false);
    }

    /**
     * Set RecyclerView's LayoutManager
     */
    public void setRecyclerViewLayoutManager(RecyclerView recyclerView) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition =
                    ((LayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        }

        recyclerView.scrollToPosition(scrollPosition);
    }
}
