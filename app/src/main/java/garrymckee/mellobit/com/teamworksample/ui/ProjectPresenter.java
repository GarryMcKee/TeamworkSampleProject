package garrymckee.mellobit.com.teamworksample.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.List;

import garrymckee.mellobit.com.teamworksample.api.TeamworkApiService;
import garrymckee.mellobit.com.teamworksample.api.TeamworkApiUtils;
import garrymckee.mellobit.com.teamworksample.model.People;
import garrymckee.mellobit.com.teamworksample.model.Person;
import garrymckee.mellobit.com.teamworksample.model.Project;
import garrymckee.mellobit.com.teamworksample.model.ProjectRepository;
import garrymckee.mellobit.com.teamworksample.model.Projects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Garry on 17/06/2017.
 */

public class ProjectPresenter implements ProjectContract.ProjectPresenter {

    private ProjectContract.ProjectFragment mView;

    public ProjectPresenter(ProjectContract.ProjectFragment projectFragment) {
        mView = projectFragment;
    }

    @Override
    public Project getProject(int projectId) {
        Project project = ProjectRepository.getInstance().getProject(projectId);
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
                mView.onPeopleReady(response.body().getPeople());
            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                Log.e("CHECKPERSON", "GetPeople call failed");
            }
        });
    }

    @Override
    public void sendEmail(String[] emails, String subject, Context context) {
        //Todo bug when e-mail app is still open, uses old intent data
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        Log.d("CHECKEMAIL", "Sending to: " + emails[0]);
        intent.putExtra(Intent.EXTRA_EMAIL, emails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }

    }
}
