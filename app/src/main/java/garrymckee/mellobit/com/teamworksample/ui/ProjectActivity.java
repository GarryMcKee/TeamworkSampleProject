package garrymckee.mellobit.com.teamworksample.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

import garrymckee.mellobit.com.teamworksample.SingleFragmentActivity;

/**
 * Created by Garry on 17/06/2017.
 */

public class ProjectActivity extends SingleFragmentActivity {

    private static final String EXTRA_PROJECT_ID = "com.garrymckee.mellobit.teamworksample.project_id";

    public static Intent newIntent(Context context, int projectId) {
        Intent intent = new Intent(context, ProjectActivity.class);
        intent.putExtra(EXTRA_PROJECT_ID, projectId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return ProjectFragment.newInstance(getIntent().getIntExtra(EXTRA_PROJECT_ID, -1));
    }
}
