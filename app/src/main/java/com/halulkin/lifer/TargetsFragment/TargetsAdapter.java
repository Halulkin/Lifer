package com.halulkin.lifer.TargetsFragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.halulkin.lifer.DBHelper;
import com.halulkin.lifer.R;

import java.util.ArrayList;
import java.util.List;


class TargetsAdapter extends RecyclerView.Adapter<TargetsAdapter.ViewHolder> {

    private List<TargetsModel> targetsModelList = new ArrayList<>();
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();
    private SparseBooleanArray starStateArray = new SparseBooleanArray();
    private DBHelper db;

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
        holder.tvTargetTitle.setText(targetsModel.getTargetName());
        holder.tvTargetDate.setText(targetsModel.getTargetDate());
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
        for (int i = 0; i < targetsModelList.size(); i++) {
            if (targetsModelList.get(i).getTargetStatus() == 1) {
                itemStateArray.put(i, true);
            } else {
                itemStateArray.put(i, false);
            }
        }
    }

    private void strikeText(TextView tvTargetTitle) {
        tvTargetTitle.setPaintFlags(tvTargetTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvTargetTitle.setTextColor(Color.GRAY);
    }

    private void cancelStrike(TextView tvTargetTitle) {
        tvTargetTitle.setPaintFlags(tvTargetTitle.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        tvTargetTitle.setTextColor(Color.BLACK);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTargetTitle, tvTargetDate;
        LottieAnimationView lvTargetCheckBox, lvTargetStar;
        SoundPool sp;

        ViewHolder(View itemView) {
            super(itemView);
            tvTargetTitle = itemView.findViewById(R.id.tvTargetTitle);
            lvTargetCheckBox = itemView.findViewById(R.id.lvTargetCheckBox);
            lvTargetStar = itemView.findViewById(R.id.lvTargetStar);
            tvTargetDate = itemView.findViewById(R.id.tvTargetDate);
            lvTargetCheckBox.setOnClickListener(this);
            lvTargetStar.setOnClickListener(this);
            initSoundPoolBuilder();
            db = new DBHelper(itemView.getContext());
        }

        void initSoundPoolBuilder() {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            sp = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();
            sp.load(itemView.getContext(), R.raw.sample, 1);
        }

        void playSound() {
            AudioManager audioManager = (AudioManager) itemView.getContext().getSystemService(Context.AUDIO_SERVICE);
            assert audioManager != null;
            float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float leftVolume = curVolume / maxVolume;
            float rightVolume = curVolume / maxVolume;
            int priority = 1;
            int no_loop = 0;
            float normal_playback_rate = 1f;
            sp.play(1, leftVolume, rightVolume, priority, no_loop,
                    normal_playback_rate);
        }

        void bindCheckBox(int position) {
            if (!itemStateArray.get(position, false)) {
                lvTargetCheckBox.setProgress(0F);
                cancelStrike(tvTargetTitle);
            } else {
                lvTargetCheckBox.setProgress(1f);
                strikeText(tvTargetTitle);
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
                    startCheckBoxAnimation();
                    itemStateArray.put(adapterPosition, true);
                    strikeText(tvTargetTitle);
                    playSound();
                    targetsModelList.get(adapterPosition).setTargetStatus(1);
                } else {
                    startCheckBoxAnimation();
                    itemStateArray.put(adapterPosition, false);
                    cancelStrike(tvTargetTitle);
                    targetsModelList.get(adapterPosition).setTargetStatus(0);
                }

                db.updateTarget(targetsModelList.get(adapterPosition));

            } else if (v == lvTargetStar) {
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
            if (lvTargetCheckBox.getProgress() == 0) {
                lvTargetCheckBox.playAnimation();
            } else {
                lvTargetCheckBox.setProgress(0);
                lvTargetCheckBox.pauseAnimation();
            }
        }

        private void startStarAnimation() {
            if (lvTargetStar.getProgress() == 0) {
                lvTargetStar.playAnimation();
            } else {
                lvTargetStar.setProgress(0);
                lvTargetStar.pauseAnimation();
            }
        }
    }
}

