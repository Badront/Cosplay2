package ru.badr.cosplay2.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.badr.cosplay2.model.cards.User;
import com.bumptech.glide.Glide;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import ru.badr.base.BaseSpiceManager;
import ru.badr.base.activity.BaseActivity;
import ru.badr.base.fragment.BaseFragment;
import ru.badr.base.util.Navigate;
import ru.badr.cosplay2.task.UserInfoLoadRequest;
import ru.badr.cosplay2.util.Utils;
import ru.badr.opencon.R;

/**
 * Created by Badr on 24.11.2015.
 */
public class UserInfoFragment extends BaseFragment implements RequestListener<User>, View.OnClickListener {
    protected SpiceManager mSpiceManager = new BaseSpiceManager(UncachedSpiceService.class);
    private View mProgressBarHolder;
    private View mScrollView;

    private View mVkHolder;
    private View mVkDivider;

    private ImageView mAvatar;

    private User mUser;


    @Override
    protected String getTitle() {
        return getString(R.string.user);
    }

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info, container, false);
        mProgressBarHolder = view.findViewById(R.id.progressbar);
        mScrollView = view.findViewById(R.id.scrollView);

        mVkHolder = view.findViewById(R.id.vk_holder);
        mVkDivider = view.findViewById(R.id.vk_divider);

        mAvatar = (ImageView) view.findViewById(R.id.avatar);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListeners();
    }

    private void setListeners() {
        mVkHolder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mVkHolder.equals(v)) {
            if (mUser != null && !TextUtils.isEmpty(mUser.getVk())) {
                Uri uri = Uri.parse(mUser.getVk());
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        }
    }

    public void onRefresh() {
        mScrollView.setVisibility(View.INVISIBLE);
        mProgressBarHolder.setVisibility(View.VISIBLE);
        mSpiceManager.execute(new UserInfoLoadRequest(getActivity(), getArguments().getLong(Navigate.PARAM_ID)), this);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        mProgressBarHolder.setVisibility(View.GONE);
        showMessage(spiceException.getCause() != null ? spiceException.getCause().getMessage() : spiceException.getMessage(), getString(R.string.repeat), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    @Override
    public void onRequestSuccess(User user) {
        mScrollView.setVisibility(View.VISIBLE);
        mProgressBarHolder.setVisibility(View.GONE);

        mUser = user;
        initView();

    }

    private void initView() {
        BaseActivity context = (BaseActivity) getActivity();
        View root = getView();
        if (context != null && root != null) {
            ((CollapsingToolbarLayout) root.findViewById(R.id.collapsing_toolbar)).setTitle(mUser.getTitle());
            Glide.with(this).load(Utils.getUserAvatar(context, mUser)).into(mAvatar);

            ((TextView) root.findViewById(R.id.city)).setText(mUser.getCity());
            ((TextView) root.findViewById(R.id.sex)).setText(Utils.getUserSex(context, mUser));

            if (!TextUtils.isEmpty(mUser.getVk())) {
                mVkDivider.setVisibility(View.VISIBLE);
                mVkHolder.setVisibility(View.VISIBLE);
                ((TextView) mVkHolder.findViewById(R.id.vk)).setText(mUser.getVk());
            } else {
                mVkDivider.setVisibility(View.GONE);
                mVkHolder.setVisibility(View.GONE);
            }
        }
    }
}
