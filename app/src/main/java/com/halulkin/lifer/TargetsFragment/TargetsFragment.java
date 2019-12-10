package com.halulkin.lifer.TargetsFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halulkin.lifer.DBHelper;
import com.halulkin.lifer.MainActivity;
import com.halulkin.lifer.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TargetsFragment extends Fragment {

    public DBHelper db;
    private List<TargetsModel> targetsData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.targets_fragment, container, false);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(getContext());


        ((MainActivity) Objects.requireNonNull(getActivity())).closeDrawer();
        ((MainActivity) Objects.requireNonNull(getActivity())).expandToolbar();

        ((MainActivity) getActivity()).changeCollapsingToolbarImage(1);

        RecyclerView rvTargets = view.findViewById(R.id.rvTargets);
        TargetsAdapter targetsAdapter = new TargetsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTargets.setLayoutManager(linearLayoutManager);
        rvTargets.setAdapter(targetsAdapter);

//        fillTargetItems();

        targetsData = db.getAllTargets();
        targetsAdapter.loadItems(targetsData);

        db.read();
    }

    private void fillTargetItems() {
        TargetsModel targetsModel1 = new TargetsModel("Wake up at 6 o'clock up at 6 o'clock up at 6 o'clock up at 6 o'clock", "Today", 0);
        TargetsModel targetsModel2 = new TargetsModel("Run 100 km", "Tomorrow", 1);
        TargetsModel targetsModel3 = new TargetsModel("Earn 999$", "Yesterday", 0);
        TargetsModel targetsModel4 = new TargetsModel("Become most strong man ", "Night", 1);

//        targetsData.add(targetsModel1);
//        targetsData.add(targetsModel2);
//        targetsData.add(targetsModel3);
//        targetsData.add(targetsModel4);

        db.addTarget(targetsModel1);
        db.addTarget(targetsModel2);
        db.addTarget(targetsModel3);
        db.addTarget(targetsModel4);
    }
}
