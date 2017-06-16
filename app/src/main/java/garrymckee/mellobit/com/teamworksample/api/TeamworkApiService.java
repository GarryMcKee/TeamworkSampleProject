package garrymckee.mellobit.com.teamworksample.api;

import java.util.List;

import garrymckee.mellobit.com.teamworksample.model.Project;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Garry on 16/06/2017.
 */

public interface TeamworkApiService {

    public static final String TEAMWORK_API_BASE_URL = "";

    @GET("/projects.json")
    Call<List<Project>> listProjects(
            @Header("Authorization") String authHeader,
            @Path("id") String id
    );

}

