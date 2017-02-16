package com.mcc.healthservicefinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mcc.healthservicefinal.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import com.mcc.healthservicefinal.objects.MainMenu;

/**
 * Created by LINKON on 1/25/2017.
 */

public class GridAdapter extends BaseAdapter {

    private ArrayList<MainMenu> menus = new ArrayList<>();
    private Context mContext;

    public GridAdapter(Context context, ArrayList<MainMenu> menus){
        this.mContext = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder;
        View grid=convertView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (grid == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.content_grid_view,null);

            holder=new MyHolder(grid);
            grid.setTag(holder);

        }
        else {
            holder=(MyHolder)grid.getTag();
        }

        holder.keyword.setText(menus.get(position).getName());
        Glide.with(mContext)
                .load(menus.get(position).getImage())
                .into(holder.mImage);


        return grid;
    }

    public class MyHolder {
        TextView keyword;
        ImageView mImage;

        public MyHolder(View v) {
            keyword = (TextView) v.findViewById(R.id.grid_text);
            mImage = (ImageView)v.findViewById(R.id.grid_image);
        }

    }

}
