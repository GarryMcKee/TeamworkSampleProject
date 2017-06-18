package garrymckee.mellobit.com.teamworksample.ui;

import garrymckee.mellobit.com.teamworksample.api.TeamworkApiService;
import garrymckee.mellobit.com.teamworksample.api.TeamworkApiUtils;
import garrymckee.mellobit.com.teamworksample.model.ProjectRepository;
import garrymckee.mellobit.com.teamworksample.model.Projects;
import garrymckee.mellobit.com.teamworksample.ui.ProjectListContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                ProjectRepository.getInstance().setProjects(response.body().getProjects());
                mView.onProjectsReady(ProjectRepository.getInstance().getProjects());
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {

            }
        });
    }
}
