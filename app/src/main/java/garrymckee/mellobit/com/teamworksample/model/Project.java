package garrymckee.mellobit.com.teamworksample.model;

/**
 * Created by Garry on 16/06/2017.
 */

public class Project {
    private Company company;
    private boolean starred;
    private String name;
    private String description;
    private String logo;
    private int id;

    @Override
    public String toString() {
        return getName() + "id: " + getId();
    }

    public Company getCompany() {
        return company;
    }

    public boolean isStarred() {
        return starred;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLogo() {
        return logo;
    }

    public int getId() {
        return id;
    }
}
