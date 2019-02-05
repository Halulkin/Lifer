package com.halulkin.lifer.ScheduleFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lguipeng.library.animcheckbox.AnimCheckBox;
import com.halulkin.lifer.R;

import java.util.ArrayList;
import java.util.List;


class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<ScheduleModel> scheduleModelList = new ArrayList<>();
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

    ScheduleAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutForItem = R.layout.schedule_adapter_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutForItem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleModel scheduleModel = scheduleModelList.get(position);
        holder.tvScheduleTime.setText(scheduleModel.getTime());
        holder.tvScheduleTitle.setText(scheduleModel.getTitle());
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (scheduleModelList == null) {
            return 0;
        }
        return scheduleModelList.size();
    }

    void loadItems(List<ScheduleModel> scheduleModelList) {
        this.scheduleModelList = scheduleModelList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements AnimCheckBox.OnClickListener {
        TextView tvScheduleTime;
        TextView tvScheduleTitle;
        AnimCheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            tvScheduleTime = itemView.findViewById(R.id.tvScheduleTime);
            tvScheduleTitle = itemView.findViewById(R.id.tvScheduleTitle);
            checkBox = itemView.findViewById(R.id.animCheckBoxSchedule);
            checkBox.setOnClickListener(this);
        }

        void bind(int position) {
            if (!itemStateArray.get(position, false)) {
                checkBox.setChecked(false, false);
            } else {
                checkBox.setChecked(true, false);
            }
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            if (!itemStateArray.get(adapterPosition, false)) {
                checkBox.setChecked(true);
                itemStateArray.put(adapterPosition, true);
            } else {
                checkBox.setChecked(false);
                itemStateArray.put(adapterPosition, false);
            }
        }
    }
}