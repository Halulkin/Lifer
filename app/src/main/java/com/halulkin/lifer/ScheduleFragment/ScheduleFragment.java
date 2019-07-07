package com.halulkin.lifer.ScheduleFragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halulkin.lifer.MainActivity;
import com.halulkin.lifer.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ScheduleFragment extends Fragment {

    public RecyclerView rvSchedule;
    public ScheduleAdapter scheduleAdapter;
    public LinearLayoutManager linearLayoutManager;

    List<ScheduleModel> scheduleData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.schedule_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) Objects.requireNonNull(getActivity())).closeDrawer();
        ((MainActivity) Objects.requireNonNull(getActivity())).expandToolbar();

        ((MainActivity)getActivity()).changeCollapsingToolbarImage(1);

        rvSchedule = view.findViewById(R.id.rvSchedule);
        scheduleAdapter = new ScheduleAdapter();
        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvSchedule.setLayoutManager(linearLayoutManager);
        rvSchedule.setAdapter(scheduleAdapter);

        fillScheduleItems();
        Collections.sort(scheduleData);

        scheduleAdapter.loadItems(scheduleData);

    }

    private void fillScheduleItems() {
        scheduleData.add(new ScheduleModel("10:00", "Wake up at 6 o'clock", false));
        scheduleData.add(new ScheduleModel("11:36", "Run 10 km", false));
        scheduleData.add(new ScheduleModel("11:06", "Learn full information about kotlin", false));
        scheduleData.add(new ScheduleModel("09:40", "Earn 100 dollars", false));
        scheduleData.add(new ScheduleModel("11:40", "Learn full information about kotlin", false));
        scheduleData.add(new ScheduleModel("18:30", "Earn 100 dollars", false));
    }
}