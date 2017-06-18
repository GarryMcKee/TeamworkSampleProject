package garrymckee.mellobit.com.teamworksample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import garrymckee.mellobit.com.teamworksample.R;
import garrymckee.mellobit.com.teamworksample.model.Person;
import garrymckee.mellobit.com.teamworksample.model.Project;

/**
 * Created by Garry on 17/06/2017.
 */

public class ProjectFragment extends Fragment implements ProjectContract.ProjectFragment{

    public static final String ARG_PROJECT_ID = "arg_project_id";

    @BindView(R.id.name_text_view)
    TextView mNameTextview;

    @BindView(R.id.desc_text_view)
    TextView mDescTextView;

    @BindView(R.id.people_list_view)
    RecyclerView peopleListView;

    private int mProjectId;
    private boolean mValidProject;
    private ProjectContract.ProjectPresenter mPresenter;
    private Project mProject;

    public static ProjectFragment newInstance(int projectId) {

        Bundle args = new Bundle();
        args.putInt(ARG_PROJECT_ID, projectId);

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ProjectPresenter(this);
        mProjectId = getArguments().getInt(ARG_PROJECT_ID);
        mValidProject = mProjectId != -1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, v);
        if(mValidProject) {
            Project project = mPresenter.getProject(mProjectId);
            String projectName = project.getName();
            String projectDesc = project.getDescription();

            mNameTextview.setText(projectName);
            mDescTextView.setText(projectDesc);
            peopleListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.fetchPeople(mProjectId);
    }

    @Override
    public void onPeopleReady(List<Person> people) {
        PeopleAdapter adapter = new PeopleAdapter(people);
        peopleListView.setAdapter(adapter);

    }

    private class PeopleAdapter extends RecyclerView.Adapter<PersonHolder> {

        List<Person> mPeople;

        public PeopleAdapter(List<Person> people) {
            mPeople = people;
        }

        @Override
        public  PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new PersonHolder(inflater.inflate(R.layout.people_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(final PersonHolder holder, int position) {
            final Person person = mPeople.get(position);

            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            String displayName = firstName + " " + lastName;

            holder.personAvatarView.setImageURI(person.getAvatarUrl());
            holder.personNameTextView.setText(displayName);

            holder.emailPersonIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CHECKEMAIL", "Sending e-mail to: " + person.geteMail());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mPeople.size();
        }
    }

    protected class PersonHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.person_avatar_view)
        SimpleDraweeView personAvatarView;
        @BindView(R.id.person_name_text_view)
        TextView personNameTextView;
        @BindView(R.id.email_person_icon)
        ImageView emailPersonIcon;

        public PersonHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
