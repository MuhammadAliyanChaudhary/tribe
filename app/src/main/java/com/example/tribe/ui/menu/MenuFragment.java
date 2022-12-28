package com.example.tribe.ui.menu;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.tribe.LC.LoginActivity;
import com.example.tribe.R;


import com.example.tribe.databinding.FragmentMenuBinding;
import com.example.tribe.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuFragment extends Fragment {


   private Toolbar toolbar;
    private FragmentMenuBinding binding;
    CardView memories,community, islam, learning, kidshealth;
    LinearLayout logoutButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MenuViewModel menuViewModel =
                new ViewModelProvider(this).get(MenuViewModel.class);

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        memories=root.findViewById(R.id.memories);
        islam=root.findViewById(R.id.islam);
        learning=root.findViewById(R.id.learning);
        kidshealth=root.findViewById(R.id.kidshealth);
        logoutButton=root.findViewById(R.id.logout_btn);
        community=root.findViewById(R.id.community);
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_menu_to_community);

            }
        });
        memories.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_navigation_menu_to_memories2));
        islam.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_navigation_menu_to_islam));
        learning.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_navigation_menu_to_learning));
        kidshealth.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_navigation_menu_to_kids_health));

        toolbar = getActivity().findViewById(R.id.main_menu_toolbar);
        logoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            SharedPreferences settings = getActivity().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            new Utils(getActivity()).clearSharedPrefs();
            new Utils(getActivity()).SetShowOnboard(false);
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

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