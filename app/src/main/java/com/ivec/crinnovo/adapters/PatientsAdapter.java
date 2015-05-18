package com.ivec.crinnovo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ivec.crinnovo.R;
import com.ivec.crinnovo.model.Patient;
import com.ivec.crinnovo.model.Project;

import java.util.ArrayList;

/**
 * Created by jpgarduno on 3/28/15.
 */
public class PatientsAdapter  extends BaseAdapter{

    private static final int TYPE_MAX_COUNT = 1;

    private ArrayList<Patient> mData = new ArrayList<Patient>();
    private LayoutInflater mInflater;

    public PatientsAdapter(LayoutInflater inflater) {
        mInflater = inflater;
    }

    public void addItem(final Patient item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Patient getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if(type == 0) {
            //Inflate add new patient layout
            convertView = mInflater.inflate(R.layout.patient_view, null);
            TextView patientName = (TextView) convertView.findViewById(R.id.patient_name);
            patientName.setText("Register new patient");
            TextView patientId = (TextView)convertView.findViewById(R.id.patient_id);
            patientId.setText(-1 + "");
        } else {
            convertView = mInflater.inflate(R.layout.patient_view, null);
            TextView patientName = (TextView) convertView.findViewById(R.id.patient_name);
            patientName.setText(mData.get(position).getName());
            TextView patientId = (TextView)convertView.findViewById(R.id.patient_id);
            patientId.setText(mData.get(position).getId() + "");
        }

        return convertView;
    }
}
