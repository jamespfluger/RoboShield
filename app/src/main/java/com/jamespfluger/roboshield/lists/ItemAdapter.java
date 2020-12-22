package com.jamespfluger.roboshield.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jamespfluger.roboshield.R;
import com.jamespfluger.roboshield.utils.PrefsUtil;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    public final String fragmentTypeKey;
    private final List<Item> itemList;

    public ItemAdapter(List<Item> items, String listType) {
        this.itemList = items;
        this.fragmentTypeKey = listType;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item, parent, false);
        return new ItemViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.item = itemList.get(position);
        holder.textView.setText(itemList.get(position).content);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addItem(Item item) {
        itemList.add(item);
        PrefsUtil.putList(fragmentTypeKey, itemList);
        this.notifyItemInserted(getItemCount() - 1);
    }

    public void removeItem(int index) {
        itemList.remove(index);
        PrefsUtil.putList(fragmentTypeKey, itemList);
        this.notifyItemRemoved(index);
    }
}
