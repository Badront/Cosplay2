package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.badr.base.adapter.BaseRecyclerAdapter;
import ru.badr.cosplay2.R;
import ru.badr.cosplay2.adapter.viewholder.MediaPhotoViewHolder;
import ru.badr.cosplay2.api.media.Album;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 15:49
 */
public class MediaAlbumAdapter extends BaseRecyclerAdapter<Album, MediaPhotoViewHolder> {
    public MediaAlbumAdapter(List<Album> data) {
        super(data);
    }

    @Override
    public MediaPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_photo_row, parent, false);
        return new MediaPhotoViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(MediaPhotoViewHolder holder, int position) {
        Album album = getItem(position);
        Context context = holder.itemView.getContext();
        holder.title.setText(album.getTitle());
        holder.likes.setText(String.valueOf(album.getLikes()));
        if (!TextUtils.isEmpty(album.getThumbnailSrc())) {
            Glide.with(context).load(album.getThumbnailSrc()).into(holder.photo);
        } else {
            Glide.with(context).load("file:///android_asset/no_thumbnail.png").into(holder.photo);
        }
    }
}
