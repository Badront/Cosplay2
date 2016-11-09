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
import ru.badr.cosplay2.adapter.ScheduleAdapter;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.schedule.ScheduleNode;
import ru.badr.cosplay2.task.ScheduleLoadRequest;
import ru.badr.opencon.R;
import xyz.danoz.recyclerviewfastscroller.sectionindicator.title.SectionTitleIndicator;
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;

/**
 * Created by Badr on 16.11.2015.
 */
public class ScheduleFragment extends BaseRecyclerFragment<Object, BaseViewHolder> implements RequestListener<ScheduleNode.List> {
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
    protected RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new LayoutManager(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.schedule_layout;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.schedule);
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
        mSpiceManager.execute(new ScheduleLoadRequest(getActivity()), this);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        setRefreshing(false);
        setAdapter(new ScheduleAdapter(null));

        showMessage(spiceException.getCause() != null ? spiceException.getCause().getMessage() : spiceException.getMessage(), getString(R.string.repeat), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    public void onRequestSuccess(ScheduleNode.List nodes) {
        setRefreshing(false);
        setAdapter(new ScheduleAdapter(nodes));
        setRecyclerViewLayoutManager(getRecyclerView());
        if (getAdapter().getItemCount() == 0) {
            mSectionTitleIndicator.setVisibility(View.GONE);
            mFastScroller.setVisibility(View.GONE);
        } else {
            mSectionTitleIndicator.setVisibility(View.VISIBLE);
            mFastScroller.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRecyclerViewItemClick(View v, int position) {
        LineItem lineItem = (LineItem) getAdapter().getItem(position);
        ScheduleNode node = (ScheduleNode) lineItem.data;
        if (node.getCard() != null) {
            Card card = node.getCard();
            Bundle bundle = new Bundle();
            bundle.putString(FestCardInfoFragment.TITLE, card.getTopicName());
            bundle.putSerializable(FestCardInfoFragment.CARD, card);
            Navigate.to(getActivity(), FestCardInfoFragment.class, bundle, false);
        }
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
