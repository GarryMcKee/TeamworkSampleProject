package garrymckee.mellobit.com.teamworksample;

import android.util.Log;

import java.util.List;

import garrymckee.mellobit.com.teamworksample.api.TeamworkApiService;
import garrymckee.mellobit.com.teamworksample.api.TeamworkApiUtils;
import garrymckee.mellobit.com.teamworksample.model.Project;
import garrymckee.mellobit.com.teamworksample.model.Projects;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Garry on 16/06/2017.
 */

public class ProjectListPresenter implements ProjectListContract.ProjectListPresenter{

    private ProjectListContract.ProjectListFragment mView;

    public ProjectListPresenter(ProjectListContract.ProjectListFragment view) {
        mView = view;
    }

    @Override
    public void fetchProjects() {
        TeamworkApiService apiService = TeamworkApiUtils.getApiService();
        String authString = TeamworkApiUtils.getAuthHeaderString();
        Call<Projects> call = apiService.listProjects(authString);
        call.enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {
                Log.d("CHECKPROJECT", response.body().toString());
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {

            }
        });
        mView.onProjectsReady(null);
    }
}
