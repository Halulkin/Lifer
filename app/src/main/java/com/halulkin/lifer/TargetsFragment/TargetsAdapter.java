package com.halulkin.lifer.TargetsFragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.halulkin.lifer.R;

import java.util.ArrayList;
import java.util.List;


class TargetsAdapter extends RecyclerView.Adapter<TargetsAdapter.ViewHolder> {

    private List<TargetsModel> targetsModelList = new ArrayList<>();
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();
    private SparseBooleanArray starStateArray = new SparseBooleanArray();

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
        holder.bindCheckBox(position);
        holder.bindStar(position);
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
        LottieAnimationView lvTargetCheckBox, lvTargetStar;
        final MediaPlayer mp;

        ViewHolder(View itemView) {
            super(itemView);
            tvTargetTitle = itemView.findViewById(R.id.tvTargetTitle);
            lvTargetCheckBox = itemView.findViewById(R.id.lvTargetCheckBox);
            lvTargetStar = itemView.findViewById(R.id.lvTargetStar);


            lvTargetCheckBox.setOnClickListener(this);
            lvTargetStar.setOnClickListener(this);

            mp = MediaPlayer.create(itemView.getContext(), R.raw.sample);
        }

        void bindCheckBox(int position) {
            if (!itemStateArray.get(position, false)) {
                lvTargetCheckBox.setProgress(0F);
            } else {
                lvTargetCheckBox.setProgress(1f);
            }
        }

        void bindStar(int position) {
            if (!starStateArray.get(position, false)) {
                lvTargetStar.setProgress(0F);
            } else {
                lvTargetStar.setProgress(1f);
            }
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();

            if (v == lvTargetCheckBox) {
                if (!itemStateArray.get(adapterPosition, false)) {
                    itemStateArray.put(adapterPosition, true);
                    startCheckBoxAnimation();
                    tvTargetTitle.setPaintFlags(tvTargetTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    tvTargetTitle.setTextColor(Color.GRAY);
                    mp.start();

                } else {
                    itemStateArray.put(adapterPosition, false);
                    startCheckBoxAnimation();
                    tvTargetTitle.setPaintFlags(tvTargetTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    tvTargetTitle.setTextColor(Color.BLACK);
                }

            } else if(v == lvTargetStar) {
                if (!starStateArray.get(adapterPosition, false)) {
                    starStateArray.put(adapterPosition, true);
                    startStarAnimation();
                } else {
                    starStateArray.put(adapterPosition, false);
                    startStarAnimation();
                }
            }
        }

        private void startCheckBoxAnimation() {
            ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(400);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    lvTargetCheckBox.setProgress((Float) valueAnimator.getAnimatedValue());
                }
            });

            if (lvTargetCheckBox.getProgress() == 0f) {
                animator.start();
            } else {
                lvTargetCheckBox.setProgress(0f);
            }
        }

        private void startStarAnimation() {
            ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(450);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    lvTargetStar.setProgress((Float) valueAnimator.getAnimatedValue());
                }
            });

            if (lvTargetStar.getProgress() == 0f) {
                animator.start();
            } else {
                lvTargetStar.setProgress(0f);
            }
        }
    }
}

