package garrymckee.mellobit.com.teamworksample.projectlist;

import android.content.Context;

import java.util.List;

import garrymckee.mellobit.com.teamworksample.model.Project;
import garrymckee.mellobit.com.teamworksample.baseclasses.BasePresenter;
import garrymckee.mellobit.com.teamworksample.baseclasses.BaseView;

/**
 * Created by Garry on 16/06/2017.
 */

public interface ProjectListContract {

    interface ProjectListPresenter extends BasePresenter {
        void fetchProjects();
        int getProjectId(int position);
        void showProject(Context context, int projectId);
    }

    interface ProjectListView extends BaseView {
        void onProjectsReady(List<Project> projects);
    }
}
