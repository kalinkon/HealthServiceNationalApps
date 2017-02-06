package com.mcc.healthservicefinal.activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.mcc.healthservicefinal.R;
import com.mcc.healthservicefinal.adapter.ListAdapterDiseaseList;
import com.mcc.healthservicefinal.handler.RequestSubMenuDiseaseList;
import com.mcc.healthservicefinal.objects.SubMenu;
import com.mcc.healthservicefinal.handler.RequestSubSubMenu;
import com.mcc.healthservicefinal.objects.SubSubMenu;


import java.util.ArrayList;

/**
 * Created by LINKON on 1/29/2017.
 */

public class SubMenuDiseaseListActivity extends AppCompatActivity{
    private ArrayList<SubMenu> subMenus = new ArrayList<>();
    private ArrayList<SubSubMenu> subSubMenus=new ArrayList<>();

    private GridView gridView;
    private String menuId;
    private String subMenuId;
    private String subSubMenuId;
    private ListView mListView;
    private String symptomSubSubMenuId;
    private String preventionsSubSubMenuId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub_menu_diseaselist);
        menuId = getIntent().getStringExtra("menuId");
        loadDataInBackground();
        ShowList();
        ShowDataOnItemClick();
    }

    private void loadDataInBackground() {

        try {
            RequestSubMenuDiseaseList requestSubMenuDiseaseList = new RequestSubMenuDiseaseList(this,menuId);
            requestSubMenuDiseaseList.execute();
            subMenus = requestSubMenuDiseaseList.getSubMenus();
        } catch (Exception e) {
            e.printStackTrace();
        }    }

    private void ShowList(){
        mListView = (ListView)findViewById(R.id.tvList);
        mListView.setAdapter(new ListAdapterDiseaseList(this,subMenus));
    }


    private void ShowDataOnItemClick(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                fetchNextMenuData(position);
                if (!subSubMenus.isEmpty()){

                    symptomSubSubMenuId=getSymtomSubSubMenuId();
                    preventionsSubSubMenuId=getPreventionsSubSubMenuId();
                    callNextIntentWithParameters();
                }
            }
        });
    }

    private String getSymtomSubSubMenuId(){

        for(int i=0;i<subSubMenus.size();i++){
            if (subSubMenus.get(i).getSubSubMenuName().contains("রোগের লক্ষণ")){
                return subSubMenus.get(i).getSubSubMenuId();
            }
        }
        return null;
    }


    private String getPreventionsSubSubMenuId(){
        for(int i=0;i<subSubMenus.size();i++){
            if (!(subSubMenus.get(i).getSubSubMenuName().contains("রোগের লক্ষণ"))){
                return subSubMenus.get(i).getSubSubMenuId();
            }
        }
       return null;
    }

    private String getSubMenuIdFromRequestedItem(int position){
        return subMenus.get(position).getSubMenuId();
    }

    private void callNextIntentWithParameters(){
        Intent intent = new Intent(SubMenuDiseaseListActivity.this,ContentDiseaseActivity.class);
        intent.putExtra("menuId", menuId);
        intent.putExtra("subMenuId", subMenuId);
        intent.putExtra("symtomId",symptomSubSubMenuId);
        intent.putExtra("preventionId",preventionsSubSubMenuId);
        startActivity(intent);
    }

 private void fetchNextMenuData(int position){
     subMenuId = getSubMenuIdFromRequestedItem(position);
     RequestSubSubMenu requestSubSubMenu = new RequestSubSubMenu(SubMenuDiseaseListActivity.this, menuId, subMenuId);
     requestSubSubMenu.execute();
     subSubMenus = requestSubSubMenu.getSubSubMenus();
 }

}
