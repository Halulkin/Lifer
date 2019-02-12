package com.halulkin.lifer.TargetsFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.util.List;
import java.util.Objects;

public class TargetsFragment extends Fragment {

    public RecyclerView rvTargets;
    public TargetsAdapter targetsAdapter;
    public LinearLayoutManager linearLayoutManager;

    List<TargetsModel> targetsData = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.targets_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) Objects.requireNonNull(getActivity())).closeDrawer();
        ((MainActivity) Objects.requireNonNull(getActivity())).expandToolbar();

        ((MainActivity)getActivity()).changeCollapsingToolbarImage(1);

        rvTargets = view.findViewById(R.id.rvTargets);
        targetsAdapter = new TargetsAdapter();
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTargets.setLayoutManager(linearLayoutManager);
        rvTargets.setAdapter(targetsAdapter);

        fillTargetItems();

        targetsAdapter.loadItems(targetsData);
    }

    private void fillTargetItems() {
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "Today",false));
        targetsData.add(new TargetsModel("Run 1000 km", "Tomorrow",false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", "Today",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "05.02.2019",false));
        targetsData.add(new TargetsModel("Run 1000 km", "05.02.2019",false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", "2 weeks",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "1 month",false));
        targetsData.add(new TargetsModel("Run 1000 km", "Tomorrow",false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", "Today",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "05.02.2019",false));
        targetsData.add(new TargetsModel("Run 1000 km", "05.02.2019",false));
        targetsData.add(new TargetsModel("Learn full information about kotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlinkotlin", "05.02.2019",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "Today",false));
        targetsData.add(new TargetsModel("Run 1000 km", "Tomorrow",false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", "Today",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "05.02.2019",false));
        targetsData.add(new TargetsModel("Run 1000 km", "05.02.2019",false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", "05.02.2019",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "Today",false));
        targetsData.add(new TargetsModel("Run 1000 km", "Tomorrow",false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", "Today",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "05.02.2019",false));
        targetsData.add(new TargetsModel("Run 1000 km", "05.02.2019",false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", "05.02.2019",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "Today",false));
        targetsData.add(new TargetsModel("Run 1000 km", "Tomorrow",false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", "Today",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "05.02.2019",false));
        targetsData.add(new TargetsModel("Run 1000 km", "05.02.2019",false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", "05.02.2019",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "Today",false));
        targetsData.add(new TargetsModel("Run 1000 km", "Tomorrow",false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", "Today",false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", "05.02.2019",false));
        targetsData.add(new TargetsModel("Run 1000 km", "05.02.2019",false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", "05.02.2019",false));
    }
}
