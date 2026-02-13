package com.example.pocketmonsters.presentation.leaderboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocketmonsters.R;
import com.example.pocketmonsters.model.UserDetail;
import com.example.pocketmonsters.presentation.profile.ProfileFragment;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

    private TextView userName;
    private TextView userExperiencePoints;
    private ImageView userImage;
    private TextView userRank;
    private LeaderboardFragment leaderboardFragment;

    public LeaderboardViewHolder(@NonNull View itemView, LeaderboardClickListener clickListener, LeaderboardFragment leaderboardFragment) {
        super(itemView);

        this.leaderboardFragment = leaderboardFragment;
        userName = itemView.findViewById(R.id.userName);
        userExperiencePoints = itemView.findViewById(R.id.userExperiencePoints);
        userImage = itemView.findViewById(R.id.userImage);
        userRank = itemView.findViewById(R.id.userRank);
        itemView.setOnClickListener(v -> {
            clickListener.onLeaderboardClick(getAdapterPosition());
        });
    }

    public void bind(LeaderboardItem item) {
        userName.setText(item.getUsername());
        userExperiencePoints.setText(item.getScore() + " punti");
        userRank.setText(getAdapterPosition() + 1 + "°");
        userImage.setImageResource(R.drawable.baseline_account_circle_24);
    }

    private void openUserDetails(UserDetail user) {
        ProfileFragment profileFragment = new ProfileFragment();

        //todo add this method to the profile fragment
//        profileFragment.setUserDetail(user);

        FragmentManager fragmentManager = leaderboardFragment.getParentFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, profileFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void bindPicture(UserDetail user) {
        if (user.getProfilePicture() != null) {
            byte[] decodedString = android.util.Base64.decode(user.getProfilePicture(), android.util.Base64.DEFAULT);
            android.graphics.Bitmap decodedByte = android.graphics.BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            userImage.setImageBitmap(decodedByte);
        } else {
            userImage.setImageResource(R.drawable.baseline_account_circle_24);
        }
    }
}
