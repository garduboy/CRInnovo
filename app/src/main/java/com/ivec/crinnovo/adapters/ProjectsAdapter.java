package com.ivec.crinnovo.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivec.crinnovo.R;
import com.ivec.crinnovo.model.Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jpgarduno on 3/28/15.
 */
public class ProjectsAdapter extends BaseAdapter {
    private static final int TYPE_MAX_COUNT = 1;

    private ArrayList<Project> mData = new ArrayList<Project>();
    private LayoutInflater mInflater;

    public ProjectsAdapter(LayoutInflater inflater) {
        mInflater = inflater;
    }

    public void addItem(final Project item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
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
    public Project getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        convertView = mInflater.inflate(R.layout.project_view, null);
        TextView projectName = (TextView)convertView.findViewById(R.id.project_name);
        projectName.setText(mData.get(position).getName());
        TextView projectId = (TextView)convertView.findViewById(R.id.project_id);
        projectId.setText(mData.get(position).getProjectId() + "");

        return convertView;
    }
}
