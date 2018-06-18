package ru.badr.cosplay2.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ru.badr.base.fragment.BaseFragment;
import ru.badr.base.util.MapUtils;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 23.09.2015
 * 10:50
 */
public class AboutFragment extends BaseFragment implements View.OnClickListener {
    private View mMapHolder;
    private ImageView mMapImageView;
    private ImageView mAboutImage;

    @Override
    protected String getTitle() {
        return getString(R.string.about);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_layout, container, false);
        mAboutImage = (ImageView) view.findViewById(R.id.image);
        mAboutImage.setOnClickListener(null);
        mMapImageView = (ImageView) view.findViewById(R.id.map_image);
        mMapHolder = (View) mMapImageView.getParent();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Glide.with(getContext()).load("file:///android_asset/about16.png").into(mAboutImage);
        mMapImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onGlobalLayout() {
                mMapImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                float scale = getResources().getDisplayMetrics().density;
                int width = (int) (mMapImageView.getWidth() / scale);
                int height = width / 2;
                Glide
                        .with(AboutFragment.this)
                        .load(
                                MapUtils.getStaticMapUrl(
                                        mMapImageView.getContext(),
                                        getString(R.string.address),
                                        width,
                                        height,
                                        true))
                        .placeholder(new ColorDrawable(Color.GRAY))
                        .into(mMapImageView);
            }
        });
        mMapHolder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mMapHolder.equals(v)) {
            String address = getString(R.string.address);
            Uri mapsUri = Uri.parse("geo:0,0?q=" + address);
            Intent mapsIntent = new Intent(Intent.ACTION_VIEW, mapsUri);
            if (mapsIntent.resolveActivity(v.getContext().getPackageManager()) != null) {
                startActivity(mapsIntent);
            } else {
                String uri = "http://maps.google.com/maps?f=d&hl=ru&saddr=" + address;
                mapsIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                if (mapsIntent.resolveActivity(v.getContext().getPackageManager()) != null) {
                    startActivity(mapsIntent);
                }
            }
        }
    }
}
