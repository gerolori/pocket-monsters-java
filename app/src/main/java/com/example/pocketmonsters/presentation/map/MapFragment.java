package com.example.pocketmonsters.presentation.map;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.pocketmonsters.R;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MapFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMyLocationChangeListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener {
    public static final int FINE_PERMISSION_REQUEST = 1;
    protected GoogleMap myMap;

    protected Location currentLocation;
    protected FusedLocationProviderClient fusedLocationProviderClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_maps, container, false);

        FloatingActionButton button = v.findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });


        return v;
    }

    private void showBottomDialog() {
        final Dialog dialog = new Dialog(this.getContext());
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.bottom_sheet);


    dialog.show();
    dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    dialog.setCancelable(true);
    dialog.setCanceledOnTouchOutside(true);
    dialog.getWindow().setGravity(Gravity.BOTTOM);

    //bottom sheet behavior
    BottomSheetBehavior bsb = BottomSheetBehavior.from((View) dialog.findViewById(R.id.sheet));
    bsb.setPeekHeight(1000, true);
//    bsb.setGestureInsetBottomIgnored(true);
    bsb.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
    bsb.setHideable(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]
                    {android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_REQUEST);
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        fetchLocation();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]
                    {android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_REQUEST);
        }

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(requireActivity(), R.raw.style_json));

            if (!success) {
                Log.e("MapsLoading", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsLoading", "Can't find style. Error: ", e);
        }

        myMap = googleMap;

        myMap.setMyLocationEnabled(true);

        myMap.setOnMapClickListener(MapFragment.this);
        myMap.setOnMarkerClickListener(MapFragment.this);
        myMap.setOnInfoWindowClickListener(MapFragment.this);
        myMap.setOnMyLocationChangeListener(MapFragment.this);
        myMap.setOnMyLocationButtonClickListener(MapFragment.this);
        myMap.setOnMyLocationClickListener(MapFragment.this);

        myMap.getUiSettings().setZoomControlsEnabled(false);
        myMap.getUiSettings().setMyLocationButtonEnabled(true);
        myMap.getUiSettings().setCompassEnabled(true);
        myMap.getUiSettings().setTiltGesturesEnabled(true);

        handleMapsCallback();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if((requestCode == FINE_PERMISSION_REQUEST) && (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            fetchLocation();
        }
        else{
            Toast.makeText(requireActivity(), "Permission denied, please allow permission to access location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMyLocationChange(@NonNull Location location) {
        currentLocation = location;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    protected void fetchLocation() {
        if ((ActivityCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]
                    {android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_REQUEST);
        }

        CurrentLocationRequest clr = new CurrentLocationRequest.Builder().setPriority(Priority.PRIORITY_HIGH_ACCURACY).build();

        Task<Location> task = fusedLocationProviderClient.getCurrentLocation(clr, null);
        task.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location;

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("location", location.getLatitude() + "," + location.getLongitude());
                editor.apply();
                Log.d("MapFragment", "Location updated successfully to the shared preferences");

                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(this);
                }
            }
        });
    }

    protected void handleMapsCallback(){
        if(currentLocation != null){
            LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            myMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(latLng));
            myMap.animateCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(latLng, 18));
        }
    }
}

//    in case of needing  to update location via LatLng
//    protected void updateLatLng(LatLng latLng){
//        currentLocation.setLatitude(latLng.latitude);
//        currentLocation.setLongitude(latLng.longitude);
//    }
