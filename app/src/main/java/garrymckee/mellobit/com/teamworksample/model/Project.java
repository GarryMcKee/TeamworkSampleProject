package garrymckee.mellobit.com.teamworksample.model;

/**
 * Created by Garry on 16/06/2017.
 */

public class Project {
    Company company;
    boolean starred;
    String name;
    String description;
    String logo;
    int id;

    @Override
    public String toString() {
        return name + "id: " + id;
    }
}
