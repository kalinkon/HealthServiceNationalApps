package com.mcc.healthservicefinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mcc.healthservicefinal.R;
import com.mcc.healthservicefinal.objects.Content;

import java.util.ArrayList;

/**
 * Created by LINKON on 1/29/2017.
 */

public class ListAdapterHospitalList extends BaseAdapter{
    private ArrayList<Content> hospitals = new ArrayList<>();
    private Context mContext;
    public ListAdapterHospitalList(Context mContext, ArrayList<Content> hospitals){
        this.mContext=mContext;
        this.hospitals=hospitals;
    }
    public int getCount(){
        return hospitals.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View row= convertView;
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(row==null){
            row =inflater.inflate(R.layout.hospital_list_view,null);
            TextView textView = (TextView)row.findViewById(R.id.tvContent);
            textView.setText(hospitals.get(position).getDetails());
        }
        return row;
    }
}
