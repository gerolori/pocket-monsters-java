package com.example.pocketmonsters.presentation.iteminteraction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pocketmonsters.R;
import com.example.pocketmonsters.data.local.virtualitem.VirtualItem;
import com.example.pocketmonsters.databinding.FragmentObjectInteractionBinding;

public class ObjectInteractionFragment extends DialogFragment {

    private static final String ARG_VIRTUAL_ITEM = "virtual_item";
    private FragmentObjectInteractionBinding binding;
    private VirtualItem item;

    public ObjectInteractionFragment() {
        // Required empty public constructor
    }

    public static ObjectInteractionFragment newInstance(VirtualItem item) {
        ObjectInteractionFragment fragment = new ObjectInteractionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_VIRTUAL_ITEM, (Parcelable) item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ObjectInteractionDialogTheme);

        if (getArguments() != null) {
            item = getArguments().getParcelable(ARG_VIRTUAL_ITEM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentObjectInteractionBinding.inflate(inflater, container, false);

        binding.dismissDialog.setOnClickListener(v -> dismiss());

        // Use the item here to populate UI if needed
        if (item != null) {
            // TODO: Display item information
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setVirtualItem(VirtualItem item) {
        this.item = item;
    }
}
