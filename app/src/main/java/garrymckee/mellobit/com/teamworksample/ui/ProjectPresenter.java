package garrymckee.mellobit.com.teamworksample.ui;

import garrymckee.mellobit.com.teamworksample.model.Project;
import garrymckee.mellobit.com.teamworksample.model.ProjectRepository;

/**
 * Created by Garry on 17/06/2017.
 */

public class ProjectPresenter implements ProjectContract.ProjectPresenter {
    @Override
    public Project getProject(int projectId) {
        Project project = ProjectRepository.getInstance().getProject(projectId);
        return project;
    }
}
