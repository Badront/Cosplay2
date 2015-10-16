package ru.badr.base.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.badr.base.adapter.OnItemClickListener;
import ru.badr.base.adapter.OnItemLongClickListener;


/**
 * Created by ABadretdinov
 * 12.12.2014
 * 16:46
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public BaseViewHolder(View itemView) {
        this(itemView, null);
    }

    public BaseViewHolder(View itemView, OnItemClickListener clickListener) {
        this(itemView, clickListener, null);
    }

    public BaseViewHolder(View itemView, OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
        super(itemView);
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        initView(itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null) {
            clickListener.onItemClick(v, getLayoutPosition());
        }
    }

    protected abstract void initView(View itemView);

    @Override
    public boolean onLongClick(View v) {
        return longClickListener != null && longClickListener.onItemLongClick(v, getLayoutPosition());
    }
}
