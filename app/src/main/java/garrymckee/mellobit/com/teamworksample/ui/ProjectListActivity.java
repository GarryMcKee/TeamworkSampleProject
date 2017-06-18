package garrymckee.mellobit.com.teamworksample.ui;

import android.support.v4.app.Fragment;

public class ProjectListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ProjectListFragment();
    }
}
