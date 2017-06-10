package com.projects.gungde.learningrealm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projects.gungde.learningrealm.R;
import com.projects.gungde.learningrealm.model.Item;

import io.realm.RealmResults;

/**
 * Created by roma on 20.10.15.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private RealmResults<Item> items;
    private Context context;

    public ItemAdapter(RealmResults<Item> items) {
        this.items = items;
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

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView txItemName;

        public ItemViewHolder(View itemView) {
            super(itemView);

            txItemName = (TextView) itemView.findViewById(R.id.tv_name_item);
        }
    }
}
