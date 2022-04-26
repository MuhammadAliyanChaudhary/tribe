package com.example.tribe.ui.home;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tribe.R;
import com.example.tribe.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private Toolbar toolbar;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
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


//        menu.findItem(R.id.icon_profile_btn).setVisible(false);
        inflater.inflate(R.menu.main_custom_menu, menu);
        menu.findItem(R.id.icon_profile_btn).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);

    }






/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.icon_search_btn:

                Toast.makeText(getActivity(), "search icon", Toast.LENGTH_SHORT).show();
                return true;
            default:

            return super.onOptionsItemSelected(item);


        }

    }*/

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }


    }
