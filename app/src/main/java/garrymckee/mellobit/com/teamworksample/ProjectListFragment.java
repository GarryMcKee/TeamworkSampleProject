package garrymckee.mellobit.com.teamworksample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import garrymckee.mellobit.com.teamworksample.model.Project;

/**
 * Created by Garry on 16/06/2017.
 */

public class ProjectListFragment extends Fragment implements ProjectListContract.ProjectListFragment{

    private ProjectListPresenter mPresenter;
    private List<Project> mProjects;

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
        if(!Fresco.hasBeenInitialized()) {
            Fresco.initialize(getActivity());
        }
        mPresenter = new ProjectListPresenter(this);
        mPresenter.fetchProjects();
    }

    @Override
    public void onProjectsReady(List<Project> projects) {
        mProjects = projects;
        mProjectListView.setAdapter(new ProjectAdapter(mProjects));
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.project_logo)
        SimpleDraweeView projectLogo;
        @BindView(R.id.project_name_text_view)
        TextView projectNameTextView;
        @BindView(R.id.project_desc_text_view)
        TextView projectDescTextview;

        public ProjectViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
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
