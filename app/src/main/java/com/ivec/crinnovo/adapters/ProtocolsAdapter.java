package com.ivec.crinnovo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivec.crinnovo.R;
import com.ivec.crinnovo.model.Protocol;

import java.util.ArrayList;

/**
 * Created by jpgarduno on 3/28/15.
 */
public class ProtocolsAdapter extends BaseAdapter {
    private static final int TYPE_MAX_COUNT = 1;

    private ArrayList<Protocol> mData = new ArrayList<Protocol>();
    private LayoutInflater mInflater;

    public ProtocolsAdapter(LayoutInflater inflater) {
        mInflater = inflater;
    }

    public void addItem(final Protocol item) {
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
    public Protocol getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        convertView = mInflater.inflate(R.layout.protocol_view, null);
        ImageView protocolLogo = (ImageView)convertView.findViewById(R.id.protocol_logo);
        protocolLogo.setImageResource(mData.get(position).getLogo());
        TextView protocolId = (TextView)convertView.findViewById(R.id.protocol_id);
        protocolId.setText(mData.get(position).getId() + "");

        return convertView;
    }
}
