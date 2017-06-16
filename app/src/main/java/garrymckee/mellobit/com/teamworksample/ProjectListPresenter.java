package garrymckee.mellobit.com.teamworksample;

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
        //fetch projects
        mView.onProjectsReady(null);
    }
}
