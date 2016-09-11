package ru.badr.cosplay2.fragment;

import android.os.Bundle;
import android.view.View;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import ru.badr.base.BaseSpiceManager;
import ru.badr.base.fragment.BaseRecyclerFragment;
import ru.badr.base.util.Navigate;
import ru.badr.cosplay2.adapter.FestPhotoCardAdapter;
import ru.badr.cosplay2.adapter.viewholder.FestPhotoCardViewHolder;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.cards.list.ListCard;
import ru.badr.cosplay2.task.TaggedCardsLoadRequest;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 16:20
 */
public abstract class FestSectionListFragment extends BaseRecyclerFragment<ListCard, FestPhotoCardViewHolder> implements RequestListener<ListCard.List> {
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
    public boolean isNeedDivider() {
        return false;
    }

    protected abstract String getSectionPropertyTag();

    @Override
    public void onRefresh() {
        setRefreshing(true);
        mSpiceManager.execute(new TaggedCardsLoadRequest(getActivity(), getSectionPropertyTag()), this);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        setRefreshing(false);
        setAdapter(new FestPhotoCardAdapter(null));

        showMessage(spiceException.getCause() != null ? spiceException.getCause().getMessage() : spiceException.getMessage(), getString(R.string.repeat), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    public void onRequestSuccess(ListCard.List cards) {
        setRefreshing(false);
        setAdapter(new FestPhotoCardAdapter(cards));
    }

    @Override
    public void onRecyclerViewItemClick(View v, int position) {
        Card card = getAdapter().getItem(position);
        Bundle bundle = new Bundle();
        bundle.putString(FestCardInfoFragment.TITLE, getTitle());
        bundle.putSerializable(FestCardInfoFragment.CARD, card);
        Navigate.to(getActivity(), FestCardInfoFragment.class, bundle, false);
    }
}
