package com.mcc.healthservicefinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mcc.healthservicefinal.R;
import java.util.ArrayList;

import com.mcc.healthservicefinal.handler.RequestSubMenuDiseaseList;
import com.mcc.healthservicefinal.network.NetworkConnection;
import com.mcc.healthservicefinal.objects.MainMenu;
import com.mcc.healthservicefinal.adapter.GridAdapter;
import com.mcc.healthservicefinal.handler.RequestMainMenu;
import com.mcc.healthservicefinal.objects.SubMenu;

/**
 * Created by LINKON on 1/25/2017.
 */

public class MainMenuActivity extends AppCompatActivity {

    private ArrayList<MainMenu> menus = new ArrayList<>();
    private ArrayList<SubMenu> subMenus = new ArrayList<>();

    private GridView gridView;
    private NetworkConnection networkConnection;

    private String menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);




    }
    @Override
    protected void onStart() {
        super.onStart();
        networkConnection = new NetworkConnection(this);
        connectionInformation();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void doOperations() {

        loadDataInBackground();
        gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter( new GridAdapter(this, menus));
        showDataOnItemClick();

    }
    private void connectionInformation() {
        if (!networkConnection.isTurnedOn()) {
            turnOnDataOrWifi();
        }else{
            doOperations();
        }
    }
    private void turnOnDataOrWifi() {
        networkConnection.accessNetworkSettings(MainMenuActivity.this);
    }

    private void loadDataInBackground() {

        try {
            RequestMainMenu requestMainMenu = new RequestMainMenu(this);
            requestMainMenu.execute();
            menus = requestMainMenu.getMenus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDataOnItemClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fetchNextMenuData(position);

                if(subMenus.isEmpty()){
                    callNextHospitalMenuWithParameters();
                }
                else{
                    callDiseaseListWithParameters();
                }
            }
        });
    }

    private void callNextHospitalMenuWithParameters(){
        Intent intent = new Intent(MainMenuActivity.this, HospitalListActivity.class);
        intent.putExtra("menuId", menuId);
        startActivity(intent);
    }

    private void callDiseaseListWithParameters(){
        Intent intent =new Intent(MainMenuActivity.this,SubMenuDiseaseListActivity.class);
        intent.putExtra("menuId",menuId);
        startActivity(intent);
    }

    private void fetchNextMenuData(int position){
        menuId = getDataFromRequestedMenu(position);
        RequestSubMenuDiseaseList requestSubMenuDiseaseList= new RequestSubMenuDiseaseList(MainMenuActivity.this,menuId);
        requestSubMenuDiseaseList.execute();
        subMenus = requestSubMenuDiseaseList.getSubMenus();
    }


    private String getDataFromRequestedMenu(int position) {
        return menus.get(position).getMenuId().toString();
    }
}