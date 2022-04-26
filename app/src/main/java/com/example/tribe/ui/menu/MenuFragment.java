package com.example.tribe.ui.menu;

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
import android.widget.Toolbar;

import com.example.tribe.R;
import com.example.tribe.databinding.FragmentMenuBinding;
import com.example.tribe.databinding.FragmentMessageBinding;
import com.example.tribe.ui.message.MessageViewModel;

public class MenuFragment extends Fragment {


   private Toolbar toolbar;
    private FragmentMenuBinding binding;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MenuViewModel menuViewModel =
                new ViewModelProvider(this).get(MenuViewModel.class);

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        toolbar = getActivity().findViewById(R.id.main_menu_toolbar);







        /*toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.icon_profile_btn){
                    Toast.makeText(getActivity(), "profile", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });*/








        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
       /* setHasOptionsMenu(true);*/
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

      /*  inflater.inflate(R.menu.profile_menu, menu);*/

        super.onCreateOptionsMenu(menu, inflater);
    }


}