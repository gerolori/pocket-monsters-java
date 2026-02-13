package com.example.pocketmonsters.data.remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.pocketmonsters.BuildConfig;
import com.example.pocketmonsters.data.remote.callback.UserEditPositionShareCallback;
import com.example.pocketmonsters.data.remote.callback.UserEditProfilePictureCallback;
import com.example.pocketmonsters.data.remote.callback.VirtualItemActivatedCallback;
import com.example.pocketmonsters.data.remote.callback.RankingCallback;
import com.example.pocketmonsters.data.remote.callback.UserDetailCallback;
import com.example.pocketmonsters.data.remote.callback.UserEditNameCallback;
import com.example.pocketmonsters.data.remote.callback.UserIDCallback;
import com.example.pocketmonsters.data.remote.callback.UsersNearbyCallback;
import com.example.pocketmonsters.data.remote.callback.VirtualItemDetailCallback;
import com.example.pocketmonsters.data.remote.callback.VirtualItemNearbyCallback;
import com.example.pocketmonsters.model.UserDetail;
import com.example.pocketmonsters.model.UserID;
import com.example.pocketmonsters.model.UserNearby;
import com.example.pocketmonsters.model.UserRanking;
import com.example.pocketmonsters.model.VirtualItemActivated;
import com.example.pocketmonsters.model.VirtualItemDetail;
import com.example.pocketmonsters.model.VirtualItemNearby;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InterfaceConverter {
    private static final String SHARED_PREFS_USER = "user";
    private static final String PREF_KEY_SID = "sid";
    private static final String PREF_KEY_UID = "uid";
    private static final String PREF_KEY_PROFILE = "profile";
    private static final String SID_NOT_FOUND = "sid not found";
    private static final String UID_NOT_FOUND = "uid not found";
    private static final String TAG_GET_USER_ID = InterfaceConverter.class.getSimpleName() + "getUserID";
    private static final String TAG_REQUEST_USER_ID = InterfaceConverter.class.getSimpleName() + "requestUserID";
    private static final String TAG_GET_VIRTUAL_ITEMS_NEARBY = InterfaceConverter.class.getSimpleName() + "getVirtualItemsNearby";
    private static final String TAG_GET_RANKING = InterfaceConverter.class.getSimpleName() + "getRanking";
    private static final String TAG_EDIT_USER_NAME = InterfaceConverter.class.getSimpleName() + "editUserName";
    private static final String TAG_EDIT_PROFILE_PICTURE = InterfaceConverter.class.getSimpleName() + "editProfilePicture";
    private static final String TAG_EDIT_POSITION_SHARE = InterfaceConverter.class.getSimpleName() + "editPositionShare";

    private static String sidIC;
    private static String uidIC;
    private static final String API_URL = BuildConfig.API_URL;

    public static String getSidIC() {
        return sidIC;
    }

    public static String getUidIC() {
        return uidIC;
    }

    public static String getApiUrl() {
        return API_URL;
    }

    //getUserID and requestUserID
    public static void getUserID(UserIDCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(com.example.pocketmonsters.data.remote.ApiInterface.class);
        apiInterface.getUserID().enqueue(new Callback<UserID>() {
            @Override
            public void onResponse(Call<UserID> call, Response<UserID> response) {
                UserID userID = response.body();
                callback.onUserIDSuccess(userID);
            }

            @Override
            public void onFailure(Call<UserID> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    public static CompletableFuture<Void> requestUserID(Context context) {
        CompletableFuture<Void> future = new CompletableFuture<>();

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_USER, Context.MODE_PRIVATE);
        String sid = sharedPreferences.getString(PREF_KEY_SID, SID_NOT_FOUND);
        String uid = sharedPreferences.getString(PREF_KEY_UID, UID_NOT_FOUND);

        if (!sid.equals(SID_NOT_FOUND) && !uid.equals(UID_NOT_FOUND)) {

            InterfaceConverter.getUserID(new UserIDCallback() {
                @Override
                public void onUserIDSuccess(UserID userID) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(PREF_KEY_SID, userID.getSid());
                    editor.putString(PREF_KEY_UID, userID.getUid());
                    editor.apply();
                    sidIC = userID.getSid();
                    uidIC = userID.getUid();
                    future.complete(null);
                }

                @Override
                public void onError(Throwable throwable) {
                    future.completeExceptionally(throwable);
                }
            } );
        } else {
            Log.d(TAG_REQUEST_USER_ID, sid);
            Log.d(TAG_REQUEST_USER_ID, uid);
            sidIC = sid;
            uidIC = uid;
            future.complete(null);
        }
        return future;
    }

    //getVirtualItemNearby, requestVirtualItemNearby
    public static void getVirtualItemsNearby(VirtualItemNearbyCallback callback, double lat, double lon) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getVirtualItemsNearby(sidIC, lat, lon).enqueue(new Callback<List<VirtualItemNearby>>() {
            @Override
            public void onResponse(Call<List<VirtualItemNearby>> call, Response<List<VirtualItemNearby>> response) {
                List<VirtualItemNearby> virtualItemsNearby = response.body();
                callback.onVirtualItemsNearbySuccess(virtualItemsNearby);
            }

            @Override
            public void onFailure(Call<List<VirtualItemNearby>> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });

    }
    public static CompletableFuture<List<VirtualItemNearby>> requestVirtualItemsNearby(double lat, double lon) {
        CompletableFuture<List<VirtualItemNearby>> future = new CompletableFuture<>();
            InterfaceConverter.getVirtualItemsNearby(new VirtualItemNearbyCallback() {
                @Override
                public void onVirtualItemsNearbySuccess(List<VirtualItemNearby> virtualItemNearby) {
                    future.complete(virtualItemNearby);
                }
                @Override
                public void onError(Throwable throwable) {
                    future.completeExceptionally(throwable);
                }
            }, lat, lon);
        return future;
        }

    //getVirtualItemDetail, requestVirtualItemDetail
    public static void getVirtualItemDetail(VirtualItemDetailCallback callback, String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getVirtualItemDetail(id, sidIC).enqueue(new Callback<VirtualItemDetail>() {
            @Override
            public void onResponse(Call<VirtualItemDetail> call, Response<VirtualItemDetail> response) {
                VirtualItemDetail virtualItemDetail = response.body();
                callback.onVirtualItemDetailSuccess(virtualItemDetail);
            }
            @Override
            public void onFailure(Call<VirtualItemDetail> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
    public static CompletableFuture<VirtualItemDetail> requestVirtualItemDetail(Context context, String id) {
        CompletableFuture<VirtualItemDetail> future = new CompletableFuture<>();
        InterfaceConverter.getVirtualItemDetail(new VirtualItemDetailCallback() {
            @Override
            public void onVirtualItemDetailSuccess(VirtualItemDetail virtualItemDetail) {
                future.complete(virtualItemDetail);
            }
            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        }, id);
        return future;
    }

    //activateVirtualItem, requestActivateVirtualItem
    public static void getActivateVirtualItem(VirtualItemActivatedCallback callback, String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.activatedVirtualItem(id, sidIC).enqueue(new Callback<VirtualItemActivated>() {
            @Override
            public void onResponse(Call<VirtualItemActivated> call, Response<VirtualItemActivated> response) {
                VirtualItemActivated virtualItemActivated = response.body();
                callback.onActivatedVirtualItemSuccess(virtualItemActivated);
            }
            @Override
            public void onFailure(Call<VirtualItemActivated> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
    public static CompletableFuture<VirtualItemActivated> requestActivateVirtualItem(Context context, String id) {
        CompletableFuture<VirtualItemActivated> future = new CompletableFuture<>();
        InterfaceConverter.getActivateVirtualItem(new VirtualItemActivatedCallback() {
            @Override
            public void onActivatedVirtualItemSuccess(VirtualItemActivated virtualItemActivated) {
                future.complete(virtualItemActivated);
            }
            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        }, id);
        return future;
    }

    //getUserDetail, requestUserDetail

    public static void getUserDetail(UserDetailCallback callback, String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getUserDetail(id, sidIC).enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                UserDetail userDetail = response.body();
                callback.onUserDetailSuccess(userDetail);
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    public static CompletableFuture<UserDetail> requestUserDetail(Context context, String id) {
        CompletableFuture<UserDetail> future = new CompletableFuture<>();

        InterfaceConverter.getUserDetail(new UserDetailCallback() {
            @Override
            public void onUserDetailSuccess(UserDetail userDetail) {
                future.complete(userDetail);
            }

            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        }, id);
        return future;
    }

    public static CompletableFuture<UserDetail> requestLoggedUserDetail(Context context) {
        CompletableFuture<UserDetail> future = new CompletableFuture<>();

        InterfaceConverter.getUserDetail(new UserDetailCallback() {
            @Override
            public void onUserDetailSuccess(UserDetail userDetail) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_USER, Context.MODE_PRIVATE);
                String jsonProfile = sharedPreferences.getString(PREF_KEY_PROFILE, "");
                Gson gson = new Gson();
                UserDetail oldUserDetail = gson.fromJson(jsonProfile, UserDetail.class);

                if (oldUserDetail != null) {
                    userDetail.setName(oldUserDetail.getName());
                    userDetail.setProfilePicture(oldUserDetail.getProfilePicture());
                    userDetail.setPositionShared(oldUserDetail.getPositionShared());
                }

                Gson gsonProfile = new Gson();
                String jsonProfileTmp = gsonProfile.toJson(userDetail);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PREF_KEY_PROFILE, jsonProfileTmp);
                editor.apply();

                future.complete(userDetail);
            }

            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        }, uidIC);
        return future;
    }

    //getUserNearby, requestUserNearby
    public static void getUsersNearby(UsersNearbyCallback callback, String lat, String lon) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getUserNearby(sidIC, lat, lon).enqueue(new Callback<List<UserNearby>>() {
            @Override
            public void onResponse(Call<List<UserNearby>> call, Response<List<UserNearby>> response) {
                List<UserNearby> userNearby = response.body();
                callback.onUsersNearbySuccess(userNearby);
            }

            @Override
            public void onFailure(Call<List<UserNearby>> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
    public static CompletableFuture<List<UserNearby>> requestUsersNearby(String lat, String lon) {
        CompletableFuture<List<UserNearby>> future = new CompletableFuture<>();
        InterfaceConverter.getUsersNearby(new UsersNearbyCallback() {
            @Override
            public void onUsersNearbySuccess(List<UserNearby> userNearby) {
                future.complete(userNearby);
            }
            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        }, lat, lon);
        return future;
    }

    //TODO: editUserName, requestEditUserName
    public static void getEditUserName(UserEditNameCallback callback, String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.editUserName(uidIC , sidIC, name).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG_EDIT_USER_NAME, "onResponse: " + response);
                callback.onUserEditSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.d(TAG_EDIT_USER_NAME, "onFailure: " + throwable.getMessage());
                callback.onError(throwable);
            }
        });
    }
    public static CompletableFuture<Void> requestEditUserName(Context context, String newName) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_USER, Context.MODE_PRIVATE);

        InterfaceConverter.getEditUserName(new UserEditNameCallback() {
            @Override
            public void onUserEditSuccess() {
                String jsonProfile = sharedPreferences.getString(PREF_KEY_PROFILE, "");
                Gson gson = new Gson();
                UserDetail oldUserDetail = gson.fromJson(jsonProfile, UserDetail.class);
                oldUserDetail.setName(newName);

                String updateJsonProfile = gson.toJson(oldUserDetail);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PREF_KEY_PROFILE, updateJsonProfile);
                editor.apply();

                future.complete(null);
            }

            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        }, newName);
        return future;
    }

    //editProfilePicture, requestEditProfilePicture
    public static void getEditProfilePicture(UserEditProfilePictureCallback callback, String profilePicture) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.editProfilePicture(uidIC, sidIC, profilePicture).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG_EDIT_PROFILE_PICTURE, "onResponse: " + response);
                callback.onUserProfilePictureEditSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.d(TAG_EDIT_PROFILE_PICTURE, "onFailure: " + throwable.getMessage());
                callback.onError(throwable);
            }
        });
    }
    public static CompletableFuture<Void> requestEditProfilePicture(Context context, String newProfilePicture) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_USER, Context.MODE_PRIVATE);

        InterfaceConverter.getEditProfilePicture(new UserEditProfilePictureCallback() {
            @Override
            public void onUserProfilePictureEditSuccess() {
                String jsonProfile = sharedPreferences.getString(PREF_KEY_PROFILE, "");
                Gson gson = new Gson();
                UserDetail oldUserDetail = gson.fromJson(jsonProfile, UserDetail.class);
                oldUserDetail.setProfilePicture(newProfilePicture);

                String updateJsonProfile = gson.toJson(oldUserDetail);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PREF_KEY_PROFILE, updateJsonProfile);
                editor.apply();

                future.complete(null);
            }

            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        }, newProfilePicture);
        return future;
    }

    //editPositionShare, requestEditPositionShare
    private static void getEditPositionShare(UserEditPositionShareCallback callback, boolean positionShare) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.editPositionShare(uidIC, sidIC, positionShare).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG_EDIT_POSITION_SHARE, "onResponse: " + response);
                callback.onUserEditPositionShareSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.d(TAG_EDIT_POSITION_SHARE, "onFailure: " + throwable.getMessage());
                callback.onError(throwable);
            }
        });
    }
    private static CompletableFuture<Void> requestEditPositionShare(Context context, boolean newPositionShare) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_USER, Context.MODE_PRIVATE);

        InterfaceConverter.getEditPositionShare(new UserEditPositionShareCallback() {
            @Override
            public void onUserEditPositionShareSuccess() {
                String jsonProfile = sharedPreferences.getString(PREF_KEY_PROFILE, "");
                Gson gson = new Gson();
                UserDetail oldUserDetail = gson.fromJson(jsonProfile, UserDetail.class);
                oldUserDetail.setPositionShared(newPositionShare);

                String updateJsonProfile = gson.toJson(oldUserDetail);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PREF_KEY_PROFILE, updateJsonProfile);
                editor.apply();

                future.complete(null);
            }

            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        }, newPositionShare);
        return future;
    }

    //getRanking, requestRanking
    private static void getRanking(RankingCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getRanking(sidIC).enqueue(new Callback<List<UserRanking>>() {
            @Override
            public void onResponse(Call<List<UserRanking>> call, Response<List<UserRanking>> response) {
                List<UserRanking> userRanking = response.body();
                callback.onRankingSuccess(userRanking);
            }

            @Override
            public void onFailure(Call<List<UserRanking>> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
    private static CompletableFuture<List<UserRanking>> requestRanking(Context context) {
        CompletableFuture<List<UserRanking>> future = new CompletableFuture<>();

        InterfaceConverter.getRanking(new RankingCallback() {
            @Override
            public void onRankingSuccess(List<UserRanking> ranking) {
                future.complete(null);
            }

            @Override
            public void onError(Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        });
        return future;
    }

}
