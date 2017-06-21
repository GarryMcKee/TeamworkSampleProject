package garrymckee.mellobit.com.teamworksample.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import garrymckee.mellobit.com.teamworksample.api.TeamworkApiService;
import garrymckee.mellobit.com.teamworksample.api.TeamworkApiUtils;
import garrymckee.mellobit.com.teamworksample.model.People;
import garrymckee.mellobit.com.teamworksample.model.Project;
import garrymckee.mellobit.com.teamworksample.model.ProjectRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Garry on 17/06/2017.
 */

public class ProjectPresenter implements ProjectContract.ProjectPresenter {

    private static final String LOG_TAG = ProjectPresenter.class.getSimpleName();

    private ProjectContract.ProjectView mView;

    public ProjectPresenter(ProjectContract.ProjectView view) {
        mView = view;
    }

    @Override
    public Project getProject(int projectId) {
        Project project = ProjectRepository.getInstance().getProjectById(projectId);
        return project;
    }

    @Override
    public void fetchPeople(int projectId) {
        TeamworkApiService apiService = TeamworkApiUtils.getApiService();
        String authString = TeamworkApiUtils.getAuthHeaderString();
        Call<People> call = apiService.getPeopleFromProject(authString, Integer.toString(projectId));
        call.enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                if(mView != null) {
                    mView.onPeopleReady(response.body().getPeople());
                } else {
                    Log.e(LOG_TAG, "View is null");
                }

            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                Log.e(LOG_TAG, "GetPeople call failed");
                mView.showErrorState();
            }
        });
    }

    @Override
    public void sendEmail(String[] emails, String subject, Context context) {
        //Todo bug when e-mail app is still open, uses old intent data
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, emails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }

    }

    @Override
    public void detachView() {
        mView = null;
    }
}
