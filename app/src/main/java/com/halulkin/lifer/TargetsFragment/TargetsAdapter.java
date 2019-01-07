package com.halulkin.lifer.TargetsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.halulkin.lifer.R;

import java.util.List;


class TargetsAdapter extends RecyclerView.Adapter<TargetsAdapter.ViewHolder> {

    private List<TargetsModel> targetsModelList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    TargetsAdapter(Context context, List<TargetsModel> targetsModelList) {
        this.mInflater = LayoutInflater.from(context);
        this.targetsModelList = targetsModelList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.targets_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TargetsModel targetsModel = targetsModelList.get(position);
        holder.tvTargetTime.setText(String.valueOf(position+1));
        holder.tvTargetTitle.setText(targetsModel.getTitle());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return targetsModelList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTargetTime;
        TextView tvTargetTitle;
        TextView tvTargetDate;

        ViewHolder(View itemView) {
            super(itemView);
            tvTargetTime = itemView.findViewById(R.id.tvTargetTime);
            tvTargetTitle = itemView.findViewById(R.id.tvTargetTitle);
            tvTargetDate = itemView.findViewById(R.id.tvTargetDate);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return targetsModelList.get(id).getTitle();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}