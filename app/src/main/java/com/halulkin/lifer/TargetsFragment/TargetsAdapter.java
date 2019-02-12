package com.halulkin.lifer.TargetsFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lguipeng.library.animcheckbox.AnimCheckBox;
import com.halulkin.lifer.R;

import java.util.ArrayList;
import java.util.List;


class TargetsAdapter extends RecyclerView.Adapter<TargetsAdapter.ViewHolder> {

    private List<TargetsModel> targetsModelList = new ArrayList<>();
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

    TargetsAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutForItem = R.layout.targets_adapter_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutForItem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TargetsModel targetsModel = targetsModelList.get(position);
        holder.tvTargetTitle.setText(targetsModel.getTitle());
        holder.tvTargetDate.setText(targetsModel.getDate());
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (targetsModelList == null) {
            return 0;
        }
        return targetsModelList.size();
    }

    void loadItems(List<TargetsModel> targetsModelList) {
        this.targetsModelList = targetsModelList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTargetTitle;
        TextView tvTargetDate;
        AnimCheckBox animCheckBoxTargets;
        RelativeLayout rlTargetsCheckbox;

        ViewHolder(View itemView) {
            super(itemView);
            tvTargetTitle = itemView.findViewById(R.id.tvTargetTitle);
            tvTargetDate = itemView.findViewById(R.id.tvTargetDate);
            animCheckBoxTargets = itemView.findViewById(R.id.animCheckBoxTargets);
            rlTargetsCheckbox = itemView.findViewById(R.id.rlTargetsCheckbox);
            rlTargetsCheckbox.setOnClickListener(this);
            animCheckBoxTargets.setOnClickListener(this);
        }

        void bind(int position) {
            if (!itemStateArray.get(position, false)) {
                animCheckBoxTargets.setChecked(false, false);
            } else {
                animCheckBoxTargets.setChecked(true, false);
            }
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            if (!itemStateArray.get(adapterPosition, false)) {
                animCheckBoxTargets.setChecked(true);
                itemStateArray.put(adapterPosition, true);
            } else {
                animCheckBoxTargets.setChecked(false);
                itemStateArray.put(adapterPosition, false);
            }
        }
    }
}

