package com.halulkin.lifer.ScheduleFragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.halulkin.lifer.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<ScheduleModel> scheduleModelList = new ArrayList<>();
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();
    private int time;
    private int parsedTime;

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

    private int getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        return (hours * 60) + minutes;
    }

    private int parseStringToTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm", Locale.US);

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormat.parse(time));
            int hours = cal.get(Calendar.HOUR_OF_DAY);
            int minutes = cal.get(Calendar.MINUTE);
            parsedTime = (hours * 60) + minutes;

        } catch (ParseException ignored) {
        }
        return parsedTime;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleModel scheduleModel = scheduleModelList.get(position);
        time = getCurrentTime();
        holder.tvScheduleTime.setText(scheduleModel.getTime());
        holder.tvScheduleTitle.setText(scheduleModel.getTitle());
        holder.bind(position);

        if (position < scheduleModelList.size() - 1) {
            if ((time >= parseStringToTime(scheduleModelList.get(position).getTime())) && (time < parseStringToTime(scheduleModelList.get(position + 1).getTime()))) {
                holder.setActiveColor();
                holder.tvScheduleTitle.setTypeface(holder.tvScheduleTitle.getTypeface(), Typeface.BOLD);
                holder.tvScheduleTime.setTypeface(holder.tvScheduleTitle.getTypeface(), Typeface.BOLD);

            } else {
                holder.setInActiveColor();
                holder.tvScheduleTitle.setTypeface(holder.tvScheduleTitle.getTypeface(), Typeface.NORMAL);
                holder.tvScheduleTime.setTypeface(holder.tvScheduleTitle.getTypeface(), Typeface.NORMAL);
            }
        } else {
            if ((time >= parseStringToTime(scheduleModelList.get(position).getTime()))) {
                holder.setActiveColor();
                holder.tvScheduleTitle.setTypeface(holder.tvScheduleTitle.getTypeface(), Typeface.BOLD);
                holder.tvScheduleTime.setTypeface(holder.tvScheduleTitle.getTypeface(), Typeface.BOLD);

            } else {
                holder.setInActiveColor();
                holder.tvScheduleTitle.setTypeface(holder.tvScheduleTitle.getTypeface(), Typeface.NORMAL);
                holder.tvScheduleTime.setTypeface(holder.tvScheduleTitle.getTypeface(), Typeface.NORMAL);
            }
        }
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvScheduleTime;
        TextView tvScheduleTitle;
        CardView cvScheduleTime;
        LottieAnimationView lvScheduleCheckBox;


        ViewHolder(View itemView) {
            super(itemView);
            tvScheduleTime = itemView.findViewById(R.id.tvScheduleTime);
            tvScheduleTitle = itemView.findViewById(R.id.tvScheduleTitle);
            cvScheduleTime = itemView.findViewById(R.id.cvScheduleTime);
            lvScheduleCheckBox = itemView.findViewById(R.id.lvScheduleCheckBox);
            lvScheduleCheckBox.setOnClickListener(this);

        }

        void bind(int position) {
            if (!itemStateArray.get(position, false)) {
                lvScheduleCheckBox.setProgress(0F);
            } else {
                lvScheduleCheckBox.setProgress(1f);
            }
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            if (!itemStateArray.get(adapterPosition, false)) {
                itemStateArray.put(adapterPosition, true);
                startCheckAnimation();
            } else {
                itemStateArray.put(adapterPosition, false);
                startCheckAnimation();
            }
        }

        private void startCheckAnimation() {
            ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(700);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    lvScheduleCheckBox.setProgress((Float) valueAnimator.getAnimatedValue());
                }
            });

            if (lvScheduleCheckBox.getProgress() == 0f) {
                animator.start();
            } else {
                lvScheduleCheckBox.setProgress(0f);
            }
        }

//
//
//
//        void bind(int position) {
//            if (!itemStateArray.get(position, false)) {
//                animCheckBoxSchedule.setChecked(false, false);
//            } else {
//                animCheckBoxSchedule.setChecked(true, false);
//            }
//        }

        public void setActiveColor() {
            cvScheduleTime.setCardBackgroundColor(Color.parseColor("#D1EEE9"));
        }

        void setInActiveColor() {
            cvScheduleTime.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
//
//        @Override
//        public void onClick(View v) {
//            int adapterPosition = getAdapterPosition();
//            if (!itemStateArray.get(adapterPosition, false)) {
//                animCheckBoxSchedule.setChecked(true);
//                itemStateArray.put(adapterPosition, true);
//
//            } else {
//                animCheckBoxSchedule.setChecked(false);
//                itemStateArray.put(adapterPosition, false);
//            }
//        }
    }
}