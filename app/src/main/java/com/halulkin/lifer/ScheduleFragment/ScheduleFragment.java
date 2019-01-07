package com.halulkin.lifer.ScheduleFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halulkin.lifer.MainActivity;
import com.halulkin.lifer.R;

import java.util.ArrayList;
import java.util.Objects;

public class ScheduleFragment extends Fragment {

    private RecyclerView rvSchedule;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.schedule_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSchedule = (RecyclerView) view.findViewById(R.id.rvSchedule);

        ((MainActivity) Objects.requireNonNull(getActivity())).closeDrawer();
        ((MainActivity) Objects.requireNonNull(getActivity())).expandToolbar();

        setupScheduleRecyclerView();
    }


    private void setupScheduleRecyclerView() {

        ArrayList<ScheduleModel> scheduleData = new ArrayList<>();
        scheduleData.add(new ScheduleModel("06:00", "Wake up at 6 o'clock", false));
        scheduleData.add(new ScheduleModel("07:00", "Run 10 km", false));
        scheduleData.add(new ScheduleModel("09:00", "Learn full information about kotlin", false));
        scheduleData.add(new ScheduleModel("18:30", "Earn 100 dollars", false));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvSchedule.setLayoutManager(linearLayoutManager);
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getContext(), scheduleData);
        rvSchedule.setAdapter(scheduleAdapter);
    }
}
