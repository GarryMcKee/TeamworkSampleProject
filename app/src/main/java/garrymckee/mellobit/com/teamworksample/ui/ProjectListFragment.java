package garrymckee.mellobit.com.teamworksample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import garrymckee.mellobit.com.teamworksample.R;
import garrymckee.mellobit.com.teamworksample.model.Project;

/**
 * Created by Garry on 16/06/2017.
 */

public class ProjectListFragment extends Fragment implements ProjectListContract.ProjectListView {

    private ProjectListPresenter mPresenter;

    @BindView(R.id.project_list_view)
    RecyclerView mProjectListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new ProjectListPresenter(this);
        mPresenter.fetchProjects();
        setupUi();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void onProjectsReady(List<Project> projects) {
        mProjectListView.setAdapter(new ProjectAdapter(projects));
    }

    @Override
    public void setupUi() {
        mProjectListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void showErrorState() {
        Toast.makeText(
                getActivity(),
                R.string.general_error,
                Toast.LENGTH_LONG)
                .show();
    }

    protected class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.project_logo)
        SimpleDraweeView projectLogo;
        @BindView(R.id.project_name_text_view)
        TextView projectNameTextView;
        @BindView(R.id.project_desc_text_view)
        TextView projectDescTextview;

        public ProjectViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int projectId = mPresenter.getProjectId(this.getLayoutPosition());
            mPresenter.showProject(getActivity(), projectId);
        }
    }

    private class ProjectAdapter extends RecyclerView.Adapter<ProjectViewHolder> {

        private List<Project> mProjects;

        public ProjectAdapter(List<Project> projects) {
            mProjects = projects;
        }

        @Override
        public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ProjectViewHolder(inflater.inflate(R.layout.project_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ProjectViewHolder holder, int position) {
            Project project = mProjects.get(position);
            holder.projectLogo.setImageURI(project.getLogo());
            holder.projectNameTextView.setText(project.getName());
            holder.projectDescTextview.setText(project.getDescription());
        }

        @Override
        public int getItemCount() {
            return mProjects.size();
        }
    }
}
