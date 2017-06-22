package garrymckee.mellobit.com.teamworksample.projectlist;

import android.support.v4.app.Fragment;

import garrymckee.mellobit.com.teamworksample.baseclasses.SingleFragmentActivity;

public class ProjectListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ProjectListFragment();
    }
}
