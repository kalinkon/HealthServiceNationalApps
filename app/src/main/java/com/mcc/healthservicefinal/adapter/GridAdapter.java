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

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.content_grid_view, null);

            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            textView.setText(menus.get(position).getName().toString());

            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            Glide.with(mContext)
                    .load(menus.get(position).getImage())
                    .into(imageView);

        }
        else {
            grid = (View) convertView;
        }
        return grid;
    }

}
