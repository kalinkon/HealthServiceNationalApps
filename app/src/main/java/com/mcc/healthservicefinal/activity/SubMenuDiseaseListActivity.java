package com.mcc.healthservicefinal.activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;
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
    private ArrayList<SubMenu> searchedDisease = new ArrayList<>();
    private String searchString;
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
        ShowList(subMenus);
        ShowDataOnItemClick(subMenus);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        ImageView closeButton = (ImageView)searchView.findViewById(R.id.search_close_btn);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String newText){
//                searchString = newText;
//                searchView();
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query){
                searchString =query;
                searchView();
                return true;
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Clear query
                searchView.setQuery("", false);
                //Collapse the action view
                searchView.onActionViewCollapsed();
                //Collapse the search widget

                ShowList(subMenus);

            }
        });
        return true;
    }

    private void searchView(){

        for(int index=0;index<subMenus.size();index++){
            if(subMenus.get(index).getSubMenuName().contains(searchString)){
                searchedDisease.add(subMenus.get(index));
            }
            else{
                Toast.makeText(this,"Not Available",Toast.LENGTH_LONG).show();
            }
        }

        ShowList(searchedDisease);
        ShowDataOnItemClick(searchedDisease);



    }





    private void loadDataInBackground() {

        try {
            RequestSubMenuDiseaseList requestSubMenuDiseaseList = new RequestSubMenuDiseaseList(this,menuId);
            requestSubMenuDiseaseList.execute();
            subMenus = requestSubMenuDiseaseList.getSubMenus();
        } catch (Exception e) {
            e.printStackTrace();
        }    }

    private void ShowList(ArrayList<SubMenu> subMenus){
        mListView = (ListView)findViewById(R.id.tvList);
        mListView.setAdapter(new ListAdapterDiseaseList(this,subMenus));
    }


    private void ShowDataOnItemClick(final ArrayList<SubMenu> subMenus){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                fetchNextMenuData(position,subMenus);
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

    private String getSubMenuIdFromRequestedItem(int position,ArrayList<SubMenu> subMenus){
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

 private void fetchNextMenuData(int position,ArrayList<SubMenu> subMenus){
     subMenuId = getSubMenuIdFromRequestedItem(position,subMenus);
     RequestSubSubMenu requestSubSubMenu = new RequestSubSubMenu(SubMenuDiseaseListActivity.this, menuId, subMenuId);
     requestSubSubMenu.execute();
     subSubMenus = requestSubSubMenu.getSubSubMenus();
 }

}
