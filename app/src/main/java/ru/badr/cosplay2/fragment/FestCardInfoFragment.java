package ru.badr.cosplay2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import ru.badr.base.fragment.BaseFragment;
import ru.badr.cosplay2.R;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.api.cards.info.json.ReqValuesHolder;
import ru.badr.cosplay2.task.CardResultLoadRequest;
import ru.badr.cosplay2.view.FestCardImageFieldView;
import ru.badr.cosplay2.view.FestCardLinkFieldView;
import ru.badr.cosplay2.view.FestCardTextFieldView;
import ru.badr.cosplay2.view.FestCardUserFieldView;

/**
 * Created by ABadretdinov
 * 20.10.2015
 * 11:07
 */
public class FestCardInfoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RequestListener<ReqValuesHolder.List> {
    public static final String TITLE = "title";
    public static final String CARD = "card";

    private View mProgressBar;
    private View mContentView;
    private TextView mTitleView;
    private LinearLayout mFieldsHolder;

    private SpiceManager mSpiceManager = new SpiceManager(UncachedSpiceService.class);

    private Card mCard;

    @Override
    protected String getTitle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle.getString(TITLE);
        }/*topic.title*/
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mSpiceManager.isStarted()) {
            mSpiceManager.start(getActivity().getApplicationContext());
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        mContentView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mSpiceManager.execute(new CardResultLoadRequest(getActivity().getApplicationContext(), mCard), this);
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
        View view = inflater.inflate(R.layout.fest_card_info_layout, container, false);

        mContentView = view.findViewById(R.id.scrollView);
        mProgressBar = view.findViewById(ru.badr.base.R.id.progressbar);
        mTitleView = (TextView) view.findViewById(R.id.title);
        mFieldsHolder = (LinearLayout) view.findViewById(R.id.fields_holder);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(CARD)) {
            mCard = (Card) bundle.getSerializable(CARD);
            mTitleView.setText(mCard.getVotingTitle());
        }
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        mProgressBar.setVisibility(View.GONE);
        showMessage(spiceException.getMessage(), getString(R.string.repeat), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                onRefresh();
            }
        });
    }

    @Override
    public void onRequestSuccess(ReqValuesHolder.List reqValuesHolders) {
        mProgressBar.setVisibility(View.GONE);
        mContentView.setVisibility(View.VISIBLE);
        initData(reqValuesHolders);
    }

    private void initData(ReqValuesHolder.List reqValuesHolders) {
        mFieldsHolder.removeAllViews();
        Context context = getActivity();
        LayoutInflater inflater = LayoutInflater.from(context);
        for (int i = 0, size = reqValuesHolders.size(); i < size; i++) {
            if (i != 0) {
                mFieldsHolder.addView(inflater.inflate(R.layout.list_divider, mFieldsHolder, false));
            }
            ReqValuesHolder reqValuesHolder = reqValuesHolders.get(i);
            switch (reqValuesHolder.getType()) {
                case text:
                    FestCardTextFieldView textFieldView = new FestCardTextFieldView(context);
                    textFieldView.setReqValueHolder(reqValuesHolder);
                    mFieldsHolder.addView(textFieldView);
                    break;
                case image:
                    FestCardImageFieldView imageFieldView = new FestCardImageFieldView(context);
                    imageFieldView.setReqValueHolder(reqValuesHolder);
                    mFieldsHolder.addView(imageFieldView);
                    break;
                case link:
                    FestCardLinkFieldView linkFieldView = new FestCardLinkFieldView(context);
                    linkFieldView.setReqValueHolder(reqValuesHolder);
                    mFieldsHolder.addView(linkFieldView);
                    break;
                case user:
                    FestCardUserFieldView userFieldView = new FestCardUserFieldView(context);
                    userFieldView.setReqValueHolder(reqValuesHolder);
                    mFieldsHolder.addView(userFieldView);
                    break;
            }
        }
    }
}
