package garrymckee.mellobit.com.teamworksample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import garrymckee.mellobit.com.teamworksample.R;
import garrymckee.mellobit.com.teamworksample.model.Project;

/**
 * Created by Garry on 17/06/2017.
 */

public class ProjectFragment extends Fragment {

    public static final String ARG_PROJECT_ID = "arg_project_id";

    @BindView(R.id.name_text_view)
    TextView mNameTextview;

    @BindView(R.id.desc_text_view)
    TextView mDescTextView;

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
        mPresenter = new ProjectPresenter();
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

        }
        return v;
    }
}
