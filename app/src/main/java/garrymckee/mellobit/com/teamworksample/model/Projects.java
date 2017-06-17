package garrymckee.mellobit.com.teamworksample.model;

import java.util.List;

/**
 * Created by Garry on 17/06/2017.
 */

public class Projects {
    List<Project> projects;

    @Override
    public String toString() {
        String projectList = "";

        for (Project project : projects) {
            projectList = projectList + "\n" + project.toString();
        }

        return projectList;
    }
}
