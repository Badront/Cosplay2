package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.badr.base.adapter.BaseRecyclerAdapter;
import ru.badr.cosplay2.R;
import ru.badr.cosplay2.adapter.viewholder.FestPhotoCardViewHolder;
import ru.badr.cosplay2.api.cards.Card;
import ru.badr.cosplay2.util.Utils;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 16:29
 */
public class FestPhotoCardAdapter extends BaseRecyclerAdapter<Card, FestPhotoCardViewHolder> {
    public FestPhotoCardAdapter(List<Card> data) {
        super(data);
    }

    @Override
    public FestPhotoCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photocosplay_row, parent, false);
        return new FestPhotoCardViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(FestPhotoCardViewHolder holder, int position) {
        Card card = getItem(position);
        Context context = holder.itemView.getContext();
        holder.title.setText(card.getVotingTitle());

        Glide.with(context).load(Utils.getCardImageUrl(context, card)).into(holder.photo);

    }
}
