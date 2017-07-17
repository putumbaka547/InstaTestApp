package com.training.testapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.training.testapp.R;
import com.training.testapp.adapter.PostsAdapter;
import com.training.testapp.apiclient.BaseURL;
import com.training.testapp.apiclient.InstaAPI;
import com.training.testapp.models.APIResponse;
import com.training.testapp.models.Data;
import com.training.testapp.models.User;
import com.training.testapp.util.Util;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Hardcoding all the values to make the app work for time being
    private static final String ACCESS_TOKEN = "5733702393.9a4a473.0bfcb822bc9243eb9f4014ed222f9f04";
    private static final double LATITUDE = 40.4344520;
    private static final double LONGITUDE = -74.4910910;
    private static final String SCOPE = "public_content";
    private static final double DISTANCE = 5000;

    @Bind(R.id.allPosts)
    RecyclerView allPosts;

    @Bind(R.id.errorMsg)
    TextView errorMsg;

    @Bind(R.id.userProfile)
    LinearLayout userProfile;

    @Bind(R.id.userImage)
    ImageView userImage;

    @Bind(R.id.userName)
    TextView userName;

    private InstaAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        allPosts.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(allPosts.getContext(),
                layoutManager.getOrientation());
        allPosts.addItemDecoration(dividerItemDecoration);

        api = BaseURL.getAPI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        prepareViewPostsCall();
    }

    /**
     * Method for Get posts API call
     */
    private void prepareViewPostsCall() {
        Call<APIResponse> call = api.loadAllPosts(LATITUDE, LONGITUDE, ACCESS_TOKEN, SCOPE, DISTANCE);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                final APIResponse apiResponse = response.body();
                if (response.isSuccessful() && apiResponse != null) {
                    List<Data> dataList = apiResponse.getData();
                    if (dataList.size() > 0) {
                        setUI(dataList);
                    } else {
                        userProfile.setVisibility(View.GONE);
                        errorMsg.setVisibility(View.VISIBLE);
                        allPosts.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Method to set response to UI
     *
     * @param postList List of posts
     */
    private void setUI(List<Data> postList) {
        if (postList != null && postList.size() > 0) {
            errorMsg.setVisibility(View.GONE);
            allPosts.setVisibility(View.VISIBLE);
            userProfile.setVisibility(View.VISIBLE);

            User userDetails = postList.get(0).getUser();
            if (userDetails != null) {
                userName.setText(String.format(getString(R.string.user_name), userDetails.getUsername()));
                Util.loadImage(getApplicationContext(), userDetails.getProfilePicture(), userImage);
            }

            PostsAdapter adapter = new PostsAdapter(MainActivity.this, postList);
            allPosts.setAdapter(adapter);
        }
    }

}
