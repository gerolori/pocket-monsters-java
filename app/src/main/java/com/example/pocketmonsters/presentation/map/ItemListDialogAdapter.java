package com.example.pocketmonsters.presentation.map;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketmonsters.R;

public class ItemListDialogAdapter extends RecyclerView.Adapter<ItemListDialogViewHolder> {

    private ItemListDialogViewModel viewModel;
    private android.view.LayoutInflater mInflater;
    private ItemListDialogClickListener clickListener;

    public ItemListDialogAdapter(android.content.Context context, ItemListDialogViewModel viewModel, ItemListDialogClickListener clickListener) {
        this.viewModel = viewModel;
        this.mInflater = android.view.LayoutInflater.from(context);
        this.clickListener = clickListener;
    }
    @NonNull
    @Override
    public ItemListDialogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ItemListDialogViewHolder(view, clickListener, viewModel.getItemListDialogFragment());
    }
    @Override
    public void onBindViewHolder(@NonNull ItemListDialogViewHolder holder, int position) {
        holder.bind(viewModel.getItemPosition(position));
    }
    @Override
    public int getItemCount() {
        return viewModel.countItems();
    }
}
