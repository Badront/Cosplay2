package ru.badr.cosplay2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badr.cosplay2.model.cards.list.ListCard;
import com.bumptech.glide.Glide;

import java.util.List;

import ru.badr.base.adapter.BaseRecyclerAdapter;
import ru.badr.cosplay2.adapter.viewholder.FestPhotoCardViewHolder;
import ru.badr.cosplay2.util.Utils;
import ru.badr.opencon.R;

/**
 * Created by ABadretdinov
 * 19.10.2015
 * 16:29
 */
public class FestPhotoCardAdapter extends BaseRecyclerAdapter<ListCard, FestPhotoCardViewHolder> {
    public FestPhotoCardAdapter(List<ListCard> data) {
        super(data);
    }

    @Override
    @NonNull
    public FestPhotoCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photocosplay_row, parent, false);
        return new FestPhotoCardViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FestPhotoCardViewHolder holder, int position) {
        ListCard card = getItem(position);
        Context context = holder.itemView.getContext();
        holder.title.setText(card.getVotingTitle());
        if (!TextUtils.isEmpty(card.getWin())) {
            holder.win.setVisibility(View.VISIBLE);
            int color;
            if ("1".equals(card.getWin())) {
                color = R.color.gold;
            } else if ("2".equals(card.getWin())) {
                color = R.color.silver;
            } else {
                color = R.color.bronze;
            }
            holder.win.setColorFilter(context.getResources().getColor(color));
        } else {
            holder.win.setVisibility(View.GONE);
        }

        Glide.with(context).load(Utils.getCardImageUrl(context, card)).into(holder.photo);

    }
}
