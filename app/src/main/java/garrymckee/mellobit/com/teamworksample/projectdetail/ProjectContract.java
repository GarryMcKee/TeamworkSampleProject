package garrymckee.mellobit.com.teamworksample.projectdetail;

import android.support.v4.app.FragmentManager;

import java.util.List;

import garrymckee.mellobit.com.teamworksample.model.Person;
import garrymckee.mellobit.com.teamworksample.model.Project;
import garrymckee.mellobit.com.teamworksample.baseclasses.BasePresenter;
import garrymckee.mellobit.com.teamworksample.baseclasses.BaseView;

/**
 * Created by Garry on 17/06/2017.
 */

public interface ProjectContract {

    interface ProjectPresenter extends BasePresenter {
        Project getProject(int projectId);
        void fetchPeople(int projectId);
        void showProfileSheet(FragmentManager fragmentManager, Person person);
    }

    interface ProjectView extends BaseView {
        void onPeopleReady(List<Person> people);
    }
}
