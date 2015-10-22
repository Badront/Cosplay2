package ru.badr.cosplay2.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import java.util.List;

import ru.badr.base.fragment.RecyclerFragment;
import ru.badr.cosplay2.adapter.MediaAlbumAdapter;
import ru.badr.cosplay2.adapter.viewholder.MediaPhotoViewHolder;
import ru.badr.cosplay2.api.media.Album;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 15:57
 */
public class MediaAlbumsFragment extends RecyclerFragment<Album, MediaPhotoViewHolder> {
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;


    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        this.mOnRefreshListener = onRefreshListener;
    }

    @Override
    public boolean isNeedDivider() {
        return false;
    }

    @Override
    public void onRecyclerViewItemClick(View v, int position) {
        Album album = getAdapter().getItem(position);
        Uri uri = Uri.parse(album.getUrl());
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onRefresh() {
        setRefreshing(true);
        if (mOnRefreshListener != null) {
            mOnRefreshListener.onRefresh();
        }
    }

    public void setAlbums(List<Album> albums) {
        setRefreshing(false);
        setAdapter(new MediaAlbumAdapter(albums));
    }
}
