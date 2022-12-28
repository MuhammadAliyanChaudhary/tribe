package com.example.tribe.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tribe.Adapters.BlogAdapter;
import com.example.tribe.Adapters.DependentAdapter;
import com.example.tribe.Adapters.EventsAdapter;
import com.example.tribe.Adapters.MemberAdapter;
import com.example.tribe.Adapters.TribeMembers;
import com.example.tribe.Adapters.UserAdapter;
import com.example.tribe.LC.AddDependentsActivity;
import com.example.tribe.LC.MessageActivity;
import com.example.tribe.LC.Search;
import com.example.tribe.Model.DependentModel;
import com.example.tribe.Model.EventMOdel;
import com.example.tribe.Model.User;
import com.example.tribe.Notifications.Data;
import com.example.tribe.Notifications.MyResponse;
import com.example.tribe.Notifications.Sender;
import com.example.tribe.Notifications.Token;
import com.example.tribe.R;
import com.example.tribe.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private Toolbar toolbar;
    RecyclerView trybe_members_recyclerview, events_rec, dependents_rec;
    List<User> membersList;
    List<DependentModel> listDep;
    DependentAdapter dependentAdapter;


    CardView addMembers;
    TribeMembers tribeMembers;
    MemberAdapter memberAdapter;
    String trybeid="notadd";
   AlertDialog searchDialog;
   ImageButton addDependentsButton;
    private FragmentHomeBinding binding;

    ProgressDialog progressDialog;
    EventsAdapter eventsAdapter;
    List<EventMOdel>eventMOdelList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();





        progressDialog= new ProgressDialog(getActivity(),R.style.DialogStyle);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);

        toolbar = root.findViewById(R.id.main_trybe_toolbar);
        trybe_members_recyclerview = root.findViewById(R.id.trybe_members_recyclerview);
        events_rec = root.findViewById(R.id.events_rec);
        addMembers=root.findViewById(R.id.add_members_card);
        addDependentsButton = root.findViewById(R.id.add_dependents_button);
        dependents_rec = root.findViewById(R.id.dependents_recycler_view);

        addDependentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AddDependentsActivity.class);
                startActivity(intent);


            }
        });




        dependents_rec.setHasFixedSize(true);
        dependents_rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        listDep = new ArrayList<>();


        fetchDependents();












        membersList = new ArrayList<>();
        trybe_members_recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        trybe_members_recyclerview.setLayoutManager(linearLayoutManager);

        tribeMembers = new TribeMembers(membersList, getContext());
        trybe_members_recyclerview.setAdapter(tribeMembers);





        eventMOdelList =new ArrayList<>();
        eventsAdapter=new EventsAdapter(getContext(),eventMOdelList);
        events_rec.setLayoutManager(new LinearLayoutManager(getContext()));
        events_rec.setAdapter(eventsAdapter);

        addMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RecyclerView recyclerView;

                List<User> mUsers;
                EditText search_users;


                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View deleteDialogView = factory.inflate(R.layout.searchdialog, null);
                recyclerView = deleteDialogView.findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                mUsers = new ArrayList<>();


                search_users = deleteDialogView.findViewById(R.id.search_users);
                search_users.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        if(charSequence.length()>0){
                            final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
                            Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("search")
                                    .startAt(charSequence.toString())
                                    .endAt(charSequence.toString()+"\uf8ff");

                            query.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    mUsers.clear();
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                        User user = snapshot.getValue(User.class);

                                        assert user != null;
                                        assert fuser != null;
                                        if (!user.getId().equals(fuser.getUid())){
                                            mUsers.add(user);
                                        }
                                    }

                                    memberAdapter = new MemberAdapter(getActivity(), mUsers, false,trybeid);
                                    recyclerView.setAdapter(memberAdapter);


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        else{
                            mUsers.clear();
                            memberAdapter = new MemberAdapter(getActivity(), mUsers, false,trybeid);
                            recyclerView.setAdapter(memberAdapter);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


                searchDialog = new AlertDialog.Builder(getActivity()).create();
                searchDialog.setView(deleteDialogView);
                searchDialog.show();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.icon_search_btn) {
                    Toast.makeText(getActivity(), "search", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        getTrybeMenmbers();
        getEvents();
        return root;
    }


    void getEvents(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


                        FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("trybe").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                trybeid=snapshot.getValue(String.class);
                                FirebaseDatabase.getInstance().getReference("Events").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        eventMOdelList.clear();
                                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                            EventMOdel eventMOdel= dataSnapshot.getValue(EventMOdel.class);
                                            if(eventMOdel.getTrybeid().equals(trybeid))
                                            eventMOdelList.add(eventMOdel);
                                        }
                                        eventsAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


    }


    void getTrybeMenmbers() {
        progressDialog.show();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.orderByChild("trybe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User user = snapshot.getValue(User.class);
                    if (user.getTrybe().equals("notadded")) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("trybe");

                        trybeid = reference.push().getKey();

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("trybeid", trybeid);
                        if (searchDialog!=null){searchDialog.dismiss();}
                        reference.child(trybeid).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("trybe").setValue(trybeid);
                                progressDialog.dismiss();
                            }
                        });

                    }
                    else{
                        FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("trybe").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                 trybeid=snapshot.getValue(String.class);
                                FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        membersList.clear();
                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                            User user = snapshot.getValue(User.class);
                                            if(user.getTrybe().equals(trybeid))
                                            membersList.add(user);
                                        }
                                        tribeMembers.notifyDataSetChanged();
                                        progressDialog.dismiss();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_child_menu, menu);
        menu.findItem(R.id.icon_add_child_profile).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




    public void fetchDependents(){

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.orderByChild("trybe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    User user = snapshot.getValue(User.class);
                    String tribeId = user.getTrybe();


                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("dependents").child(tribeId);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            listDep.clear();

                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                DependentModel dependentModel = dataSnapshot.getValue(DependentModel.class);
                                listDep.add(dependentModel);


                            }
                            DependentAdapter dependentAdapter = new DependentAdapter(getContext(), listDep);
                            dependents_rec.setAdapter(dependentAdapter);
                            dependentAdapter.notifyDataSetChanged();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
