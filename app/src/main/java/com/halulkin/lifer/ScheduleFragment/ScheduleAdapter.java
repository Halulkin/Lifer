package com.halulkin.lifer.ScheduleFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.halulkin.lifer.R;

import java.util.List;


class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<ScheduleModel> scheduleModelList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ScheduleAdapter(Context context, List<ScheduleModel> scheduleModelList) {
        this.mInflater = LayoutInflater.from(context);
        this.scheduleModelList = scheduleModelList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.schedule_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScheduleModel scheduleModel = scheduleModelList.get(position);
        holder.tvScheduleTime.setText(scheduleModel.getTime());
        holder.tvScheduleTitle.setText(scheduleModel.getTitle());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return scheduleModelList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvScheduleTime;
        TextView tvScheduleTitle;

        ViewHolder(View itemView) {
            super(itemView);
            tvScheduleTime = itemView.findViewById(R.id.tvScheduleTime);
            tvScheduleTitle = itemView.findViewById(R.id.tvScheduleTitle);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return scheduleModelList.get(id).getTitle();
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