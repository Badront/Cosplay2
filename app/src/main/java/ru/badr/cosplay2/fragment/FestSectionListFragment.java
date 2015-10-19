package ru.badr.cosplay2.fragment;

import android.view.View;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import ru.badr.base.fragment.BaseRecyclerFragment;
import ru.badr.cosplay2.R;
import ru.badr.cosplay2.adapter.FestPhotoCardAdapter;
import ru.badr.cosplay2.adapter.viewholder.FestPhotoCardViewHolder;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.task.TaggedCardsLoadRequest;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 16:20
 */
public abstract class FestSectionListFragment extends BaseRecyclerFragment<Card, FestPhotoCardViewHolder> implements RequestListener<Card.List> {
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
    public boolean isNeedDivider() {
        return false;
    }

    protected abstract String getSectionPropertyTag();

    @Override
    public void onRefresh() {
        mSpiceManager.execute(new TaggedCardsLoadRequest(getActivity().getApplicationContext(), getSectionPropertyTag()), this);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        setRefreshing(false);
        showMessage(spiceException.getLocalizedMessage(), getString(R.string.repeat), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    public void onRequestSuccess(Card.List cards) {
        setRefreshing(false);
        setAdapter(new FestPhotoCardAdapter(cards));
    }
}
