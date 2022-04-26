package com.example.tribe.ui.message;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tribe.R;
import com.example.tribe.databinding.FragmentHomeBinding;
import com.example.tribe.databinding.FragmentMessageBinding;
import com.example.tribe.ui.home.HomeViewModel;

public class MessageFragment extends Fragment {

    private Toolbar toolbar;
    private FragmentMessageBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MessageViewModel messageViewModel =
                new ViewModelProvider(this).get(MessageViewModel.class);

        binding = FragmentMessageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        toolbar = root.findViewById(R.id.main_trybe_toolbar);





        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.icon_search_btn){
                    Toast.makeText(getActivity(), "search", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });










        return root;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.main_custom_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}