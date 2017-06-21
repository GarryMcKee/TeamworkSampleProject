package garrymckee.mellobit.com.teamworksample.ui;

import android.util.Log;

import garrymckee.mellobit.com.teamworksample.api.TeamworkApiService;
import garrymckee.mellobit.com.teamworksample.api.TeamworkApiUtils;
import garrymckee.mellobit.com.teamworksample.model.ProjectRepository;
import garrymckee.mellobit.com.teamworksample.model.Projects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Garry on 16/06/2017.
 */

public class ProjectListPresenter implements ProjectListContract.ProjectListPresenter{

    private ProjectListContract.ProjectListFragment mView;
    private static final String LOG_TAG = ProjectListPresenter.class.getSimpleName();

    public ProjectListPresenter(ProjectListContract.ProjectListFragment view) {
        mView = view;
    }

    @Override
    public void fetchProjects() {
        //Projects will only be null if they have not been already fetched in the current app session
        if(ProjectRepository.getInstance().getProjects() != null) {
            getProjectsFromLocal();
        } else {
            getProjectsFromRemote();
        }

    }

    @Override
    public int getProjectId(int position) {
        return ProjectRepository.getInstance().getProjects().get(position).getId();
    }

    private void getProjectsFromLocal() {
        if(mView != null) {
            mView.onProjectsReady(ProjectRepository.getInstance().getProjects());
        } else {
            Log.e(LOG_TAG, "Null view");
        }

    }

    private void getProjectsFromRemote() {
        TeamworkApiService apiService = TeamworkApiUtils.getApiService();
        String authString = TeamworkApiUtils.getAuthHeaderString();
        Call<Projects> call = apiService.listProjects(authString);
        call.enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {

                if(mView != null) {
                    ProjectRepository.getInstance().setProjects(response.body().getProjects());
                    mView.onProjectsReady(ProjectRepository.getInstance().getProjects());
                } else {
                    Log.e(LOG_TAG, "Null view");
                }

            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                Log.e(LOG_TAG, "GetProjects call failed");
                mView.showErrorState();
            }
        });
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
