package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import ru.badr.cosplay2.api.media.AlbumsAndPhotos;
import ru.badr.cosplay2.fragment.MediaAlbumsFragment;
import ru.badr.cosplay2.fragment.MediaPhotosFragment;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 15:34
 */
public class MediaPagerAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;
    private AlbumsAndPhotos mData;

    public MediaPagerAdapter(Context context, FragmentManager fm, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        super(fm);
        this.mOnRefreshListener = onRefreshListener;
        mTitles = context.getResources().getStringArray(R.array.media_tabs);
    }

    public void setAlbumsAndPhotos(AlbumsAndPhotos albumsAndPhotos) {
        mData = albumsAndPhotos;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MediaPhotosFragment photos = new MediaPhotosFragment();
                photos.setOnRefreshListener(mOnRefreshListener);
                return photos;
            case 1:
            default:
                MediaAlbumsFragment albums = new MediaAlbumsFragment();
                albums.setOnRefreshListener(mOnRefreshListener);
                return albums;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (object instanceof MediaPhotosFragment) {
            ((MediaPhotosFragment) object).setPhotos(mData != null ? mData.getPhotos() : null);
        } else if (object instanceof MediaAlbumsFragment) {
            ((MediaAlbumsFragment) object).setAlbums(mData != null ? mData.getAlbums() : null);
        }
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
