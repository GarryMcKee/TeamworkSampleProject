package garrymckee.mellobit.com.teamworksample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

    private static final int PROJECT_DESC_MAX_LINES = 4;

    @BindView(R.id.desc_text_view)
    TextView mDescTextView;

    @BindView(R.id.people_list_view)
    RecyclerView mPeopleListView;

    @BindView(R.id.expand_desc_icon)
    ImageView mExpandDescIcon;
    private int mProjectId;
    private ProjectContract.ProjectPresenter mPresenter;

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
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new ProjectPresenter(this);
        mProjectId = getArguments().getInt(ARG_PROJECT_ID);
        mPresenter.fetchPeople(mProjectId);

        if (mPresenter != null) {
            setupUi();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.detachView();
        mPresenter = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    private void setupUi(){
        Project project = mPresenter.getProject(mProjectId);
        String projectName = project.getName();
        String projectDesc = project.getDescription();

        getActivity().setTitle(projectName);

        mDescTextView.setText(projectDesc);
        mDescTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleOverviewCollapse();

            }
        });

        mExpandDescIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleOverviewCollapse();
            }
        });

        mPeopleListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void toggleOverviewCollapse() {
        if(mDescTextView.getEllipsize() != null) {
            mDescTextView.setMaxLines(Integer.MAX_VALUE);
            mDescTextView.setEllipsize(null);
            mExpandDescIcon.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
        } else {
            mDescTextView.setMaxLines(PROJECT_DESC_MAX_LINES);
            mDescTextView.setEllipsize(TextUtils.TruncateAt.END);
            mExpandDescIcon.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
        }
    }

    @Override
    public void onPeopleReady(List<Person> people) {
        PeopleAdapter adapter = new PeopleAdapter(people);
        mPeopleListView.setAdapter(adapter);

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
                    Project project = mPresenter.getProject(mProjectId);
                    String[] emails = new String[] {person.geteMail()};
                    mPresenter.sendEmail(emails, project.getName(), getActivity());
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
