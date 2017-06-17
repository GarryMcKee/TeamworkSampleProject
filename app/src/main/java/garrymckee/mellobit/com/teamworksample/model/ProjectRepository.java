package garrymckee.mellobit.com.teamworksample.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Garry on 17/06/2017.
 */

public class ProjectRepository {

    private HashMap<Integer, Project> mProjects;
    private static ProjectRepository mInstance;

    private ProjectRepository() {

    }

    public static ProjectRepository getInstance() {

        if(mInstance == null) {
            mInstance = new ProjectRepository();
            return mInstance;
        } else {
            return mInstance;
        }
    }

    public Project getProject(int id) {
        return mProjects.get(id);
    }

    public List<Project> getProjects() {
        return new ArrayList<Project>(mProjects.values());
    }

    public void setProjects(List<Project> projects) {
        mProjects = new HashMap<>();
        for (Project project : projects) {
            mProjects.put(project.getId(), project);
        }
    }

}
