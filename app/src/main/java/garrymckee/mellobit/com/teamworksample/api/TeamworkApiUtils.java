package garrymckee.mellobit.com.teamworksample.api;

import okhttp3.Credentials;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Garry on 16/06/2017.
 */

public class TeamworkApiUtils {

    public static final String TEAMWORK_API_BASE_URL = "https://yat.teamwork.com";
    public static final String TEAMWORK_API_KEY = "april294unreal";

    public static TeamworkApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TEAMWORK_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(TeamworkApiService.class);
    }

    public static String getAuthHeaderString() {
        return Credentials.basic(TEAMWORK_API_KEY, "x");
    }

}
