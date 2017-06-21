package garrymckee.mellobit.com.teamworksample.ui;

import java.util.List;

import garrymckee.mellobit.com.teamworksample.model.Project;

/**
 * Created by Garry on 16/06/2017.
 */

public interface ProjectListContract {

    interface ProjectListPresenter extends BasePresenter{
        void fetchProjects();
    }

    interface ProjectListFragment extends BaseView {
        void onProjectsReady(List<Project> projects);
    }
}
