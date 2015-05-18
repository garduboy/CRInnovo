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

import com.ivec.crinnovo.R;
import com.ivec.crinnovo.adapters.PatientsAdapter;
import com.ivec.crinnovo.model.Patient;

/**
 * Created by jpgarduno on 3/28/15.
 */
public class PatientFragment extends Fragment {

    private int projectId;
    private ListView patientList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.patient_select, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        projectId = getArguments().getInt("projectId");

        patientList = (ListView)view.findViewById(R.id.patient_list);
        PatientsAdapter adapter = new PatientsAdapter(getActivity().getLayoutInflater());
        //Request projects depending on protocol selected
        //This collection should be retrieved by request and through an event listener
        String[] patients = {"Patient 1", "Patient 2", "Patient 3"};
        for(int i = 0; i < patients.length; i++) {
            Patient patient = new Patient();
            patient.setId(i + 1);
            patient.setName(patients[i]);
            adapter.addItem(patient);
        }
        patientList.setAdapter(adapter);
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int patient = Integer.parseInt((String) ((TextView) view.findViewById(R.id.patient_id)).getText());
                if(patient == -1) {
                    //Add new patient
                    AddPatientFragment addPatientFragment = new AddPatientFragment();
                    FragmentTransaction ft;
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.mainContent, addPatientFragment, "Register Patient");
                    ft.commit();
                } else {
                    //Work with existing patient
                }
            }
        };
        patientList.setOnItemClickListener(clickListener);
    }
}
