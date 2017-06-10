package com.projects.gungde.learningrealm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.gungde.learningrealm.AddItemDialog;
import com.projects.gungde.learningrealm.R;
import com.projects.gungde.learningrealm.model.Item;
import com.projects.gungde.learningrealm.realm.table.RealmTable;

import io.realm.RealmResults;

/**
 * Created by roma on 20.10.15.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private RealmResults<Item> items;
    private Activity activity;

    public ItemAdapter(RealmResults<Item> items,Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.txItemName.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txItemName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txItemName = (TextView) itemView.findViewById(R.id.tv_name_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Item item = items.get(getAdapterPosition());
            Bundle bundle = new Bundle();
            bundle.putString(RealmTable.ID, item.getId());
            showAddItemDialog(bundle);
        }
    }

    private void showAddItemDialog(Bundle bundle) {
        final AddItemDialog dialog = new AddItemDialog();
        dialog.setArguments(bundle);
        dialog.show(activity.getFragmentManager(), dialog.getClass().getName());
    }
}
