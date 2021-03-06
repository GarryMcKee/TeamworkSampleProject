package garrymckee.mellobit.com.teamworksample.projectdetail;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import garrymckee.mellobit.com.teamworksample.api.TeamworkApiService;
import garrymckee.mellobit.com.teamworksample.api.TeamworkApiUtils;
import garrymckee.mellobit.com.teamworksample.model.People;
import garrymckee.mellobit.com.teamworksample.model.Person;
import garrymckee.mellobit.com.teamworksample.model.Project;
import garrymckee.mellobit.com.teamworksample.model.ProjectRepository;
import garrymckee.mellobit.com.teamworksample.profilesheet.ProfileSheetFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Garry on 17/06/2017.
 */

public class ProjectPresenter implements ProjectContract.ProjectPresenter {

    private static final String LOG_TAG = ProjectPresenter.class.getSimpleName();
    private static final String DIALOG_PROFILE_SHEET = "dialogProfileSheet";

    private ProjectContract.ProjectView mView;

    public ProjectPresenter(ProjectContract.ProjectView view) {
        mView = view;
    }

    @Override
    public Project getProject(int projectId) {
        return ProjectRepository.getInstance().getProjectById(projectId);
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
                if(mView != null) {
                    mView.showGeneralError();
                    Log.e(LOG_TAG, "GetPeople call failed: " + t.getMessage());
                } else {
                    Log.e(LOG_TAG, "View is null");
                }
            }
        });
    }

    @Override
    public void showProfileSheet(FragmentManager fragmentManager, Person person) {
        ProfileSheetFragment profileSheetFragment = ProfileSheetFragment.newInstance(person);
        profileSheetFragment.show(fragmentManager, DIALOG_PROFILE_SHEET);
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
