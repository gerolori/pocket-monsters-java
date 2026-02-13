package com.example.pocketmonsters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.pocketmonsters.data.remote.InterfaceConverter;
import com.example.pocketmonsters.presentation.leaderboard.LeaderboardFragment;
import com.example.pocketmonsters.presentation.map.ItemListDialogFragment;
import com.example.pocketmonsters.presentation.map.MapFragment;
import com.example.pocketmonsters.presentation.iteminteraction.ObjectInteractionFragment;
import com.example.pocketmonsters.presentation.profile.ProfileFragment;
import com.example.pocketmonsters.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hasLocationPermission();

        //request user sid and uid
        InterfaceConverter.requestUserID(this).thenAccept(aVoid -> {
            //do nothing
            //      sets the default fragment to be the map fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MapFragment()).commit();
            Log.d("MainActivity", "UserID request successful");

        });

        //BOTTOM NAVIGATION BAR
//      sets the default bottomNavBar to be the map
        binding.bottomNavigationView.getMenu().findItem(R.id.map).setChecked(true);

        Button button = findViewById(R.id.testActionButton);
        button.setOnClickListener(v -> {
            DialogFragment dialog = new ObjectInteractionFragment();
            dialog.show(getSupportFragmentManager(), "ObjectInteractionFragment");
//            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, dialog).commit();
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;

            int itemId = item.getItemId();
            if (itemId == R.id.leaderboard) {
                selectedFragment = new LeaderboardFragment();
            } else if (itemId == R.id.profile) {
                selectedFragment = new ProfileFragment();
            } else if (itemId == R.id.map) {
                selectedFragment = new MapFragment();
            } else if (itemId == R.id.list) {
                selectedFragment = new ItemListDialogFragment();
            } else {
                selectedFragment = new MapFragment();
            }

            //replaces the fragment with the selected one
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
            return true;
        });
    }

//checks if the user has granted the location permission
    private void hasLocationPermission() {
        if(!(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

}