package com.mcc.healthservicefinal.activity.tabFragment;

/**
 * Created by LINKON on 1/30/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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

public class TabFragmentPrevention extends Fragment {
    private ArrayList<Content> preventions=null;
    private Context mContext;

    public void setPreventions(ArrayList<Content> preventions, Context mContext){
        this.preventions=preventions;
        this.mContext=mContext;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_fragment_prevention, container, false);
        TextView textView =(TextView)view.findViewById(R.id.textView);
        textView.setText(preventions.get(0).getDetails());
        ImageView imageView = (ImageView) view.findViewById(R.id.activityImage);
        Glide.with(mContext)
                .load(preventions.get(0).getImg())
                .into(imageView);
        final String mImage = preventions.get(0).getImg();

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