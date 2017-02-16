package com.mcc.healthservicefinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.mcc.healthservicefinal.R;
import com.mcc.healthservicefinal.objects.Content;

import java.util.ArrayList;

/**
 * Created by LINKON on 1/29/2017.
 */

public class ListAdapterHospitalList extends BaseAdapter implements Filterable{
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
        MyHolder holder;
        View row= convertView;
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(row==null){
            row =inflater.inflate(R.layout.hospital_list_view,null);
            holder=new MyHolder(row);
            row.setTag(holder);
//            TextView textView = (TextView)row.findViewById(R.id.tvtext);
//            textView.setText(hospitals.get(position).getDetails());
        }
        else{
            holder=(MyHolder)row.getTag();
        }
        holder.keyword.setText(hospitals.get(position).getDetails());
        return row;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class MyHolder {
        TextView keyword;

        public MyHolder(View v) {
            keyword = (TextView) v.findViewById(R.id.tvtext);
        }

    }
}
