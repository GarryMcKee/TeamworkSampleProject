package garrymckee.mellobit.com.teamworksample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import garrymckee.mellobit.com.teamworksample.model.Project;

/**
 * Created by Garry on 16/06/2017.
 */

public class ProjectListFragment extends Fragment implements ProjectListContract.ProjectListFragment{

    private ProjectListPresenter mPresenter;

    @BindView(R.id.project_list_view)
    RecyclerView mProjectListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project_list, container, false);
        ButterKnife.bind(this, v);

        mProjectListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ProjectListPresenter(this);
        mPresenter.fetchProjects();
    }

    @Override
    public void onProjectsReady(List<Project> projects) {
        //Display projects
    }

    private class ProjectViewHolder extends RecyclerView.ViewHolder {

        public ProjectViewHolder(LayoutInflater layoutInflater, ViewGroup parent){
            super(layoutInflater.inflate(R.layout.project_list_item, parent, false));
        }
    }

    private class ProjectAdapter extends RecyclerView.Adapter<ProjectViewHolder> {

        private List<Project> mProjects;

        public ProjectAdapter(List<Project> projects) {
            mProjects = projects;
        }

        @Override
        public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ProjectViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
