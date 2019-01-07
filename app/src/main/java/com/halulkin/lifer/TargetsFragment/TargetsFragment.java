package com.halulkin.lifer.TargetsFragment;

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

public class TargetsFragment extends Fragment {

    private RecyclerView rvTargets;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.targets_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTargets = (RecyclerView) view.findViewById(R.id.rvTargets);

        ((MainActivity) Objects.requireNonNull(getActivity())).closeDrawer();
        ((MainActivity) Objects.requireNonNull(getActivity())).expandToolbar();

        setupTargetsRecyclerView();
    }

    private void setupTargetsRecyclerView() {
        ArrayList<TargetsModel> targetsData = new ArrayList<>();
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", false));
        targetsData.add(new TargetsModel("Run 1000 km", false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", false));
        targetsData.add(new TargetsModel("Run 1000 km", false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", false));
        targetsData.add(new TargetsModel("Run 1000 km", false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", false));
        targetsData.add(new TargetsModel("Run 1000 km", false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", false));
        targetsData.add(new TargetsModel("Run 1000 km", false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", false));
        targetsData.add(new TargetsModel("Run 1000 km", false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", false));
        targetsData.add(new TargetsModel("Run 1000 km", false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", false));
        targetsData.add(new TargetsModel("Wake up at 6 o'clock", false));
        targetsData.add(new TargetsModel("Run 1000 km", false));
        targetsData.add(new TargetsModel("Learn full information about kotlin", false));
        targetsData.add(new TargetsModel("Earn 1000 dollars", false));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTargets.setLayoutManager(linearLayoutManager);
        TargetsAdapter targetsAdapter = new TargetsAdapter(getContext(), targetsData);
        rvTargets.setAdapter(targetsAdapter);
    }
}
