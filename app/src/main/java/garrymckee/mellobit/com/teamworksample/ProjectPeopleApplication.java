package garrymckee.mellobit.com.teamworksample;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Garry on 22/06/2017.
 */

public class ProjectPeopleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
