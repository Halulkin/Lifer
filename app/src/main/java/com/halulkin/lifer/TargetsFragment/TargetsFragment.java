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

import java.util.List;
import java.util.Objects;

public class TargetsFragment extends Fragment {

    private DBHelper db;
    private TargetsAdapter targetsAdapter;

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
        targetsAdapter = new TargetsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTargets.setLayoutManager(linearLayoutManager);
        rvTargets.setAdapter(targetsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<TargetsModel> targetsData = db.getAllTargets();
        targetsAdapter.loadItems(targetsData);
    }
}
