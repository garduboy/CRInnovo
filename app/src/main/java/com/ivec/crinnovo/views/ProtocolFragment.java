package com.ivec.crinnovo.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ivec.crinnovo.MainActivity;
import com.ivec.crinnovo.R;
import com.ivec.crinnovo.adapters.ProtocolsAdapter;
import com.ivec.crinnovo.model.Protocol;

/**
 * Created by jpgarduno on 3/27/15.
 */
public class ProtocolFragment extends Fragment {

    private ListView protocolList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.protocol_select, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        protocolList = (ListView)view.findViewById(R.id.protocol_list);
        ProtocolsAdapter adapter = new ProtocolsAdapter(getActivity().getLayoutInflater());
        //Request projects depending on protocol selected
        //This collection should be retrieved by request and through an event listener
        int[] protocols = {0, 1, 2};
        for(int i = 0; i < protocols.length; i++) {
            Protocol protocol = new Protocol();
            protocol.setId(protocols[i]);
            switch (i) {
                case 0:
                    protocol.setLogo(R.drawable.bayer);
                    break;
                case 1:
                    protocol.setLogo(R.drawable.sanofi);
                    break;
                case 2:
                    protocol.setLogo(R.drawable.roche);
                    break;
            }
            adapter.addItem(protocol);
        }
        protocolList.setAdapter(adapter);
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int protocolId = Integer.parseInt((String) ((TextView) view.findViewById(R.id.protocol_id)).getText());
                ImageView protocolLogo = (ImageView)view.findViewById(R.id.protocol_logo);

                ((MainActivity)getActivity()).setToolbarDrawable(protocolLogo.getDrawable());

                System.out.println("Protocol ID: " + protocolId);
                ProjectFragment projectFragment = new ProjectFragment();

                Bundle b = new Bundle();
                b.putInt("protocolId", protocolId);
                projectFragment.setArguments(b);

                FragmentTransaction ft;
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainContent, projectFragment, "Projects");
                ft.commit();
                getActivity().setTitle("");
            }
        };
        protocolList.setOnItemClickListener(clickListener);
    }
}
