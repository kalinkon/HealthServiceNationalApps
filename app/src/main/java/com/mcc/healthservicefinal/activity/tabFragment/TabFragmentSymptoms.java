package com.mcc.healthservicefinal.activity.tabFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcc.healthservicefinal.R;
import com.mcc.healthservicefinal.activity.ImageViewActivity;
import com.mcc.healthservicefinal.objects.Content;

import java.util.ArrayList;

/**
 * Created by LINKON on 1/30/2017.
 */

public class TabFragmentSymptoms extends Fragment {
    private ArrayList<Content> symtoms=null;
    private Context mContext;

    public void setSymtoms(ArrayList<Content> symtoms,Context mContext) {
        this.mContext=mContext;
        this.symtoms = symtoms;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab_fragment_symptoms, container, false);
        TextView textView =(TextView)view.findViewById(R.id.textView);

        textView.setText(symtoms.get(0).getDetails());
        ImageView imageView = (ImageView) view.findViewById(R.id.activityImage);
        Glide.with(mContext)
                .load(symtoms.get(0).getImg())
                .into(imageView);
        final String mImage =symtoms.get(0).getImg();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ImageViewActivity.class);
                intent.putExtra("image",mImage);
                startActivity(intent);

            }
        });

        return view;


    }
}
