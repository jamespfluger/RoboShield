package com.jamespfluger.roboshield.lists;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jamespfluger.roboshield.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public final View baseItem;
    public final TextView textView;
    public final ImageButton deleteButton;
    public Item item;

    public ItemViewHolder(View view, final ItemAdapter adapter) {
        super(view);
        baseItem = view;
        textView = view.findViewById(R.id.listItem);

        deleteButton = view.findViewById(R.id.listDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currPos = getAdapterPosition();

                if (currPos != RecyclerView.NO_POSITION) {
                    adapter.removeItem(currPos);
                }
            }
        });
    }
}
