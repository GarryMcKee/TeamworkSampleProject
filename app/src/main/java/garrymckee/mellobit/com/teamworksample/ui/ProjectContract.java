package garrymckee.mellobit.com.teamworksample.ui;

import android.content.Context;

import java.util.List;

import garrymckee.mellobit.com.teamworksample.model.Person;
import garrymckee.mellobit.com.teamworksample.model.Project;

/**
 * Created by Garry on 17/06/2017.
 */

public interface ProjectContract {

    interface ProjectPresenter {
        Project getProject(int projectId);
        void fetchPeople(int projectId);
        void sendEmail(String[] emails, String subject, Context context);
        void detachView();
    }

    interface ProjectFragment {
        void onPeopleReady(List<Person> people);
    }
}
