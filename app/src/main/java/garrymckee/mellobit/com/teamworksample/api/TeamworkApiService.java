package garrymckee.mellobit.com.teamworksample.api;

import garrymckee.mellobit.com.teamworksample.model.People;
import garrymckee.mellobit.com.teamworksample.model.Projects;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Garry on 16/06/2017.
 */

public interface TeamworkApiService {

    @GET("/projects.json")
    Call<Projects> listProjects(
            @Header("Authorization") String authHeader
    );

    @GET("/projects/{project_id}/people.json")
    Call<People> getPeopleFromProject (
            @Header("Authorization") String authHeader,
            @Path("project_id") String projectId
    );

}

