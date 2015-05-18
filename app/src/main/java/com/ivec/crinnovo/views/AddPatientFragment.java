package com.ivec.crinnovo.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivec.crinnovo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jpgarduno on 4/6/15.
 */
public class AddPatientFragment extends Fragment {

    private View selectedView;

    Object callback = new mirko.android.datetimepicker.date.DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(mirko.android.datetimepicker.date.DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth)
        {
            if(selectedView != null) {
                Calendar calendarSelected = Calendar.getInstance();
                calendarSelected.set(year, monthOfYear, dayOfMonth, 0,0,0);
                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
                ((TextView)selectedView).setText(format1.format(calendarSelected.getTime()));
            }
        }

    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.add_new_patient, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout grid = (LinearLayout)getActivity().findViewById(R.id.visitGrid);

        for(int i = 0; i < 3; i ++) {
            LinearLayout row = (LinearLayout)View.inflate(getActivity(), R.layout.visit_row, null);
            TextView visit = (TextView)row.findViewById(R.id.visit_cell);
            visit.setText(getString(R.string.visit) + i);

            TextView date = (TextView)row.findViewById(R.id.date_cell);
            date.setText(R.string.select_date);
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedView = v;
                    Calendar myCalendar = Calendar.getInstance();
                    String tag = "";
                    mirko.android.datetimepicker.date.DatePickerDialog.newInstance((mirko.android.datetimepicker.date.DatePickerDialog.OnDateSetListener)callback, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show(getActivity().getFragmentManager(), tag);
                }
            });

            grid.addView(row);
        }
    }
}
