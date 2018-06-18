package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.badr.base.adapter.BaseRecyclerAdapter;
import ru.badr.cosplay2.adapter.viewholder.MediaPhotoViewHolder;
import ru.badr.cosplay2.api.media.Photo;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 22.10.2015
 * 15:47
 */
public class MediaPhotoAdapter extends BaseRecyclerAdapter<Photo, MediaPhotoViewHolder> {
    public MediaPhotoAdapter(List<Photo> data) {
        super(data);
    }

    @Override
    @NonNull
    public MediaPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_photo_row, parent, false);
        return new MediaPhotoViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaPhotoViewHolder holder, int position) {
        Photo photo = getItem(position);
        Context context = holder.itemView.getContext();
        holder.title.setText(photo.getTitle());
        holder.likes.setText(String.valueOf(photo.getLikes()));

        Glide.with(context).load(photo.getSrc()).into(holder.photo);
    }
}
