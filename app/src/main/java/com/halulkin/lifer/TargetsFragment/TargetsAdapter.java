package com.halulkin.lifer.TargetsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lguipeng.library.animcheckbox.AnimCheckBox;
import com.halulkin.lifer.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class TargetsAdapter extends RecyclerView.Adapter<TargetsAdapter.ViewHolder> {

    private List<TargetsModel> targetsModelList = new ArrayList<>();
    private LayoutInflater mInflater;
    private HashMap<String, Boolean> itemStateArray = new HashMap<>();

    TargetsAdapter(Context context, List<TargetsModel> targetsModelList) {
        this.mInflater = LayoutInflater.from(context);
        this.targetsModelList = targetsModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.targets_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TargetsModel targetsModel = targetsModelList.get(position);
        holder.tvTargetTime.setText(String.valueOf(position + 1));
        holder.tvTargetTitle.setText(targetsModel.getTitle());

        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(targetsModel.getStatus());

        holder.checkBox.setOnCheckedChangeListener(new AnimCheckBox.OnCheckedChangeListener() {
            @Override
            public void onChange(AnimCheckBox view, boolean checked) {
                //set your object's last status
                targetsModel.setStatus(checked);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTargetTime;
        TextView tvTargetTitle;
        TextView tvTargetDate;
        AnimCheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            tvTargetTime = itemView.findViewById(R.id.tvTargetTime);
            tvTargetTitle = itemView.findViewById(R.id.tvTargetTitle);
            tvTargetDate = itemView.findViewById(R.id.tvTargetDate);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }

    @Override
    public int getItemCount() {
        return targetsModelList.size();
    }

    void loadItems(List<TargetsModel> targetsModelList) {
        this.targetsModelList = targetsModelList;
        notifyDataSetChanged();
    }
}