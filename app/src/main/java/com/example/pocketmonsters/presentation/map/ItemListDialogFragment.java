package com.example.pocketmonsters.presentation.map;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.example.pocketmonsters.R;
import com.example.pocketmonsters.databinding.FragmentItemListDialogListDialogBinding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     ItemListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 */
public class ItemListDialogFragment extends BottomSheetDialogFragment {

    public static LatLng position;
    public static String distance;
    public static void setPosition(LatLng pos){
        ItemListDialogFragment.position = pos;
    }
    public static void setDistance(String dist){
        if (dist.equals("")){
            ItemListDialogFragment.distance = "100";
        } else {
            ItemListDialogFragment.distance = Integer.parseInt(dist)+100+"";
        }
    }

    private static final String ARG_ITEM_COUNT = "item_count";
    private FragmentItemListDialogListDialogBinding binding;
    public static ItemListDialogFragment newInstance(int itemCount) {
        final ItemListDialogFragment fragment = new ItemListDialogFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    ItemListDialogViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_dialog_list_dialog, container, false);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("user", getContext().MODE_PRIVATE);

        //setting the amulet level to set view distance of object properly
        String amuletLevel = sharedPref.getString("amuletLevel", "");
        setDistance(amuletLevel);

        String location = sharedPref.getString("location", "");
        String[] locationParts = location.split(",");
        if (locationParts.length == 2) {
            try {
                double lat = Double.parseDouble(locationParts[0]);
                double lng = Double.parseDouble(locationParts[1]);
                setPosition(new LatLng(lat, lng));
            } catch (NumberFormatException e) {
                Log.e("ItemListDialogFragment", "Invalid location format", e);
            }
        } else {
            Log.e("ItemListDialogFragment", "Invalid location string: " + location);
        }

        Log.d("ItemListDialogFragment", "onViewCreated successfully");

//        viewModel = new ViewModelProvider(this).get(ItemListDialogViewModel.class);
        viewModel = new ItemListDialogViewModel();
//        viewModel.setContext(getContext());
        viewModel.setContext(getActivity());
        viewModel.setItemListDialogFragment(this);

        viewModel.getVirtualItems().observe(getViewLifecycleOwner(), virtualItems -> {
            RecyclerView recyclerView = getView().findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            ItemListDialogAdapter adapter = new ItemListDialogAdapter(getContext(), viewModel, i -> {
                Log.d("ItemListDialogFragment", "Contact clicked at position " + i);
            });
            recyclerView.setAdapter(adapter);
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        final RecyclerView recyclerView = (RecyclerView) view;
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new ItemAdapter(getArguments().getInt(ARG_ITEM_COUNT)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    private class ViewHolder extends RecyclerView.ViewHolder {
//
//        final TextView text;
//
//        ViewHolder(FragmentItemListDialogListDialogItemBinding binding) {
//            super(binding.getRoot());
//            text = binding.text;
//        }
//
//    }

//    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {
//
//        private final int mItemCount;
//
//        ItemAdapter(int itemCount) {
//            mItemCount = itemCount;
//        }
//
//        @NonNull
//        @Override
//        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//            return new ViewHolder(FragmentItemListDialogListDialogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
//
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.text.setText(String.valueOf(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return mItemCount;
//        }
//
//    }
}