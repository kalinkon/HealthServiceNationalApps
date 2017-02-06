package com.mcc.healthservicefinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mcc.healthservicefinal.R;
import com.mcc.healthservicefinal.objects.SubMenu;

import java.util.ArrayList;

/**
 * Created by LINKON on 1/29/2017.
 */

public class ListAdapterDiseaseList extends BaseAdapter{
    private ArrayList<SubMenu> subMenus = new ArrayList<>();
    private Context mContext;
    public ListAdapterDiseaseList(Context mContext, ArrayList<SubMenu> subMenus){
        this.mContext=mContext;
        this.subMenus=subMenus;
    }
    public int getCount(){
        return subMenus.size();
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
            row =inflater.inflate(R.layout.text_sub_menu,null);
            holder=new MyHolder(row);
            row.setTag(holder);
        }
        else{
            holder=(MyHolder)row.getTag();
        }

        holder.keyword.setText(subMenus.get(position).getSubMenuName());
        return row;
    }

    public class MyHolder {
        TextView keyword;

        public MyHolder(View v) {
            keyword = (TextView) v.findViewById(R.id.tvtext);
        }

    }
}



