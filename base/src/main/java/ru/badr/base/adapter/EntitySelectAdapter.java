package ru.badr.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import ru.badr.base.R;

/**
 * Created by ABadretdinov
 * 22.12.2014
 * 19:55
 */
public class EntitySelectAdapter<T> extends BaseRecyclerAdapter<T, EntityHolder> implements Filterable {
    private List<T> mFilteredData;
    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            List<T> filteredData = new ArrayList<T>();
            String lowerConstr = constraint != null ? constraint.toString().toLowerCase() : "";
            filterResults.count = filteredData.size();
            for (T entity : mData) {
                if (entity.toString() != null && entity.toString().toLowerCase().contains(lowerConstr)) {
                    filteredData.add(entity);
                }
            }
            filterResults.values = filteredData;
            return filterResults;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFilteredData = (List<T>) results.values;
            if (mFilteredData == null) {
                mFilteredData = new ArrayList<T>();
            }
            notifyDataSetChanged();
        }
    };

    public EntitySelectAdapter(List<T> data) {
        super(data);
        this.mFilteredData = mData;
    }

    public void setData(List<T> data) {
        super.setData(data);
        mFilteredData = mData;
        notifyDataSetChanged();
    }

    @Override
    public void removeItem(int position) {
        T entity = mFilteredData.get(position);
        mFilteredData.remove(position);
        mData.remove(entity);
        notifyItemRemoved(position);
    }

    @Override
    public void removeItem(T item) {
        int position = mFilteredData.indexOf(item);
        mData.remove(item);
        if (position >= 0) {
            mFilteredData.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public int getItemCount() {
        return mFilteredData.size();
    }

    @Override
    public T getItem(int position) {
        return mFilteredData.get(position);
    }

    @Override
    public List<T> getItems() {
        return mFilteredData;
    }

    @Override
    public EntityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item_1, parent, false);
        return new EntityHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(EntityHolder holder, int position) {
        T entity = getItem(position);
        holder.text1.setText(entity.toString());
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }
}
