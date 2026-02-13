package com.example.pocketmonsters.presentation.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketmonsters.R;
import com.example.pocketmonsters.data.local.virtualitem.VirtualItem;
import com.example.pocketmonsters.presentation.iteminteraction.ObjectInteractionFragment;
import com.google.android.gms.maps.model.LatLng;

public class ItemListDialogViewHolder extends RecyclerView.ViewHolder {
    private TextView itemName;
    private TextView itemType;
    private ImageView itemImage;
    private ImageView interactionIcon;
    private ItemListDialogFragment fragment;
//    private ItemListDialogViewModel viewModel;

    public ItemListDialogViewHolder(@NonNull View itemView,ItemListDialogClickListener clickListener, ItemListDialogFragment fragment) {
        super(itemView);
        this.fragment = fragment;
//        this.viewModel = viewModel;

        itemName = fragment.getView().findViewById(R.id.userName);
        itemType = fragment.getView().findViewById(R.id.userExperiencePoints);
        itemImage = fragment.getView().findViewById(R.id.userImage);
        interactionIcon = fragment.getView().findViewById(R.id.interact);

        itemView.setOnClickListener(v -> {
            clickListener.onItemClicked(getBindingAdapterPosition());
        });
    }

    public void bind(VirtualItem item) {

        interactionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( "ItemListDialogViewHolder", "Clicked on item: " + item.toString());
                //TODO: maybe useless?
                LatLng coords = new LatLng(Double.parseDouble(item.getLat()), Double.parseDouble(item.getLon()));
                openObjectInteractionFragment(item);
            }
        });

        LatLng userLatLng = fragment.position;
        Double userLat = userLatLng.latitude;
        Double userLon = userLatLng.longitude;
        Double itemLat = Double.parseDouble(item.getLat());
        Double itemLon = Double.parseDouble(item.getLon());

        double distance = computeDistance(userLat, userLon, itemLat, itemLon);

        itemName.setText(item.getName());
        if (distance < Integer.parseInt(ItemListDialogFragment.distance)) {
//            itemImage.setBackgroundColor(0); //setting color to grey if not touchable
        } else {
            itemImage.setBackgroundColor(0); //setting color to grey if not touchable
        }

        itemName.setText(item.getName());
        bindType(item);
        bindInteractionIcon(item);
        bindImage(item);
    }

    private void openObjectInteractionFragment(VirtualItem item) {

        ObjectInteractionFragment newFragment = new ObjectInteractionFragment();

        newFragment.setVirtualItem(item);

        FragmentManager fragmentManager = fragment.getParentFragmentManager();

        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, newFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void bindType(VirtualItem item) {
        if (item.getType().equals("weapon")) {
            itemType.setText("Weapon");
        } else if (item.getType().equals("armor")) {
            itemType.setText("Armor");
        } else if (item.getType().equals("amulet")) {
            itemType.setText("Amulet");
        } else if (item.getType().equals("candy")) {
            itemType.setText("Candy");
        } else if (item.getType().equals("monster")) {
            itemType.setText("Monster");
        } else {
            itemType.setText("Type not supported");
        }
    }

    private void bindInteractionIcon(VirtualItem item) {
        if (item.getType().equals("monster")) {
            interactionIcon.setImageResource(R.drawable.default_weapon);
        } else {
            interactionIcon.setImageResource(R.drawable.baseline_front_hand_24);
        }
    }

    private void bindImage(VirtualItem item) {
        if(item.getImage() != null) {
            byte[] decodedString = Base64.decode(item.getImage(), Base64.DEFAULT);
            Bitmap decoded = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            itemImage.setImageBitmap(decoded);
        } else {
            if (item.getType().equals("weapon")) {
                itemImage.setImageResource(R.drawable.default_weapon);
            } else if (item.getType().equals("armor")) {
                itemImage.setImageResource(R.drawable.default_armor);
            } else if (item.getType().equals("amulet")) {
                itemImage.setImageResource(R.drawable.default_amulet);
            } else if (item.getType().equals("candy")) {
                itemImage.setImageResource(R.drawable.default_candy);
            } else if (item.getType().equals("monster")) {
                itemImage.setImageResource(R.drawable.default_monster);
            }
        }
    }

    public static double computeDistance(double myLat, double myLon, double lat, double lon) {
        //Calculating distance using Haversine formula
        double theta = myLon - lon;
        double dist = Math.sin(Math.toRadians(myLat)) * Math.sin(Math.toRadians(lat)) + Math.cos(Math.toRadians(myLat)) * Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }
}
