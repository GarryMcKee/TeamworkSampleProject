package garrymckee.mellobit.com.teamworksample;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProjectListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ProjectListFragment();
    }
}
