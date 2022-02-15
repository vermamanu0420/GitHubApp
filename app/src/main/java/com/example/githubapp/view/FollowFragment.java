package com.example.githubapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubapp.R;
import com.example.githubapp.viewmodel.GitHubUserViewModel;

import java.util.ArrayList;

public class FollowFragment extends Fragment {

    @BindView(R.id.followList)
    RecyclerView followList;

    private GitHubUserViewModel viewModel;
    private FollowListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_follow, container, false);
        ButterKnife.bind(this, view);
        viewModel = ViewModelProviders.of(getActivity()).get(GitHubUserViewModel.class);

        adapter = new FollowListAdapter(new ArrayList<>(), item -> {
            viewModel.fetchUserDetail(item.getLogin());
            ((MainActivity) getActivity()).replaceFragments(new GitHubUserDetailFragment());
        });

        followList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        followList.setAdapter(adapter);

        observerViewModel();

        return view;
    }

    private void observerViewModel() {
        viewModel.mutableFollowList.observe(getViewLifecycleOwner(), items -> {
            adapter.updateFollowList(items);
        });

    }
}



