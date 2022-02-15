package com.example.githubapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.githubapp.R;
import com.example.githubapp.utils.Util;
import com.example.githubapp.viewmodel.GitHubUserViewModel;

public class GitHubUserDetailFragment extends Fragment {

    @BindView(R.id.fullAvatar)
    ImageView fullAvatar;

    @BindView(R.id.gitHubUserNameValue)
    TextView gitHubUserNameValue;

    @BindView(R.id.gitHubNameValue)
    TextView gitHubNameValue;

    @BindView(R.id.gitHubUserFollowerValue)
    TextView gitHubUserFollowerValue;

    @BindView(R.id.gitHubUserFollowingValue)
    TextView gitHubUserFollowingValue;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @BindView(R.id.searchButton)
    Button searchButton;

    @BindView(R.id.searchTextview)
    EditText searchTerm;

    @BindView(R.id.userDetailScrollview)
    ScrollView userDetailScrollview;



    private GitHubUserViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_git_hub_user_detail, container, false);
        ButterKnife.bind(this, rootView);
        viewModel = ViewModelProviders.of(getActivity()).get(GitHubUserViewModel.class);
        observerViewModel();


        searchButton.setOnClickListener(view -> {
            viewModel.fetchUserDetail(searchTerm.getText().toString());
        });

        gitHubUserFollowerValue.setOnClickListener(view ->{
            ((MainActivity) getActivity()).replaceFragments(new FollowFragment());
            viewModel.fetchFollowersList(viewModel.githubUserDetail.getValue().getLogin());
                });

        gitHubUserFollowingValue.setOnClickListener(view -> {
            ((MainActivity) getActivity()).replaceFragments(new FollowFragment());
            viewModel.fetchFollowingList(viewModel.githubUserDetail.getValue().getLogin());
                });

        searchTerm.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                searchButton.performClick();
            }
            return false;
        });
        return rootView;
    }

    private void observerViewModel() {

        viewModel.githubUserDetail.observe(getViewLifecycleOwner(), item -> {
            listError.setVisibility(View.GONE);
            userDetailScrollview.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.GONE);
            gitHubUserNameValue.setText(item.getLogin());
            gitHubNameValue.setText(TextUtils.isEmpty(item.getName())? "N.A":item.getName());
            gitHubUserFollowerValue.setText(item.getFollowers().toString());
            gitHubUserFollowingValue.setText(item.getFollowing().toString());
            Util.loadImageCenterCrop(fullAvatar, item.getAvatarUrl(), Util.getProgressDrawable(fullAvatar.getContext()));
        });

        viewModel.imageLoadError.observe(getViewLifecycleOwner(), isError -> {
            if (isError != null) {
                userDetailScrollview.setVisibility(View.GONE);
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }

        });

        viewModel.loading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                userDetailScrollview.setVisibility(View.GONE);
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

        }


    }
