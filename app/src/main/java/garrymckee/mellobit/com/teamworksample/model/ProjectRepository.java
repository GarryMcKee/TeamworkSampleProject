package garrymckee.mellobit.com.teamworksample.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Garry on 17/06/2017.
 */

public class ProjectRepository {

    private HashMap<Integer, Project> mProjects;

    private static ProjectRepository sInstance;

    private ProjectRepository() {
    }

    public static ProjectRepository getInstance() {

        if(sInstance == null) {
            sInstance = new ProjectRepository();
            return sInstance;
        } else {
            return sInstance;
        }
    }

    public Project getProjectById(int id) {
        return mProjects.get(id);
    }

    public List<Project> getProjects() {
        if(mProjects == null) {
            return null;
        } else {
            return new ArrayList<>(mProjects.values());
        }

    }

    public void setProjects(List<Project> projects) {
        mProjects = new HashMap<>();
        for (Project project : projects) {
            mProjects.put(project.getId(), project);
        }
    }

}
