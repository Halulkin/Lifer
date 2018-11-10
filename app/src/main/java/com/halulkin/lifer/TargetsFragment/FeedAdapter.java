package com.halulkin.lifer.TargetsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.halulkin.lifer.R;


class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int itemsCount = 0;

    FeedAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);

        return new CellFeedViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final CellFeedViewHolder holder = (CellFeedViewHolder) viewHolder;
        bindDefaultFeedItem(position, holder);
    }

    private void bindDefaultFeedItem(int position, CellFeedViewHolder holder) {
//        if (position % 2 == 0) {
//            holder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_1);
//            holder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_1);
//        } else {
//            holder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_2);
//            holder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_2);
//        }
//
//        holder.btnComments.setTag(position);
//        holder.btnMore.setTag(position);
//        holder.ivFeedCenter.setTag(holder);
//        holder.btnLike.setTag(holder);

    }

    void updateItems() {
        itemsCount = 10;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    private static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTime;
        TextView textViewName;
        TextView textViewDate;
//        CheckBox customCheckBox;

        CellFeedViewHolder(View view) {
            super(view);

            textViewTime = (TextView) view.findViewById(R.id.textViewTime);
            textViewName = (TextView) view.findViewById(R.id.textViewName);
            textViewDate = (TextView) view.findViewById(R.id.textViewDate);
//            customCheckBox = (ShineButton) view.findViewById(R.id.po_image1);

        }
    }


}
