package com.ivec.crinnovo.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ivec.crinnovo.MainActivity;
import com.ivec.crinnovo.R;
import com.ivec.crinnovo.adapters.ProjectsAdapter;
import com.ivec.crinnovo.model.Project;

/**
 * Created by jpgarduno on 3/28/15.
 */
public class ProjectFragment extends Fragment {

    private ListView projectList;
    private int protocolId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.project_select, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        protocolId = getArguments().getInt("protocolId");

        projectList = (ListView)view.findViewById(R.id.project_list);
        ProjectsAdapter adapter = new ProjectsAdapter(getActivity().getLayoutInflater());
        //Request projects depending on protocol selected
        //This collection should be retrieved by request and through an event listener
        String[] projects = {"Project 1", "Project 2", "Project 3"};
        for(int i = 0; i < projects.length; i++) {
            Project project = new Project();
            project.setProjectId(i);
            project.setName(projects[i]);
            adapter.addItem(project);
        }
        projectList.setAdapter(adapter);
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int project = Integer.parseInt((String) ((TextView) view.findViewById(R.id.project_id)).getText());
                TextView projectName = (TextView)view.findViewById(R.id.project_name);

                ((MainActivity)getActivity()).setToolbarTitle(projectName.getText());

                System.out.println("Project ID: " + project);
                PatientFragment patientFragment = new PatientFragment();

                Bundle b = new Bundle();
                b.putInt("projectId", project);
                patientFragment.setArguments(b);

                FragmentTransaction ft;
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainContent, patientFragment, "Patients");
                ft.commit();
                getActivity().setTitle("");
            }
        };
        projectList.setOnItemClickListener(clickListener);
    }
}
